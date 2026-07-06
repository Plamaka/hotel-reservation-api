package com.plamaka.hotel_reservation_api.dto.request;

import com.plamaka.hotel_reservation_api.enums.RoomStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RoomRequestDTO {
	
	@NotBlank
	private String roomNumber;
	
	@NotNull
	private Integer floor;

	@NotNull
	private RoomStatus roomstatus;

	@NotNull
	private Long roomTypeId;
	
	@NotNull
	private Boolean balcony;

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

	public RoomStatus getRoomstatus() {
		return roomstatus;
	}

	public void setRoomstatus(RoomStatus roomstatus) {
		this.roomstatus = roomstatus;
	}

	public Long getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Long roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Boolean getBalcony() {
		return balcony;
	}

	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}
	
	
}
