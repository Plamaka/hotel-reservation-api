package com.plamaka.hotel_reservation_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.plamaka.hotel_reservation_api.dto.request.RoomTypeRequestDTO;
import com.plamaka.hotel_reservation_api.dto.response.RoomTypeResponseDTO;
import com.plamaka.hotel_reservation_api.entity.RoomType;
import com.plamaka.hotel_reservation_api.exception.RoomTypeNotFoundException;
import com.plamaka.hotel_reservation_api.repository.RoomTypeRepository;

@Service
public class RoomTypeService {
	
	private final RoomTypeRepository roomTypeRepository;
	
	public RoomTypeService(RoomTypeRepository roomTypeRepository) {
		this.roomTypeRepository = roomTypeRepository;
	}
	
	public List<RoomTypeResponseDTO> getAllRoomTypes(){
		 List<RoomType> roomTypes =  roomTypeRepository.findAll();
		
		 List<RoomTypeResponseDTO> response = new ArrayList<>();
		 
		 for(var type : roomTypes) {
			 RoomTypeResponseDTO dto = new RoomTypeResponseDTO();
			 
			 dto.setId(type.getId());
			 dto.setTypeName(type.getTypeName());
			 dto.setCapacity(type.getCapacity());
			 dto.setPricePerNight(type.getPricePerNight());
			 
			 response.add(dto);
		 }
		 
		 return response;
	}
	
	public RoomTypeResponseDTO createRoomType(RoomTypeRequestDTO requestDto) {
		RoomType type = new RoomType(
				requestDto.getTypeName(),
				requestDto.getCapacity(),
				requestDto.getPricePerNight(),
				requestDto.getPetsAllowed()
				);
		
		
		
		RoomType saved = roomTypeRepository.save(type); 
		
		RoomTypeResponseDTO dto = new RoomTypeResponseDTO();
		
		dto.setId(saved.getId());
		 dto.setTypeName(type.getTypeName());
		 dto.setCapacity(type.getCapacity());
		 dto.setPricePerNight(type.getPricePerNight());
		
		return dto;
	}
	
	public RoomTypeResponseDTO updateRoomType(Long id, RoomTypeRequestDTO requestDto) {
		RoomType type = roomTypeRepository.findById(id).orElseThrow(
				() -> new RoomTypeNotFoundException(id));
		
		type.setTypeName(requestDto.getTypeName());
		type.setCapacity(requestDto.getCapacity());
		type.setPricePerNight(requestDto.getPricePerNight());
		type.setPetsAllowed(requestDto.getPetsAllowed());
		
		RoomType saved = roomTypeRepository.save(type);
		
		RoomTypeResponseDTO dto = new RoomTypeResponseDTO();
		
		 dto.setId(saved.getId());
		 dto.setTypeName(type.getTypeName());
		 dto.setCapacity(type.getCapacity());
		 dto.setPricePerNight(type.getPricePerNight());
		
		return dto;
	}
	
}
