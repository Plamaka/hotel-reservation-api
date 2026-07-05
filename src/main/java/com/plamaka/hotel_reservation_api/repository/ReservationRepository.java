package com.plamaka.hotel_reservation_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plamaka.hotel_reservation_api.entity.Reservation;
import com.plamaka.hotel_reservation_api.enums.ReservationStatus;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	
	List<Reservation> findByStatus(ReservationStatus status);
}
