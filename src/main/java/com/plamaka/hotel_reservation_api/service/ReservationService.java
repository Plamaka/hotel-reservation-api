package com.plamaka.hotel_reservation_api.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.plamaka.hotel_reservation_api.dto.request.GuestPersonReservationRequeastDTO;
import com.plamaka.hotel_reservation_api.dto.request.ReservationRequestDTO;
import com.plamaka.hotel_reservation_api.dto.request.UpdateReservationRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.GetReservationResponseDTO;
import com.plamaka.hotel_reservation_api.dto.response.GuestPersonResponseDTO;
import com.plamaka.hotel_reservation_api.dto.response.GuestResponseDTO;
import com.plamaka.hotel_reservation_api.dto.response.ReservationResponseDTO;
import com.plamaka.hotel_reservation_api.dto.response.RoomReservationResponseDTO;
import com.plamaka.hotel_reservation_api.entity.Guest;
import com.plamaka.hotel_reservation_api.entity.GuestPerson;
import com.plamaka.hotel_reservation_api.entity.Reservation;
import com.plamaka.hotel_reservation_api.entity.ReservationRoom;
import com.plamaka.hotel_reservation_api.entity.Room;
import com.plamaka.hotel_reservation_api.enums.GuestType;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;
import com.plamaka.hotel_reservation_api.enums.RoomStatus;
import com.plamaka.hotel_reservation_api.exception.ExceededRoomCapacityException;
import com.plamaka.hotel_reservation_api.exception.GuestNotFoundException;
import com.plamaka.hotel_reservation_api.exception.ReservationConflictException;
import com.plamaka.hotel_reservation_api.exception.ReservationNotFoundException;
import com.plamaka.hotel_reservation_api.exception.RoomNotFoundException;
import com.plamaka.hotel_reservation_api.repository.GuestPersonRepository;
import com.plamaka.hotel_reservation_api.repository.GuestRepository;
import com.plamaka.hotel_reservation_api.repository.ReservationRepository;
import com.plamaka.hotel_reservation_api.repository.ReservationRoomRepository;
import com.plamaka.hotel_reservation_api.repository.RoomRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final RoomRepository roomRepository;
	private final GuestRepository guestRepository;
	private final GuestPersonRepository guestPersonRepository;
	private final ReservationRoomRepository reservationRoomRepository;
	
	public ReservationService(ReservationRepository reservationRepository,
			RoomRepository roomRepository,
			GuestRepository guestRepository,
			GuestPersonRepository guestPersonRepository,
			ReservationRoomRepository reservationRoomRepository) {				
		this.reservationRepository = reservationRepository;
		this.roomRepository =roomRepository;
		this.guestRepository = guestRepository;
		this.guestPersonRepository = guestPersonRepository;
		this.reservationRoomRepository = reservationRoomRepository;
	}
	
	public List<GetReservationResponseDTO> getAllPendingReservation() {
		List<Reservation> reservations = reservationRepository.findByStatus(ReservationStatus.PENDING);
		
		List<GetReservationResponseDTO> responses = new ArrayList<>();
		populateReservationToDTO(reservations, responses);	
		return responses;
	}
	
	public List<GetReservationResponseDTO> getReservationByStatus(ReservationStatus status) {
		List<Reservation> reservations = reservationRepository.findByStatus(status);
		
		List<GetReservationResponseDTO> responses = new ArrayList<>();
		populateReservationToDTO(reservations, responses);	
		return responses;
	}
	
	public ReservationResponseDTO getReservationById(Long id) {
		Reservation reservations = reservationRepository.findById(id).orElseThrow(
				() -> new ReservationNotFoundException(id));
		
		Guest guest = reservations.getGuest();
		GuestResponseDTO guestResponse = new GuestResponseDTO(guest);
		
		List<GuestPerson> gps = reservations.getGuestPersons();
		List<GuestPersonResponseDTO> gprDTOs  = new ArrayList<>();
		populateGuestPersonToDTO(gps, gprDTOs);

		List<ReservationRoom> rs = reservations.getReservationRoom();
		List<RoomReservationResponseDTO> rrrDTOs  = new ArrayList<>();
		populateReservationRoomToDTO(rs, rrrDTOs);
		
		return new ReservationResponseDTO(reservations, guestResponse, gprDTOs, rrrDTOs);
	}
	
	@Transactional
	public ReservationResponseDTO createReservation(ReservationRequestDTO requestDto) {
		Guest guest = guestRepository.findByIdAndIsDeletedFalse(requestDto.getGuestId()).orElseThrow(
				() -> new GuestNotFoundException(requestDto.getGuestId()));

		List<GuestPerson> guestPersons = new ArrayList<>();
		List<ReservationRoom> reservationRooms = new ArrayList<>();
		List<Room> rooms = new ArrayList<>();

		Reservation res = new Reservation(requestDto, guest);

		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for (var roomId : requestDto.getRoomIds()) {
			Room room = roomRepository.findById(roomId).orElseThrow(
					() -> new RoomNotFoundException(roomId));

			ReservationRoom conflict =
					reservationRoomRepository.findConflictingReservation(
									roomId,
									ReservationStatus.CANCELLED,
									LocalDate.now(),
									requestDto.getCheckOutDate(),
									requestDto.getCheckInDate())
							.orElse(null);

			if (conflict != null) {
				throw new ReservationConflictException(
						roomId,
						conflict.getReservation().getCheckInDate(),
						conflict.getReservation().getCheckOutDate());
			}
			rooms.add(room);

			totalAmount = totalAmount.add(reservationTotalSum(
					requestDto.getCheckInDate(), requestDto.getCheckOutDate(), room));

			ReservationRoom rr = new ReservationRoom(res, room);
			reservationRooms.add(rr);
		}

		populateGuestPersonsFromGPReservationDTO(guestPersons, requestDto.getGuestPersons(),res);
		exceededRoomCapacity(guestPersons.size() + 1, rooms);

		setReservation(res ,totalAmount, reservationRooms, guestPersons);		
		Reservation saved = saveReservation(res, reservationRooms, guestPersons);
		
		GuestResponseDTO guestResponse = new GuestResponseDTO(saved.getGuest());		
		List<GuestPersonResponseDTO> gprDTOs = new ArrayList<>();
		populateGuestPersonToDTO(saved.getGuestPersons(), gprDTOs);
		List<RoomReservationResponseDTO> rrrDTOs = new ArrayList<>();
		populateRoomToDTO(rooms, rrrDTOs);
		return new ReservationResponseDTO(saved, guestResponse, gprDTOs, rrrDTOs);
	}

	private static void setReservation(Reservation res, BigDecimal totalAmount, List<ReservationRoom> reservationRooms, List<GuestPerson> guestPersons) {
		res.setDepositAmount(totalAmount.multiply(BigDecimal.valueOf(0.5)));
		res.setTotalAmount(totalAmount);
		res.setReservationRoom(reservationRooms);
		res.setGuestPersons(guestPersons);
	}

	public GetReservationResponseDTO updateReservation(Long id, UpdateReservationRequestDTO requestDto) {
		Reservation res = reservationRepository.findById(id).orElseThrow(
				() -> new ReservationNotFoundException(id));
		
		res.setCheckInDate(requestDto.getCheckInDate());
		res.setCheckOutDate(requestDto.getCheckOutDate());
		res.getReservationRoom().clear();
					
		List<ReservationRoom> reservationRooms = res.getReservationRoom();
		List<GuestPerson> guestPersons = res.getGuestPersons();
		List<Room> rooms = new ArrayList<>();

		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for (var roomId : requestDto.getRoomIds()) {
			Room room = roomRepository.findById(roomId).orElseThrow(
					() -> new RoomNotFoundException(roomId));

			ReservationRoom conflict =
					reservationRoomRepository.findConflictingReservation(
								roomId,
								ReservationStatus.CANCELLED,
								LocalDate.now(),
								requestDto.getCheckOutDate(),
								requestDto.getCheckInDate())
							.orElse(null);

			if (conflict != null) {
				throw new ReservationConflictException(
						roomId,
						conflict.getReservation().getCheckInDate(),
						conflict.getReservation().getCheckOutDate());
			}
			rooms.add(room);

			totalAmount = totalAmount.add(reservationTotalSum(
					requestDto.getCheckInDate(), requestDto.getCheckOutDate(), room));

			ReservationRoom rr = new ReservationRoom(res, room);
			reservationRooms.add(rr);
		}

		exceededRoomCapacity(guestPersons.size() + 1, rooms);

		setReservation(res ,totalAmount, reservationRooms, guestPersons);		
		Reservation saved = saveReservation(res, reservationRooms, guestPersons);

		List<RoomReservationResponseDTO> rrrDTOs = new ArrayList<>();
		populateRoomToDTO(rooms, rrrDTOs);
		return new GetReservationResponseDTO(saved, rrrDTOs);
	}
	
	
	public void cancelReservation(Long id) {
		 Reservation res = reservationRepository.findById(id).orElseThrow(
				 () -> new ReservationNotFoundException(id));
		 
	 	res.setStatus(ReservationStatus.CANCELLED);	 	
	 	reservationRepository.save(res);
	}
	
	public void chackInReservation(Long id) {
		Reservation res = reservationRepository.findById(id).orElseThrow(
				 () -> new ReservationNotFoundException(id));
		 
		List<ReservationRoom> rrs = reservationRoomRepository.findByReservationId(id);
		List<Room> rooms = new ArrayList<>();
		 saveReservationStatuses(res, rrs, rooms, ReservationStatus.CHECKED_IN, RoomStatus.OCCUPIED);
	}
	
	public void chackOutReservation(Long id) {
		 Reservation res = reservationRepository.findById(id).orElseThrow(
				 () -> new ReservationNotFoundException(id));
		 
		 List<ReservationRoom> rrs = reservationRoomRepository.findByReservationId(id);
		 List<Room> rooms = new ArrayList<>();
		 saveReservationStatuses(res, rrs, rooms, ReservationStatus.CHECKED_OUT, RoomStatus.CLEANING);
	}
	
	
	
	private void saveReservationStatuses(Reservation res, List<ReservationRoom> rrs, List<Room> rooms,
			ReservationStatus checkedOut, RoomStatus cleaning) {
		res.setStatus(checkedOut);
	 	
	 	for (ReservationRoom rr : rrs) {
	 	    rooms.add(rr.getRoom());
	 	}
	 	
	 	for(var room : rooms) {
	 		room.setRoomStatus(cleaning);
	 		roomRepository.save(room);
	 	}
	 	 	
	 	reservationRepository.save(res);
	}

	public static GuestType isLegal(LocalDate birthDate) {
		Period period = Period.between(birthDate, LocalDate.now());
		
		if(period.getYears() >= 18) {
			return GuestType.ADULT;
		}
		else {
			return GuestType.CHILD;
		}
	}
	
	public void exceededRoomCapacity(Integer guest, List<Room> rooms) {
		int totalCapacity = 0;
		
		for(var room : rooms) {
			totalCapacity += room.getRoomType().getCapacity();
		}
		
		if(totalCapacity < guest) {
			throw new ExceededRoomCapacityException(totalCapacity - guest);
		}

	}
	
	public static BigDecimal reservationTotalSum(LocalDate checkIn, LocalDate checkOut, Room room) {
		BigDecimal total = BigDecimal.ZERO;
		
		Period period = Period.between(checkIn, checkOut); 
		
		BigDecimal times = BigDecimal.valueOf(period.getDays());
		
		total = room.getRoomType().getPricePerNight().multiply(times);
		
		return total;
	}
	
	
	public static void populateReservationToDTO(List<Reservation> reservations, List<GetReservationResponseDTO> responses) {
		for(var res : reservations) {		
			List<ReservationRoom> rs = res.getReservationRoom();
			List<RoomReservationResponseDTO> rrrDTOs  = new ArrayList<>();
			
			for(var r : rs) {			
				Room room = r.getRoom();
				RoomReservationResponseDTO rrrDto = new RoomReservationResponseDTO(room);
				rrrDTOs.add(rrrDto);
			}
			
			GetReservationResponseDTO dto = new GetReservationResponseDTO(res, rrrDTOs);
			responses.add(dto);
		}
	}
	
	private static void populateGuestPersonToDTO(List<GuestPerson> gps, List<GuestPersonResponseDTO> gprDTOs) {
		for (var gpr : gps) {
			GuestPersonResponseDTO gprDto = new GuestPersonResponseDTO(gpr);
			gprDTOs.add(gprDto);
		}
	}
	
	private static void populateGuestPersonsFromGPReservationDTO(List<GuestPerson> gps, List<GuestPersonReservationRequeastDTO> requestDto, Reservation res) {
		for (var person : requestDto) {
			GuestPerson gp = new GuestPerson(person, res);
			gp.setGuestType(isLegal(person.getBirthDate()));
			gps.add(gp);
		}
	}
	
	
	public static void populateReservationRoomToDTO(List<ReservationRoom> rs, List<RoomReservationResponseDTO> rrrDTOs) {
		for(var r : rs) {
			Room room = r.getRoom();			
			RoomReservationResponseDTO rrrDto = new RoomReservationResponseDTO(room);	
			rrrDTOs.add(rrrDto);
		}
	}
	
	public static void populateRoomToDTO(List<Room> rooms, List<RoomReservationResponseDTO> rrrDTOs) {
		for (var room : rooms) {
			RoomReservationResponseDTO dto = new RoomReservationResponseDTO(room);
			rrrDTOs.add(dto);
		}
	}
	
	public Reservation saveReservation(Reservation res, List<ReservationRoom> reservationRooms, List<GuestPerson> guestPersons) {
		Reservation saved = reservationRepository.save(res);

		for (var roomRes : reservationRooms) {
			roomRes.setReservation(saved);
			reservationRoomRepository.save(roomRes);
		}

		for (var guestPerson : guestPersons) {
			guestPerson.setReservation(saved);
			guestPersonRepository.save(guestPerson);
		}
		
		return saved;
	}
}
