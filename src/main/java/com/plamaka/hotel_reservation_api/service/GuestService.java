package com.plamaka.hotel_reservation_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.plamaka.hotel_reservation_api.dto.request.GuestRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.GuestResponseDTO;
import com.plamaka.hotel_reservation_api.entity.Guest;
import com.plamaka.hotel_reservation_api.exception.GuestNotFoundException;
import com.plamaka.hotel_reservation_api.repository.GuestRepository;

@Service
public class GuestService {
	
	private final GuestRepository guestRepository;
	
	public GuestService(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}
	
	public List<GuestResponseDTO> getAll(){
		List<Guest> guests = guestRepository.findAll();
		
		List<GuestResponseDTO> respons = new ArrayList<>();
		
		for(var guest : guests) {
			GuestResponseDTO dto = new GuestResponseDTO();
			
			dto.setId(guest.getId());
			dto.setFullName(guest.getFullName());
			dto.setEmail(guest.getEmail());
			
			respons.add(dto);
		}
		
		return respons;
	}
	
	public GuestResponseDTO createGuest(GuestRequestDTO requestDto) {
		Guest guest = new Guest(requestDto);
		guest.setIsDeleted(false);

		Guest saved = guestRepository.save(guest);
		return new GuestResponseDTO(saved);
	}
	
	public GuestResponseDTO updateGuest(Long id, GuestRequestDTO requestDto) {
		Guest guest = guestRepository.findById(id).orElseThrow(
				() -> new GuestNotFoundException(id));
		
		guest.setFirstName(requestDto.getFirstName());
		guest.setMiddleName(requestDto.getMiddleName());
		guest.setLastName(requestDto.getLastName());
		guest.setEmail(requestDto.getEmail());
		guest.setPhoneNumber(requestDto.getPhoneNumber());
		
		Guest saved = guestRepository.save(guest);
		
		GuestResponseDTO response = new GuestResponseDTO();
		
		response.setId(saved.getId());
		response.setFullName(saved.getFullName());
		response.setEmail(saved.getEmail());
		
		return response;
	}
	
	public void deleteGuest(Long id) {
		Guest guest = guestRepository.findById(id).orElseThrow(
				() -> new GuestNotFoundException(id));
		
		guest.setIsDeleted(true);
		
		guestRepository.save(guest);
	}

}
