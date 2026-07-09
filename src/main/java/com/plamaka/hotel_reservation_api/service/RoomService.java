package com.plamaka.hotel_reservation_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.plamaka.hotel_reservation_api.dto.request.RoomRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.RoomResponseDTO;
import com.plamaka.hotel_reservation_api.entity.Room;
import com.plamaka.hotel_reservation_api.entity.RoomType;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;
import com.plamaka.hotel_reservation_api.enums.RoomStatus;
import com.plamaka.hotel_reservation_api.exception.RoomHasReservationsException;
import com.plamaka.hotel_reservation_api.exception.RoomNotFoundException;
import com.plamaka.hotel_reservation_api.exception.RoomTypeNotFoundException;
import com.plamaka.hotel_reservation_api.repository.ReservationRoomRepository;
import com.plamaka.hotel_reservation_api.repository.RoomRepository;
import com.plamaka.hotel_reservation_api.repository.RoomTypeRepository;

@Service
public class RoomService {

	private final RoomRepository roomRepository;
	private final RoomTypeRepository roomTypeRepository;
	private final ReservationRoomRepository reservationRoomRepository;
	
	public RoomService(RoomRepository roomRepository,
			RoomTypeRepository roomTypeRepository, 
			ReservationRoomRepository reservationRoomRepository) {
		this.roomRepository = roomRepository;
		this.roomTypeRepository = roomTypeRepository;
		this.reservationRoomRepository = reservationRoomRepository;
	}
	
	public List<RoomResponseDTO> findAvailableRooms(){
		
		List<Room> rooms = roomRepository.findByRoomStatus(RoomStatus.AVAILABLE);
		
		List<RoomResponseDTO> respons = new ArrayList<>();
		populateRoomToDTO(rooms, respons);
		
		return respons;
	}
	
	public List<RoomResponseDTO> findRoomsByStatus(RoomStatus status){
		
		List<Room> rooms = roomRepository.findByRoomStatus(status);
		
		List<RoomResponseDTO> respons = new ArrayList<>();
		populateRoomToDTO(rooms, respons);
		
		return respons;
	}
	
	public List<RoomResponseDTO> findRoomsByFloor(Integer floor){
		
		List<Room> rooms = roomRepository.findByFloor(floor);
		
		List<RoomResponseDTO> respons = new ArrayList<>();
		populateRoomToDTO(rooms, respons);
		
		return respons;	
	}
	
	public List<RoomResponseDTO> findRoomsByRoomType(String type){
		
		List<Room> rooms = roomRepository.findByRoomTypeTypeName(type);
		
		List<RoomResponseDTO> respons = new ArrayList<>();
		populateRoomToDTO(rooms, respons);
		
		return respons;	
	}
	
	public RoomResponseDTO addRoom(RoomRequestDTO requestDto) {
		
		RoomType type = roomTypeRepository.findById(requestDto.getRoomTypeId()).orElseThrow(
				() -> new RoomNotFoundException(requestDto.getRoomTypeId()));
		
		Room room = new Room(requestDto);	
		room.setRoomType(type);	
		Room saved = roomRepository.save(room);
		
		return new RoomResponseDTO(saved);
	}
	
	public RoomResponseDTO updateRoom(Long id, RoomRequestDTO requestDto) {
		
		Room room = roomRepository.findById(id).orElseThrow(
				() -> new RoomNotFoundException(requestDto.getRoomTypeId()));
		
		RoomType type = roomTypeRepository.findById(requestDto.getRoomTypeId()).orElseThrow(
				() -> new RoomTypeNotFoundException(requestDto.getRoomTypeId()));
		
		setRoomFromDTO(room, requestDto, type);	
		Room saved = roomRepository.save(room);
	
		return new RoomResponseDTO(saved);
	}
	
	public void deleteRoom(Long id) {
		
		Room room = roomRepository.findById(id).orElseThrow(
				() -> new RoomNotFoundException(id));
		
		if (reservationRoomRepository.existsByRoomIdAndReservationStatusIn(
		        room.getId(),
		        List.of(
		                ReservationStatus.CONFIRMED,
		                ReservationStatus.CHECKED_IN))) {

		    throw new RoomHasReservationsException(room.getId());
		}
		
		roomRepository.delete(room);		
	}
	

	public static void populateRoomToDTO(List<Room> rooms, List<RoomResponseDTO> respons) {
		for(var room : rooms) {
			RoomResponseDTO dto = new RoomResponseDTO(room);
			respons.add(dto);
		}
	}
	
	public static void setRoomFromDTO(Room room, RoomRequestDTO requestDto, RoomType type) {
		room.setRoomNumber(requestDto.getRoomNumber());
		room.setFloor(requestDto.getFloor());
		room.setRoomStatus(requestDto.getRoomstatus());
		room.setBalcony(requestDto.getBalcony());
		room.setRoomType(type);
	}
	
}
