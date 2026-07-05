package com.plamaka.hotel_reservation_api.exception;

import java.time.LocalDate;

public class ReservationConflictException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	  public ReservationConflictException(
	            Long roomId,
	            LocalDate occupiedFrom,
	            LocalDate occupiedTo) {

	        super(
	            "Room " + roomId +
	            " is already reserved from " +
	            occupiedFrom +
	            " to " +
	            occupiedTo
	        );
	  }
}
