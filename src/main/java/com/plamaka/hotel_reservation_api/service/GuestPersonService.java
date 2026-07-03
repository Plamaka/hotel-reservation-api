package com.plamaka.hotel_reservation_api.service;

import org.springframework.stereotype.Service;

import com.plamaka.hotel_reservation_api.dto.request.GuestPersonRequeastDTO;
import com.plamaka.hotel_reservation_api.dto.response.GuestPersonResponseDTO;
import com.plamaka.hotel_reservation_api.entity.GuestPerson;
import com.plamaka.hotel_reservation_api.entity.Reservation;
import com.plamaka.hotel_reservation_api.repository.GuestPersonRepository;
import com.plamaka.hotel_reservation_api.repository.ReservationRepository;

@Service
public class GuestPersonService {
	
	private final GuestPersonRepository guestPersonRepository;
	private final ReservationRepository reservationRepository;
	
	public GuestPersonService(GuestPersonRepository guestPersonRepository, ReservationRepository reservationRepository) {
		this.guestPersonRepository = guestPersonRepository;
		this.reservationRepository = reservationRepository;
	}
	
	public GuestPersonResponseDTO createPersonGuest(GuestPersonRequeastDTO requestDto) {
		
		Reservation reservation = reservationRepository.findById(requestDto.getReservationId()).orElseThrow();
		
		GuestPerson person = new GuestPerson(
				requestDto.getFirstName(),
				requestDto.getLastName(),
				requestDto.getBirthDate(),
				requestDto.getGuestType());
		
		person.setReservation(reservation);
		
		GuestPerson saved = guestPersonRepository.save(person);
		
		GuestPersonResponseDTO response = new GuestPersonResponseDTO();
		
		response.setFullName(saved.toStringFullName());
		response.setGuestType(saved.getGuestType());
		
		return response;	
	}
	
	public GuestPersonResponseDTO updatePersonGuest(Long id, GuestPersonRequeastDTO requestDto) {
		
		GuestPerson person = guestPersonRepository.findById(id).orElseThrow();
		
		person.setFirstName(requestDto.getFirstName());
		person.setLastName(requestDto.getLastName());
		person.setBirthDate(requestDto.getBirthDate());
		person.setGuestType(requestDto.getGuestType());
		
		GuestPerson saved = guestPersonRepository.save(person);
		
		GuestPersonResponseDTO response = new GuestPersonResponseDTO();
		
		response.setFullName(saved.toStringFullName());
		response.setGuestType(saved.getGuestType());
		
		return response;	
	}
	
	public void removePersonGuestFromReservation(Long personId,Long reservationId) {
		
		
		GuestPerson person = guestPersonRepository.findById(personId).orElseThrow();
		
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
		
		
		reservation.getGuestPersons().remove(person);
		
		reservationRepository.save(reservation);
		
		
	}
	
}
