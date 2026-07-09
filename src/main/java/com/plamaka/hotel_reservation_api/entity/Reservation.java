package com.plamaka.hotel_reservation_api.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.plamaka.hotel_reservation_api.dto.request.ReservationRequestDTO;
import com.plamaka.hotel_reservation_api.enums.PaymentMethod;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@OneToMany(mappedBy = "reservation")
	private List<ReservationRoom> reservationRoom;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	
	@OneToMany(mappedBy = "reservation")
	private List<GuestPerson> guestPersons;
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	private BigDecimal depositAmount;
	
	private BigDecimal totalAmount;
	

	public Reservation() {
	}

	public Reservation(ReservationRequestDTO requestDto, Guest guest) {
		super();
		this.guest = guest;
		this.status = ReservationStatus.PENDING;
		this.checkInDate = requestDto.getCheckInDate();
		this.checkOutDate = requestDto.getCheckOutDate();
		this.paymentMethod = requestDto.getPaymentMethod();
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

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public List<GuestPerson> getGuestPersons() {
		return guestPersons;
	}

	public void setGuestPersons(List<GuestPerson> guestPersons) {
		this.guestPersons = guestPersons;
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

	public List<ReservationRoom> getReservationRoom() {
		return reservationRoom;
	}

	public void setReservationRoom(List<ReservationRoom> reservationRoom) {
		this.reservationRoom = reservationRoom;
	}
	
}
