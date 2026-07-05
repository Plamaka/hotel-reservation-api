package com.plamaka.hotel_reservation_api.exception;

public class GuestNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GuestNotFoundException(Long id){
        super("Guest not found with id: " + id);
    }
}
