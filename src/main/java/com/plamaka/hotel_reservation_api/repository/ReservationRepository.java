package com.plamaka.hotel_reservation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plamaka.hotel_reservation_api.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	
}
