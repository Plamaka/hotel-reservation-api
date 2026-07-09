package com.plamaka.hotel_reservation_api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.plamaka.hotel_reservation_api.entity.Reservation;
import com.plamaka.hotel_reservation_api.enums.PaymentMethod;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;

public class GetReservationResponseDTO {
	private Long Id;
	
	private ReservationStatus status;
	
	private Long guestId;
	
	private String guestFullName;
	
	private String guestPhoneNumber;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private List<RoomReservationResponseDTO> rooms;
	
	private Integer guestPerson;
	
	private PaymentMethod paymentMethod;
	
	private BigDecimal depositAmount;
	
	private BigDecimal totalAmount;
	
	public GetReservationResponseDTO(Reservation res, List<RoomReservationResponseDTO> rrrDTOs) {
		super();
		this.Id = res.getId();
		this.status = res.getStatus();
		this.guestId = res.getGuest().getId();
		this.guestFullName = res.getGuest().getFullName();
		this.guestPhoneNumber = res.getGuest().getPhoneNumber();
		this.checkInDate = res.getCheckInDate();
		this.checkOutDate = res.getCheckOutDate();
		this.rooms = rrrDTOs;
		this.guestPerson = res.getGuestPersons().size();
		this.paymentMethod = res.getPaymentMethod();
		this.depositAmount = res.getDepositAmount();
		this.totalAmount = res.getTotalAmount();		
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

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public String getGuestFullName() {
		return guestFullName;
	}

	public void setGuestFullName(String guestFullName) {
		this.guestFullName = guestFullName;
	}

	public String getGuestPhoneNumber() {
		return guestPhoneNumber;
	}

	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
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

	public Integer getGuestPerson() {
		return guestPerson;
	}

	public void setGuestPerson(Integer guestPerson) {
		this.guestPerson = guestPerson;
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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
