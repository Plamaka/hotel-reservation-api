package com.plamaka.hotel_reservation_api.dto.response;

import com.plamaka.hotel_reservation_api.entity.Guest;

public class GuestResponseDTO {

	public GuestResponseDTO() {
	}

	public GuestResponseDTO(Guest guest) {
		this.id = guest.getId();
		this.fullName = guest.getFullName();
		this.email = guest.getEmail();
	}

	private Long id;
	
	private String fullName;
	
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
