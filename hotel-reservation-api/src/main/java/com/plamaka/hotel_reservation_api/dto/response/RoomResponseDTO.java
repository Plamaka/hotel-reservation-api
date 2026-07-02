package com.plamaka.hotel_reservation_api.dto.response;

import com.plamaka.hotel_reservation_api.entity.RoomStatus;

public class RoomResponseDTO {
	
	private Long id;
	
	private String roomNumber;
	
	private Integer floor;

	private RoomStatus roomstatus;

	private Long roomTypeId;

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
}
