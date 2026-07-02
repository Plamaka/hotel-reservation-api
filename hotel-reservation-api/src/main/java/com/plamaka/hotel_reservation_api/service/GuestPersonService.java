package com.plamaka.hotel_reservation_api.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<GuestPersonResponseDTO> findGuestPersonsByReservationId(Long id){
		List<GuestPerson> guests = guestPersonRepository.findByReservationId(id);
		
		List<GuestPersonResponseDTO> response = new ArrayList<>();
		for(var guest : guests) {
			GuestPersonResponseDTO dto = new GuestPersonResponseDTO();
			
			dto.setNumber(guests.indexOf(guest) + 1);
			dto.setFullName(guest.toStringFullName());
			dto.setGuestType(guest.getGuestType());
			
			response.add(dto);
		}
		
		return response;
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
}
