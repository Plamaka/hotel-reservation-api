package com.plamaka.hotel_reservation_api.dto.request;

import java.time.LocalDate;
import java.util.List;

import com.plamaka.hotel_reservation_api.enums.PaymentMethod;

public class ReservationRequestDTO {
	private Long guestId;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private List<Long> roomIds;
	
	private List<GuestPersonRequeastDTO> guestPersons;
	
	private PaymentMethod paymentMethod;

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

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

	public List<GuestPersonRequeastDTO> getGuestPersons() {
		return guestPersons;
	}

	public void setGuestPersons(List<GuestPersonRequeastDTO> guestPersons) {
		this.guestPersons = guestPersons;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
