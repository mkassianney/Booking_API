package com.mkassianney.demo.DTOs;

import com.mkassianney.demo.Model.Entities.Reservation;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationSimpleResponse(
        @NotNull Long id,
        @NotNull LocalDate check_in,
        @NotNull LocalDate check_out,
        @NotNull int duration,
        @NotNull Integer number,
        @NotNull BigDecimal price
) {
    public ReservationSimpleResponse(Reservation reservation){
        this(reservation.getId(), reservation.getCheckIn(), reservation.getCheckOut(), reservation.getDuration(),
                reservation.getNumber(), reservation.getPrice());
    }

    public static ReservationSimpleResponse from(Reservation r) {
        return new ReservationSimpleResponse(
                r.getId(),
                r.getCheckIn(),
                r.getCheckOut(),
                r.getDuration(),
                r.getNumber(),
                r.getPrice()
        );
    }
}
