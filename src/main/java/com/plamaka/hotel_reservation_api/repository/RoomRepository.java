package com.plamaka.hotel_reservation_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plamaka.hotel_reservation_api.entity.Room;
import com.plamaka.hotel_reservation_api.enums.RoomStatus;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
	List<Room> findByRoomStatus(RoomStatus roomStatus);

    List<Room> findByFloor(Integer floor);

    List<Room> findByRoomTypeId(Long roomTypeId);

    List<Room> findByRoomTypeTypeName(String typeName);

}
