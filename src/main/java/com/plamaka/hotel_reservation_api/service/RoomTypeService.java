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
		 populateRoomTypeToDTO(roomTypes, response);
		 return response;
	}
	
	public RoomTypeResponseDTO createRoomType(RoomTypeRequestDTO requestDto) {
		RoomType type = new RoomType(requestDto);			
		RoomType saved = roomTypeRepository.save(type); 	
		
		return new RoomTypeResponseDTO(saved);		
	}
	
	public RoomTypeResponseDTO updateRoomType(Long id, RoomTypeRequestDTO requestDto) {
		RoomType type = roomTypeRepository.findById(id).orElseThrow(
				() -> new RoomTypeNotFoundException(id));
			
		setRoomTypeFromDTO(type, requestDto);
		RoomType saved = roomTypeRepository.save(type);
	
		return new RoomTypeResponseDTO(saved);
	}
	
	public static void populateRoomTypeToDTO(List<RoomType> roomTypes, List<RoomTypeResponseDTO> response) {
		for(var type : roomTypes) {
			 RoomTypeResponseDTO dto = new RoomTypeResponseDTO(type);
			 response.add(dto);
		 }		
	}
	
	public static void setRoomTypeFromDTO(RoomType type, RoomTypeRequestDTO requestDto) {
		type.setTypeName(requestDto.getTypeName());
		type.setCapacity(requestDto.getCapacity());
		type.setPricePerNight(requestDto.getPricePerNight());
		type.setPetsAllowed(requestDto.getPetsAllowed());
	}
}
