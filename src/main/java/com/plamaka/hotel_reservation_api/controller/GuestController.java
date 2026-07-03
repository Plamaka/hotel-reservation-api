package com.plamaka.hotel_reservation_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plamaka.hotel_reservation_api.dto.request.GuestRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.GuestResponseDTO;
import com.plamaka.hotel_reservation_api.service.GuestService;

@RestController
public class GuestController {
//	GET /guests/{id}

	private final GuestService guestService;
	
	public GuestController(GuestService guestService) {
		this.guestService = guestService;
	}
	
	@GetMapping( path = "/guests")
	public List<GuestResponseDTO> getAllGuest(){
		return guestService.getAll();
	}
	
	@PostMapping(path = "/guests")
	public GuestResponseDTO addGuest(@RequestBody GuestRequestDTO requestDto) {
		return guestService.createGuest(requestDto);
	}
	
	@PutMapping(path = "/guests")
	public GuestResponseDTO updateGuest(@PathVariable Long id,@RequestBody GuestRequestDTO requestDto) {
		return guestService.updateGuest(id,requestDto);
	}
	
	@DeleteMapping(path = "/guests")
	public void deleteGuest(@PathVariable Long id) {
		guestService.deleteGuest(id);
	}

}
