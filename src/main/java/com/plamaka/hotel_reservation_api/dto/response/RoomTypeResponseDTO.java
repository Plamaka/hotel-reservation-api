package com.plamaka.hotel_reservation_api.dto.response;

import java.math.BigDecimal;

import com.plamaka.hotel_reservation_api.entity.RoomType;

public class RoomTypeResponseDTO {
	
	private Long id;
	
	private String typeName;
	
	private Integer capacity;
	
	private BigDecimal pricePerNight;
	
	public RoomTypeResponseDTO(RoomType type) {
		super();
		this.id = type.getId();
		this.typeName = type.getTypeName();
		this.capacity = type.getCapacity();
		this.pricePerNight = type.getPricePerNight();
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
	
	
}
