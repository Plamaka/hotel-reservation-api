package com.plamaka.hotel_reservation_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plamaka.hotel_reservation_api.entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

	Optional<Guest> findByIdAndIsDeletedFalse(Long id);
}
