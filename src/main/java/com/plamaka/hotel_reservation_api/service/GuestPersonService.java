package com.plamaka.hotel_reservation_api.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.plamaka.hotel_reservation_api.dto.request.GuestPersonRequeastDTO;
import com.plamaka.hotel_reservation_api.dto.response.GuestPersonResponseDTO;
import com.plamaka.hotel_reservation_api.entity.GuestPerson;
import com.plamaka.hotel_reservation_api.entity.Reservation;
import com.plamaka.hotel_reservation_api.enums.GuestType;
import com.plamaka.hotel_reservation_api.exception.GuestPersonNotFoundException;
import com.plamaka.hotel_reservation_api.exception.ReservationNotFoundException;
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
		
		Reservation reservation = reservationRepository.findById(requestDto.getReservationId()).orElseThrow(
				() -> new ReservationNotFoundException(requestDto.getReservationId()));
		
		GuestPerson person = new GuestPerson(requestDto);
		person.setGuestType(isLegal(requestDto.getBirthDate()));
		person.setReservation(reservation);
		
		GuestPerson saved = guestPersonRepository.save(person);
		return new GuestPersonResponseDTO(saved);
	}
	
	public GuestPersonResponseDTO updatePersonGuest(Long id, GuestPersonRequeastDTO requestDto) {
		
		GuestPerson person = guestPersonRepository.findById(id).orElseThrow(
				() -> new GuestPersonNotFoundException(id));
		
		setGuestPersonFromDTO(person,requestDto);		
		GuestPerson saved = guestPersonRepository.save(person);	
		
		return new GuestPersonResponseDTO(saved);
	}
	
	public void removePersonGuest(Long personId) {
		GuestPerson person = guestPersonRepository.findById(personId).orElseThrow(
				() -> new GuestPersonNotFoundException(personId));
		
		guestPersonRepository.delete(person);
	}
	
	public static void setGuestPersonFromDTO(GuestPerson person, GuestPersonRequeastDTO requestDto) {
		person.setFirstName(requestDto.getFirstName());
		person.setLastName(requestDto.getLastName());
		person.setBirthDate(requestDto.getBirthDate());
		person.setGuestType(isLegal(requestDto.getBirthDate()));
	}
	
	public static GuestType isLegal(LocalDate birthDate) {
		Period period = Period.between(birthDate, LocalDate.now());
		
		if(period.getYears() >= 18) {
			return GuestType.ADULT;
		}
		else {
			return GuestType.CHILD;
		}
	}
	
}
