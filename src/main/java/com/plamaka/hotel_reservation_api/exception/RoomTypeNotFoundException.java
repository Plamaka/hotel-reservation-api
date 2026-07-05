package com.plamaka.hotel_reservation_api.exception;

public class RoomTypeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public RoomTypeNotFoundException(Long id){
        super("RoomType not found with id: " + id);
    }
}
