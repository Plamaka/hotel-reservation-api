package com.plamaka.hotel_reservation_api.exception;

public class GuestPersonNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GuestPersonNotFoundException(Long id){
        super("GuestPerson not found with id: " + id);
    }
}
