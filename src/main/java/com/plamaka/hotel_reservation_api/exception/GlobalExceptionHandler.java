package com.plamaka.hotel_reservation_api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(
	        RuntimeException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.BAD_REQUEST.value());
	    error.setError(HttpStatus.BAD_REQUEST.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(error);
	}
	
	@ExceptionHandler(GuestNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			GuestNotFoundException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.NOT_FOUND.value());
	    error.setError(HttpStatus.NOT_FOUND.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(GuestPersonNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			GuestPersonNotFoundException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.NOT_FOUND.value());
	    error.setError(HttpStatus.NOT_FOUND.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(RoomNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			RoomNotFoundException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.NOT_FOUND.value());
	    error.setError(HttpStatus.NOT_FOUND.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	//RoomHasReservationsException
	@ExceptionHandler(RoomTypeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			RoomTypeNotFoundException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.NOT_FOUND.value());
	    error.setError(HttpStatus.NOT_FOUND.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ReservationNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			ReservationNotFoundException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.NOT_FOUND.value());
	    error.setError(HttpStatus.NOT_FOUND.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(RoomHasReservationsException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			RoomHasReservationsException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.CONFLICT.value());
	    error.setError(HttpStatus.CONFLICT.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(ReservationConflictException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			ReservationConflictException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.CONFLICT.value());
	    error.setError(HttpStatus.CONFLICT.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(ExceededRoomCapacityException.class)
	public ResponseEntity<ErrorResponse> handleRoomNotFound(
			ExceededRoomCapacityException ex,
	        HttpServletRequest request){

	    ErrorResponse error = new ErrorResponse();

	    error.setTimestamp(LocalDateTime.now());
	    error.setStatus(HttpStatus.BAD_REQUEST.value());
	    error.setError(HttpStatus.BAD_REQUEST.name());
	    error.setMessage(ex.getMessage());
	    error.setPath(request.getRequestURI());
	    
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
