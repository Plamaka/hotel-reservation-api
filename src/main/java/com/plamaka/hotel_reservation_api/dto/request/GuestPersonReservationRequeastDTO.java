package com.plamaka.hotel_reservation_api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class GuestPersonReservationRequeastDTO {
	@Size(min=2,max = 25)
	private String firstName;

	@Size(min=2,max = 25)
    private String lastName;

	@PastOrPresent
    private LocalDate birthDate;

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
	
	
}
