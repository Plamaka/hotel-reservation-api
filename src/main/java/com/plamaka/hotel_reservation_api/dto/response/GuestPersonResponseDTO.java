package com.plamaka.hotel_reservation_api.dto.response;

import com.plamaka.hotel_reservation_api.entity.GuestType;

public class GuestPersonResponseDTO {
	
	private Integer Number;
	
	private String fullName;
	
    private GuestType guestType;
    
	public Integer getNumber() {
		return Number;
	}

	public void setNumber(Integer number) {
		Number = number;
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
