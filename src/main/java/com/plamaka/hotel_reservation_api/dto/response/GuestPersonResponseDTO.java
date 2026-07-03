package com.plamaka.hotel_reservation_api.dto.response;

import com.plamaka.hotel_reservation_api.enums.GuestType;

public class GuestPersonResponseDTO {
	
	private Long id;
	
	private String fullName;
	
    private GuestType guestType;
	
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

	public GuestType getGuestType() {
		return guestType;
	}

	public void setGuestType(GuestType guestType) {
		this.guestType = guestType;
	}
}
