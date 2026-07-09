package com.plamaka.hotel_reservation_api.dto.response;

import java.math.BigDecimal;

import com.plamaka.hotel_reservation_api.entity.Room;
import com.plamaka.hotel_reservation_api.enums.RoomStatus;

public class RoomResponseDTO {
	
	private Long id;
	
	private String roomNumber;
	
	private Integer floor;

	private RoomStatus roomStatus;

	private String typeName;
	
	private Integer capacity;
	
	private BigDecimal pricePerNight;
	
	private Boolean balcony;
	
	private Boolean petsAllowed;
	
	public RoomResponseDTO(Room room) {
		super();
		this.id = room.getId();
		this.roomNumber = room.getRoomNumber();
		this.floor = room.getFloor();
		this.roomStatus = room.getRoomStatus();
		this.typeName = room.getRoomType().getTypeName();
		this.capacity = room.getRoomType().getCapacity();
		this.pricePerNight = room.getRoomType().getPricePerNight();
		this.balcony = room.getBalcony();
		this.petsAllowed = room.getRoomType().getPetsAllowed();
	}

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

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
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
