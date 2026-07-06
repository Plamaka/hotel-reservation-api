package com.plamaka.hotel_reservation_api.dto.request;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.plamaka.hotel_reservation_api.enums.PaymentMethod;
import com.plamaka.hotel_reservation_api.validation.CheckOutAfterCheckIn;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@CheckOutAfterCheckIn
public class ReservationRequestDTO {
	
	@NotNull
	private Long guestId;
	
	@FutureOrPresent
	private LocalDate checkInDate;
	
	@Future
	private LocalDate checkOutDate;
	
	@NotNull
	private List<Long> roomIds;
	
	@Valid
	private List<GuestPersonReservationRequeastDTO> guestPersons = new ArrayList<>();
	
	@NotNull
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

	public List<GuestPersonReservationRequeastDTO> getGuestPersons() {
		return guestPersons;
	}

	public void setGuestPersons(List<GuestPersonReservationRequeastDTO> guestPersons) {
		this.guestPersons = guestPersons;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	

}
