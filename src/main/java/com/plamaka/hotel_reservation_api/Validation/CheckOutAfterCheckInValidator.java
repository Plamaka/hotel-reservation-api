package com.plamaka.hotel_reservation_api.Validation;

import com.plamaka.hotel_reservation_api.dto.request.ReservationRequestDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckOutAfterCheckInValidator implements ConstraintValidator<CheckOutAfterCheckIn, ReservationRequestDTO> {

    @Override
    public boolean isValid(ReservationRequestDTO dto,
                           ConstraintValidatorContext context) {

        return dto.getCheckOutDate().isAfter(dto.getCheckInDate());

    }

}