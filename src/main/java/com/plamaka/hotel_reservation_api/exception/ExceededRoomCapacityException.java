package com.plamaka.hotel_reservation_api.exception;

public class ExceededRoomCapacityException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExceededRoomCapacityException(Integer number) {
		super("Exceeded rooms capacity By: " + number);
	}
}
