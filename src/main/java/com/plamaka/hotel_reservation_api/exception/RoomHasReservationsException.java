package com.plamaka.hotel_reservation_api.exception;

public class RoomHasReservationsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    public RoomHasReservationsException(Long roomId) {
        super("Room with id " + roomId + " cannot be deleted because it has existing reservations.");
    }
}
