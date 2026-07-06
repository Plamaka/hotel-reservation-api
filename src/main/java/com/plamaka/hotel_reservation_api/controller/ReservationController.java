package com.plamaka.hotel_reservation_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.plamaka.hotel_reservation_api.dto.request.ReservationRequestDTO;
import com.plamaka.hotel_reservation_api.dto.request.UpdateReservationRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.GetReservationResponseDTO;
import com.plamaka.hotel_reservation_api.dto.response.ReservationResponseDTO;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;
import com.plamaka.hotel_reservation_api.service.ReservationService;

import jakarta.validation.Valid;

@RestController
public class ReservationController {
	
	private final ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping(path = "/reservations")
	public List<GetReservationResponseDTO> getPendingReservations(){
		return reservationService.getAllPendingReservation();
	}
	
	@GetMapping(path = "/reservations/reservation-status/{status}")
	public List<GetReservationResponseDTO> getByStatus(@PathVariable ReservationStatus status){
		return reservationService.getReservationByStatus(status);
	}
	
	@GetMapping(path = "/reservations/{id}")
	public ReservationResponseDTO getbyId(@PathVariable Long id){
		return reservationService.getReservationById(id);
	}
	
	@PostMapping(path = "/reservations")
	public ReservationResponseDTO getbyId(@Valid @RequestBody ReservationRequestDTO request){
		return reservationService.createReservation(request);
	}
	
	@PutMapping(path = "/reservations/{id}")
	public GetReservationResponseDTO updateReservation(@PathVariable Long id,@Valid @RequestBody UpdateReservationRequestDTO request){
		return reservationService.updateReservation(id, request);
	}

	
	@PatchMapping(path = "/reservations/{id}/check-in")
	public void makeReservationCheckIn(@PathVariable Long id){
		reservationService.chackInReservation(id);
	}
	
	@PatchMapping(path = "/reservations/{id}/check-out")
	public void makeReservationCheckOut(@PathVariable Long id){
		reservationService.chackOutReservation(id);
	}
	
	@PatchMapping(path = "/reservations/{id}/cansel")
	public void makeReservationCanseled(@PathVariable Long id){
		reservationService.cancelReservation(id);
	}
}
