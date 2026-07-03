package com.plamaka.hotel_reservation_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plamaka.hotel_reservation_api.entity.GuestPerson;

@Repository
public interface GuestPersonRepository extends JpaRepository<GuestPerson,Long> {

	 List<GuestPerson> findByReservationId(Long reservationId);
}
