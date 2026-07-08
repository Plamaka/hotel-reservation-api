package com.plamaka.hotel_reservation_api.entity;

import java.util.List;

import com.plamaka.hotel_reservation_api.dto.request.GuestRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Guests")
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstName;
	
	private String middleName;

	private String lastName;
	
	private String email;
	
	private String phoneNumber;
	
	@OneToMany(mappedBy = "guest")
	private List<Reservation> reservations;
	
	private Boolean isDeleted;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	

	public Guest() {
	}

	public Guest(String firstName, String middleName, String lastName, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Guest(GuestRequestDTO dto) {
		super();
		this.firstName = dto.getFirstName();
		this.middleName = dto.getMiddleName();
		this.lastName = dto.getLastName();
		this.email = dto.getEmail();
		this.phoneNumber = dto.getPhoneNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getFullName() {
		return firstName + " " + middleName + " " + lastName;
	}	
	
	
}
