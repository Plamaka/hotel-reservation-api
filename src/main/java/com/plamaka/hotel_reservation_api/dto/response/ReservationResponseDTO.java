package com.plamaka.hotel_reservation_api.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.plamaka.hotel_reservation_api.enums.PaymentMethod;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;

public class ReservationResponseDTO {
	
	private Long Id;
	
	private ReservationStatus status;
	
	private GuestResponseDTO guest;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private List<RoomReservationResponseDTO> rooms;
	
	private List<GuestPersonResponseDTO> guestPersons;
	
	private PaymentMethod paymentMethod;
	
	private Double depositAmount;
	
	private Double totalAmount;

	public GuestResponseDTO getGuest() {
		return guest;
	}

	public void setGuest(GuestResponseDTO guest) {
		this.guest = guest;
	}

	public List<GuestPersonResponseDTO> getGuestPersons() {
		return guestPersons;
	}

	public void setGuestPersons(List<GuestPersonResponseDTO> guestPersons) {
		this.guestPersons = guestPersons;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
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

	public List<RoomReservationResponseDTO> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomReservationResponseDTO> rooms) {
		this.rooms = rooms;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	
}
