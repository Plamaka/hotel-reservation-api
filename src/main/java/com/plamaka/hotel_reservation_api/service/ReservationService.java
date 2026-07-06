package com.plamaka.hotel_reservation_api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
import com.plamaka.hotel_reservation_api.exception.GuestIsDeletedException;
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
		
		for(var res : reservations) {
			GetReservationResponseDTO dto = new GetReservationResponseDTO();
			
			
			List<ReservationRoom> rs = res.getReservationRoom();
			List<RoomReservationResponseDTO> rrrDTOs  = new ArrayList<>();
			for(var r : rs) {
				RoomReservationResponseDTO rrrDto = new RoomReservationResponseDTO();
				
				Room room = r.getRoom();
				
				rrrDto.setId(room.getId());
				rrrDto.setTypeName(room.getRoomType().getTypeName());
				rrrDto.setRoomNumber(room.getRoomNumber());
				rrrDto.setFloor(room.getFloor());
				
				rrrDTOs.add(rrrDto);
			}
			
			
			dto.setId(res.getId());
			dto.setStatus(res.getStatus());
			dto.setGuestId(res.getGuest().getId());
			dto.setGuestFullName(res.getGuest().toStringFullName());
			dto.setGuestPhoneNumber(res.getGuest().getPhoneNumber());
			dto.setGuestPerson(res.getGuestPersons().size());
			dto.setRooms(rrrDTOs);
			dto.setCheckInDate(res.getCheckInDate());
			dto.setCheckOutDate(res.getCheckOutDate());
			dto.setPaymentMethod(res.getPaymentMethod());
			dto.setDepositAmount(res.getDepositAmount());
			dto.setTotalAmount(res.getTotalAmount());
			
			responses.add(dto);
		}
		
		return responses;
	}
	
	public List<GetReservationResponseDTO> getReservationByStatus(ReservationStatus status) {
		List<Reservation> reservations = reservationRepository.findByStatus(status);
		
		List<GetReservationResponseDTO> responses = new ArrayList<>();
		
		for(var res : reservations) {
			GetReservationResponseDTO dto = new GetReservationResponseDTO();
			
			
			List<ReservationRoom> rs = res.getReservationRoom();
			List<RoomReservationResponseDTO> rrrDTOs  = new ArrayList<>();
			for(var r : rs) {
				RoomReservationResponseDTO rrrDto = new RoomReservationResponseDTO();
				
				Room room = r.getRoom();
				
				rrrDto.setId(room.getId());
				rrrDto.setTypeName(room.getRoomType().getTypeName());
				rrrDto.setRoomNumber(room.getRoomNumber());
				rrrDto.setFloor(room.getFloor());
				
				rrrDTOs.add(rrrDto);
			}
			
			
			dto.setId(res.getId());
			dto.setStatus(res.getStatus());
			dto.setGuestId(res.getGuest().getId());
			dto.setGuestFullName(res.getGuest().toStringFullName());
			dto.setGuestPhoneNumber(res.getGuest().getPhoneNumber());
			dto.setGuestPerson(res.getGuestPersons().size());
			dto.setRooms(rrrDTOs);
			dto.setCheckInDate(res.getCheckInDate());
			dto.setCheckOutDate(res.getCheckOutDate());
			dto.setPaymentMethod(res.getPaymentMethod());
			dto.setDepositAmount(res.getDepositAmount());
			dto.setTotalAmount(res.getTotalAmount());
			
			responses.add(dto);
		}
		
		return responses;
	}
	
	public ReservationResponseDTO getReservationById(Long id) {
		Reservation reservations = reservationRepository.findById(id).orElseThrow(
				() -> new ReservationNotFoundException(id));
		
		Guest guest = reservations.getGuest();
		GuestResponseDTO guestResponse = new GuestResponseDTO();
		guestResponse.setId(guest.getId());
		guestResponse.setFullName(guest.toStringFullName());
		guestResponse.setEmail(guest.getEmail());
		
		List<GuestPerson> gps = reservations.getGuestPersons();
		List<GuestPersonResponseDTO> gprDTOs  = new ArrayList<>();
		for(var gpr : gps) {
			GuestPersonResponseDTO gprDto = new GuestPersonResponseDTO();
			
			gprDto.setId(gpr.getId());
			gprDto.setFullName(gpr.toStringFullName());
			gprDto.setGuestType(gpr.getGuestType());
			
			gprDTOs.add(gprDto);
		}
		
		List<ReservationRoom> rs = reservations.getReservationRoom();
		List<RoomReservationResponseDTO> rrrDTOs  = new ArrayList<>();
		for(var r : rs) {
			RoomReservationResponseDTO rrrDto = new RoomReservationResponseDTO();
			
			Room room = r.getRoom();
			
			rrrDto.setId(room.getId());
			rrrDto.setTypeName(room.getRoomType().getTypeName());
			rrrDto.setRoomNumber(room.getRoomNumber());
			rrrDto.setFloor(room.getFloor());
			
			rrrDTOs.add(rrrDto);
		}
		
		
		ReservationResponseDTO response = new ReservationResponseDTO();
		response.setId(reservations.getId());
		response.setStatus(reservations.getStatus());
		response.setGuest(guestResponse);
		response.setGuestPersons(gprDTOs);
		response.setRooms(rrrDTOs);
		response.setCheckInDate(reservations.getCheckInDate());
		response.setCheckOutDate(reservations.getCheckOutDate());
		response.setPaymentMethod(reservations.getPaymentMethod());
		response.setDepositAmount(reservations.getDepositAmount());
		response.setTotalAmount(reservations.getTotalAmount());
		
		return response;
	}
	
	@Transactional
	public ReservationResponseDTO createReservation(ReservationRequestDTO requestDto) {
		
		Guest guest = guestRepository.findById(requestDto.getGuestId()).orElseThrow(
				() -> new GuestNotFoundException(requestDto.getGuestId()));
		
		if(guest.getIsDeleted().equals(true)) {
			throw new GuestIsDeletedException(guest.getId());		
		}
		
		List<GuestPerson> gps = new ArrayList<>();
		List<ReservationRoom> rrs = new ArrayList<>();
		List<Room> rs = new ArrayList<>();
		
		Reservation res = new Reservation(
				ReservationStatus.PENDING,
				requestDto.getCheckInDate(),
				requestDto.getCheckOutDate(),
				requestDto.getPaymentMethod());


		res.setTotalAmount(0.0);
		res.setDepositAmount(0.0);
		res.setGuest(guest);

		double totalAmount = 0;
		
		for(var roomId : requestDto.getRoomIds()) {
			Room room = roomRepository.findById(roomId).orElseThrow(
					() -> new RoomNotFoundException(roomId));
			
			rs.add(room);
			
			ReservationRoom conflict =
				    reservationRoomRepository.findConflictingReservation(
				            roomId,
				            requestDto.getCheckOutDate(),
				            requestDto.getCheckInDate(),
				            ReservationStatus.CANCELLED)
				    .orElse(null);

				if (conflict != null) {
				    throw new ReservationConflictException(
				            roomId,
				            conflict.getReservation().getCheckInDate(),
				            conflict.getReservation().getCheckOutDate());
				}
			
				totalAmount += totalSum(
					requestDto.getCheckInDate(), requestDto.getCheckOutDate(), room);
			
			ReservationRoom rr = new ReservationRoom();
			rr.setReservation(res);
			rr.setRoom(room);
			
			rrs.add(rr);
		}		
		
		
		for(var person : requestDto.getGuestPersons()) {
			GuestPerson gp = new GuestPerson();
			
			gp.setFirstName(person.getFirstName());
			gp.setLastName(person.getLastName());
			gp.setBirthDate(person.getBirthDate());
			gp.setGuestType(isLegal(person.getBirthDate()));
			gp.setReservation(res);
			
			gps.add(gp);
		}

		exceededRoomCapacity(gps.size() + 1, rs);
		
		res.setDepositAmount(totalAmount * 0.5);
		res.setTotalAmount(totalAmount);
		res.setReservationRoom(rrs);
		res.setGuestPersons(gps);
		
		Reservation saved = reservationRepository.save(res);
		
		for(var  roomRes : rrs) {
			roomRes.setReservation(saved);
			reservationRoomRepository.save(roomRes);
		}
		
		for(var guestPerson : gps) {
			guestPerson.setReservation(saved);
			guestPersonRepository.save(guestPerson);
		}
		
		GuestResponseDTO guestResponse = new GuestResponseDTO();
		guestResponse.setId(guest.getId());
		guestResponse.setFullName(guest.toStringFullName());
		guestResponse.setEmail(guest.getEmail());
		
		List<GuestPersonResponseDTO> gprDTOs  = new ArrayList<>();
		for(var gpr : gps) {
			GuestPersonResponseDTO gprDto = new GuestPersonResponseDTO();
			
			gprDto.setId(gpr.getId());
			gprDto.setFullName(gpr.toStringFullName());
			gprDto.setGuestType(gpr.getGuestType());
			
			gprDTOs.add(gprDto);
		}
		
		List<RoomReservationResponseDTO> rrrDTOs  = new ArrayList<>();
		for(var room : rs) {
			RoomReservationResponseDTO rrrDto = new RoomReservationResponseDTO();
			
			rrrDto.setId(room.getId());
			rrrDto.setTypeName(room.getRoomType().getTypeName());
			rrrDto.setRoomNumber(room.getRoomNumber());
			rrrDto.setFloor(room.getFloor());
			
			rrrDTOs.add(rrrDto);
		}
		
		
		ReservationResponseDTO response = new ReservationResponseDTO();
		response.setId(saved.getId());
		response.setStatus(saved.getStatus());
		response.setGuest(guestResponse);
		response.setGuestPersons(gprDTOs);
		response.setRooms(rrrDTOs);
		response.setCheckInDate(saved.getCheckInDate());
		response.setCheckOutDate(saved.getCheckOutDate());
		response.setPaymentMethod(saved.getPaymentMethod());
		response.setDepositAmount(saved.getDepositAmount());
		response.setTotalAmount(saved.getTotalAmount());
		
		return response;
	}
	
	public GetReservationResponseDTO updateReservation(Long id, UpdateReservationRequestDTO requestDto) {
		Reservation res = reservationRepository.findById(id).orElseThrow(
				() -> new ReservationNotFoundException(id));
		
		res.setTotalAmount(0.0);
		res.setDepositAmount(0.0);
		res.setCheckInDate(requestDto.getCheckInDate());
		res.setCheckOutDate(requestDto.getCheckOutDate());
		res.getReservationRoom().clear();
		
		double totalAmount = 0;
		
		List<ReservationRoom> rrsOld = res.getReservationRoom();
			
		List<GuestPerson> gps = res.getGuestPersons();
		List<Room> rs = new ArrayList<>();
		for(var roomId : requestDto.getRoomIds()) {
			Room room = roomRepository.findById(roomId).orElseThrow(
					() -> new RoomNotFoundException(roomId));
			
			rs.add(room);
			
			System.out.println("Room: " + roomId);
			System.out.println("CheckIn: " + requestDto.getCheckInDate());
			System.out.println("CheckOut: " + requestDto.getCheckOutDate());
			
			ReservationRoom conflict =
				    reservationRoomRepository.findConflictingReservation(
				            roomId,
				            requestDto.getCheckInDate(),
				            requestDto.getCheckOutDate(),
				            ReservationStatus.CANCELLED)
				    .orElse(null);
			
			System.out.println("Conflict found: " + conflict);

				if (conflict != null) {
				    throw new ReservationConflictException(
				            roomId,
				            conflict.getReservation().getCheckInDate(),
				            conflict.getReservation().getCheckOutDate());
				}
			
				totalAmount += totalSum(
					requestDto.getCheckInDate(), requestDto.getCheckOutDate(), room);
			
			ReservationRoom rr = new ReservationRoom();
			rr.setReservation(res);
			rr.setRoom(room);
			
			rrsOld.add(rr);
		}
		
		exceededRoomCapacity(gps.size() + 1, rs);
		
		res.setReservationRoom(rrsOld);
		res.setDepositAmount(totalAmount * 0.5);
		res.setTotalAmount(totalAmount);
		Reservation saved = reservationRepository.save(res);
		
		GetReservationResponseDTO dto = new GetReservationResponseDTO();
		
		
		List<ReservationRoom> rrsNew = saved.getReservationRoom();
		List<RoomReservationResponseDTO> rrrDTOs  = new ArrayList<>();
		for(var r : rrsNew) {
			RoomReservationResponseDTO rrrDto = new RoomReservationResponseDTO();
			
			Room room = r.getRoom();
			
			rrrDto.setId(room.getId());
			rrrDto.setTypeName(room.getRoomType().getTypeName());
			rrrDto.setRoomNumber(room.getRoomNumber());
			rrrDto.setFloor(room.getFloor());
			
			rrrDTOs.add(rrrDto);
		}
		
		
		dto.setId(saved.getId());
		dto.setStatus(saved.getStatus());
		dto.setGuestId(saved.getGuest().getId());
		dto.setGuestFullName(saved.getGuest().toStringFullName());
		dto.setGuestPhoneNumber(saved.getGuest().getPhoneNumber());
		dto.setGuestPerson(saved.getGuestPersons().size());
		dto.setRooms(rrrDTOs);
		dto.setCheckInDate(saved.getCheckInDate());
		dto.setCheckOutDate(saved.getCheckOutDate());
		dto.setPaymentMethod(saved.getPaymentMethod());
		dto.setDepositAmount(saved.getDepositAmount());
		dto.setTotalAmount(saved.getTotalAmount());
		
		return dto;
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
		 
	 	res.setStatus(ReservationStatus.CHECKED_IN);
	 	
	 	List<Room> rooms = new ArrayList<>();

	 	for (ReservationRoom rr : rrs) {
	 	    rooms.add(rr.getRoom());
	 	}
	 	
	 	for(var room : rooms) {
	 		room.setRoomStatus(RoomStatus.OCCUPIED);
	 		roomRepository.save(room);
	 	}
	 	
	 	
	 	reservationRepository.save(res);
	}
	
	public void chackOutReservation(Long id) {
		 Reservation res = reservationRepository.findById(id).orElseThrow(
				 () -> new ReservationNotFoundException(id));
		 
		 List<ReservationRoom> rrs = reservationRoomRepository.findByReservationId(id);
		 
	 	res.setStatus(ReservationStatus.CHECKED_OUT);
	 	
	 	List<Room> rooms = new ArrayList<>();

	 	for (ReservationRoom rr : rrs) {
	 	    rooms.add(rr.getRoom());
	 	}
	 	
	 	for(var room : rooms) {
	 		room.setRoomStatus(RoomStatus.CLEANING);
	 		roomRepository.save(room);
	 	}
	 	
	 	
	 	reservationRepository.save(res);
	}
	
	public GuestType isLegal(LocalDate birthDate) {
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
	
	public Double totalSum(LocalDate checkIn, LocalDate checkOut, Room room) {
		double total = 0;
		
		Period period = Period.between(checkIn, checkOut); 
		
		total = room.getRoomType().getPricePerNight() * period.getDays();
		
		return total;
	}
}
