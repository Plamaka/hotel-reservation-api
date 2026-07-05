package com.plamaka.hotel_reservation_api.exception;

public class RoomNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RoomNotFoundException(Long id){
        super("Room not found with id: " + id);
    }
}
