package com.plamaka.hotel_reservation_api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.plamaka.hotel_reservation_api.entity.GuestPerson;
import com.plamaka.hotel_reservation_api.enums.PaymentMethod;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;

public class ReservationResponseDTO {
	
	private Long Id;
	
	private ReservationStatus status;
	
	private GuestResponseDTO guest;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private List<RoomReservationResponseDTO> rooms;
	
	private List<GuestPersonResponseDTO> guestPersons = new ArrayList<>();
	
	private PaymentMethod paymentMethod;
	
	private BigDecimal depositAmount;
	
	private BigDecimal totalAmount;

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

	public void addGuests(List<GuestPerson> list) {
		for (GuestPerson guestPerson : list) {
			guestPersons.add(new GuestPersonResponseDTO(guestPerson));
		}
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
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

	public BigDecimal getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigDecimal depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	
}
