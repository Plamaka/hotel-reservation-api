package com.plamaka.hotel_reservation_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plamaka.hotel_reservation_api.dto.request.RoomRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.RoomResponseDTO;
import com.plamaka.hotel_reservation_api.enums.RoomStatus;
import com.plamaka.hotel_reservation_api.service.RoomService;

@RestController
public class RoomController {
	
	public final RoomService roomService;
	
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@GetMapping(path = "/rooms")
	public List<RoomResponseDTO> getAvableRooms(){
		return roomService.findAvailableRooms();
	}
	
	@GetMapping(path = "/rooms/room-status")
	public List<RoomResponseDTO> getRoomsByStatus(@PathVariable RoomStatus status){
		return roomService.findRoomsByStatus(status);
	}
	
	@GetMapping(path = "/rooms/floor")
	public List<RoomResponseDTO> getRoomsOnFloor(@PathVariable Integer floor){
		return roomService.findRoomsByFloor( floor);
	}
	
	@GetMapping(path = "/rooms/room-type")
	public List<RoomResponseDTO> getRoomsByType(@PathVariable String typeName){
		return roomService.findRoomsByRoomType(typeName);
	}
	
	@PostMapping(path = "/rooms")
	public RoomResponseDTO postRoom(@RequestBody RoomRequestDTO request){
		return roomService.addRoom(request);
	}
	
	@PutMapping(path = "/rooms/{id}")
	public RoomResponseDTO updateRoom(@PathVariable Long id, @RequestBody RoomRequestDTO request){
		return roomService.updateRoom(id, request);
	}
	
	@DeleteMapping(path = "/rooms")
	public void deleteRoom(@PathVariable Long id){
		roomService.deleteRoom(id);
	}
	
}
