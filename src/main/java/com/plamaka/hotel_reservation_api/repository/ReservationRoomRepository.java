package com.plamaka.hotel_reservation_api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.plamaka.hotel_reservation_api.entity.ReservationRoom;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;

@Repository
public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {

	@Query("""
		    SELECT COUNT(rr)
		    FROM ReservationRoom rr
		    WHERE rr.room.id = :roomId
		      AND rr.reservation.checkInDate < :checkOut
		      AND rr.reservation.checkOutDate > :checkIn
		      AND rr.reservation.status <> :status
		    """)
		long countConflictingReservations(
		        @Param("roomId") Long roomId,
		        @Param("checkOut") LocalDate checkOut,
		        @Param("checkIn") LocalDate checkIn,
		        @Param("status") ReservationStatus status);
	
	List<ReservationRoom> findByReservationId(Long reservationId);
	
	
}
