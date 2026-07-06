package com.plamaka.hotel_reservation_api.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckOutAfterCheckInValidator.class)
@Documented
public @interface CheckOutAfterCheckIn {

    String message() default "Check-out date must be after check-in date.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}