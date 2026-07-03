package com.plamaka.hotel_reservation_api.entity;

import java.util.List;

import com.plamaka.hotel_reservation_api.enums.RoomStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "Rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String roomNumber;
	
	private Integer floor;
	
	@Enumerated(EnumType.STRING)
	private RoomStatus roomStatus;
	
	@ManyToOne
	@JoinColumn(name = "room_type_id")
	private RoomType roomType;
	
	private Boolean balcony;
	
	@OneToMany(mappedBy = "room")
	private List<ReservationRoom> reservationRooms;
	
	public Room() {
	}
	
	public Room(String roomNumber, Integer floor, RoomStatus roomstatus, Boolean balcony) {
		super();
		this.roomNumber = roomNumber;
		this.floor = floor;
		this.roomStatus = roomstatus;
		this.balcony = balcony;
	}
	
	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Boolean getBalcony() {
		return balcony;
	}

	public void setBalcony(Boolean balcony) {
		this.balcony = balcony;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public List<ReservationRoom> getReservationRooms() {
		return reservationRooms;
	}

	public void setReservationRooms(List<ReservationRoom> reservationRooms) {
		this.reservationRooms = reservationRooms;
	}
}
