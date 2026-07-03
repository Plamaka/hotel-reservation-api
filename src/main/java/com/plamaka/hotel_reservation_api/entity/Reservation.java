package com.plamaka.hotel_reservation_api.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "Reservations")
public class Reservation {
//	id
//	checkInDate
//	checkOutDate
//	guest
//	adults
//	kids
//	paymentMethod
//	depositAmount
	
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
	
	private Double depositAmount;
	
	public Reservation() {
	}

	public Reservation(ReservationStatus status, LocalDate checkInDate,
			LocalDate checkOutDate, PaymentMethod paymentMethod,
			Double depositAmount) {
		super();
		this.status = status;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.paymentMethod = paymentMethod;
		this.depositAmount = depositAmount;
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

	public Double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public List<ReservationRoom> getReservationRoom() {
		return reservationRoom;
	}

	public void setReservationRoom(List<ReservationRoom> reservationRoom) {
		this.reservationRoom = reservationRoom;
	}
	
}
