package com.plamaka.hotel_reservation_api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.plamaka.hotel_reservation_api.entity.ReservationRoom;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;

@Repository
public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {

	@Query("""
		    SELECT rr
		    FROM ReservationRoom rr
		    WHERE rr.room.id = :roomId
		      AND rr.reservation.status <> 'CANCELED'
		      AND rr.reservation.checkOutDate >= :timeNow
		      AND rr.reservation.checkInDate < :checkOut
		      AND rr.reservation.checkOutDate > :checkIn
		    """)
	Optional<ReservationRoom> findConflictingReservation(
			@Param("roomId") Long roomId,
			@Param("status") ReservationStatus status,
			@Param("timeNow") LocalDate timeNow,
			@Param("checkOut") LocalDate checkOut,
			@Param("checkIn") LocalDate checkIn);

	List<ReservationRoom> findByReservationId(Long reservationId);
	
	boolean existsByRoomIdAndReservationStatusIn(
	        Long roomId,
	        List<ReservationStatus> statuses);
}
