package com.plamaka.hotel_reservation_api.dto.response;

import com.plamaka.hotel_reservation_api.enums.RoomStatus;

public class RoomResponseDTO {
	
	private Long id;
	
	private String roomNumber;
	
	private Integer floor;

	private RoomStatus roomStatus;

	private String typeName;
	
	private Integer capacity;
	
	private Double pricePerNight;
	
	private Boolean balcony;
	
	private Boolean petsAllowed;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(Double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public Boolean getBalcony() {
		return balcony;
	}

	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}

	public Boolean getPetsAllowed() {
		return petsAllowed;
	}

	public void setPetsAllowed(Boolean petsAllowed) {
		this.petsAllowed = petsAllowed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}
}
