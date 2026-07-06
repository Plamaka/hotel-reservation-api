package com.plamaka.hotel_reservation_api.exception;

public class GuestIsDeletedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GuestIsDeletedException(Long id) {
		super("Guest is deleted with id: "+ id);
	}

}
