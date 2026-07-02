package com.plamaka.hotel_reservation_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.plamaka.hotel_reservation_api.dto.response.GuestPersonResponseDTO;
import com.plamaka.hotel_reservation_api.service.GuestPersonService;

@RestController
public class GuestPersonController {
	
	private final GuestPersonService guestPersonService;
	
	public GuestPersonController(GuestPersonService guestPersonService) {
		this.guestPersonService = guestPersonService;
	}
	
	@GetMapping(path = "/guest-persons/{id}")
	public List<GuestPersonResponseDTO> getPersonsByReervation(Long id) {
		return guestPersonService.findGuestPersonsByReservationId(id);
	}
}
