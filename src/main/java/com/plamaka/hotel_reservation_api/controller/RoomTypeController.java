package com.plamaka.hotel_reservation_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plamaka.hotel_reservation_api.dto.request.RoomTypeRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.RoomTypeResponseDTO;
import com.plamaka.hotel_reservation_api.service.RoomTypeService;

@RestController
public class RoomTypeController {
	
	private final RoomTypeService roomTypeService;
	
	public RoomTypeController(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}
	
	@GetMapping(path = "/room-type")
	public List<RoomTypeResponseDTO> getAll(){
		return roomTypeService.getAllRoomTypes();
	}
	
	@PostMapping(path = "/room-type")
	public RoomTypeResponseDTO createRoomType(@RequestBody RoomTypeRequestDTO request) {
		return roomTypeService.createRoomType(request);
	}
	
	@PutMapping(path = "/room-type/{id}")
	public RoomTypeResponseDTO updateRoomType(@PathVariable Long id ,@RequestBody RoomTypeRequestDTO request) {
		return roomTypeService.updateRoomType(id, request);
	}
}