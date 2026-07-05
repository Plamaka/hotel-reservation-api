package com.plamaka.hotel_reservation_api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plamaka.hotel_reservation_api.dto.request.GuestPersonRequeastDTO;
import com.plamaka.hotel_reservation_api.dto.response.GuestPersonResponseDTO;
import com.plamaka.hotel_reservation_api.service.GuestPersonService;

@RestController
public class GuestPersonController {
	
	private final GuestPersonService guestPersonService;
	
	public GuestPersonController(GuestPersonService guestPersonService) {
		this.guestPersonService = guestPersonService;
	}
	
	@PostMapping(path = "/guest-persons")
	public GuestPersonResponseDTO addPersonGuest(@RequestBody GuestPersonRequeastDTO request) {
		return guestPersonService.createPersonGuest(request);
	}
	
	@PutMapping(path = "/guest-persons/{id}")
	public GuestPersonResponseDTO addPersonGuest(@PathVariable Long id, @RequestBody GuestPersonRequeastDTO request ) {
		return guestPersonService.updatePersonGuest(id, request);
	}
	
	@DeleteMapping(path = "/guest-persons/{id}")
	public void deleteGuestPerson(@PathVariable Long id) {
		guestPersonService.removePersonGuest(id);
	}
}
