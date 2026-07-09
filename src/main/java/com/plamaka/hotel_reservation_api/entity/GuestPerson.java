package com.plamaka.hotel_reservation_api.entity;

import java.time.LocalDate;

import com.plamaka.hotel_reservation_api.dto.request.GuestPersonRequeastDTO;
import com.plamaka.hotel_reservation_api.dto.request.GuestPersonReservationRequeastDTO;
import com.plamaka.hotel_reservation_api.enums.GuestType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Guest_Persons")
public class GuestPerson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;
    
    @Enumerated(EnumType.STRING)
    private GuestType guestType;

    
    @ManyToOne()
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public GuestPerson() {
    }
    
	public GuestPerson(GuestPersonRequeastDTO requestDto) {
		super();
		this.firstName = requestDto.getFirstName();
		this.lastName = requestDto.getLastName();
		this.birthDate = requestDto.getBirthDate();
	}

	public GuestPerson(GuestPersonReservationRequeastDTO requestDto, Reservation res) {
		super();
		this.firstName = requestDto.getFirstName();
		this.lastName = requestDto.getLastName();
		this.birthDate = requestDto.getBirthDate();
		this.reservation = res;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public GuestType getGuestType() {
		return guestType;
	}

	public void setGuestType(GuestType guestType) {
		this.guestType = guestType;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}    
	
	public String toStringFullName() {
		return firstName + " " + lastName;
	}	
}
