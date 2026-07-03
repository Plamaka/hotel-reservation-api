package com.plamaka.hotel_reservation_api.dto.request;

import java.time.LocalDate;

import com.plamaka.hotel_reservation_api.enums.GuestType;

public class GuestPersonRequeastDTO {
	private String firstName;

    private String lastName;

    private LocalDate birthDate;
    
    private GuestType guestType;
    
    private Long reservationId;

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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
    
    
}
