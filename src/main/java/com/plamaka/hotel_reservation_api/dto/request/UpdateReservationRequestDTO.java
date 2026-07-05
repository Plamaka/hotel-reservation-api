package com.plamaka.hotel_reservation_api.dto.request;

import java.time.LocalDate;
import java.util.List;

public class UpdateReservationRequestDTO {
	private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private List<Long> roomIds;

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public List<Long> getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(List<Long> roomIds) {
		this.roomIds = roomIds;
	}
    
}
