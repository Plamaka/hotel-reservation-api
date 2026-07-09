package com.plamaka.hotel_reservation_api.entity;

import java.math.BigDecimal;
import java.util.List;

import com.plamaka.hotel_reservation_api.dto.request.RoomTypeRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Room_Types")
public class RoomType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String typeName;
	
	private Integer capacity;
	
	private BigDecimal pricePerNight;
	
	private Boolean petsAllowed;
	
	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms;
	
	public RoomType() {
	}

	public RoomType(RoomTypeRequestDTO typeDto) {
		super();
		this.typeName = typeDto.getTypeName();
		this.capacity = typeDto.getCapacity();
		this.pricePerNight = typeDto.getPricePerNight();
		this.petsAllowed = typeDto.getPetsAllowed();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(BigDecimal pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public Boolean getPetsAllowed() {
		return petsAllowed;
	}

	public void setPetsAllowed(Boolean petsAllowed) {
		this.petsAllowed = petsAllowed;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
}
