package com.plamaka.hotel_reservation_api.dto.response;

import com.plamaka.hotel_reservation_api.entity.Room;

public class RoomReservationResponseDTO {
	private Long id;

	private String typeName;
	
	private String roomNumber;
	
	private Integer floor;
	
	public RoomReservationResponseDTO(Room room) {
		this.id = room.getId();
		this.typeName = room.getRoomType().getTypeName();
		this.roomNumber = room.getRoomNumber();
		this.floor = room.getFloor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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


}
