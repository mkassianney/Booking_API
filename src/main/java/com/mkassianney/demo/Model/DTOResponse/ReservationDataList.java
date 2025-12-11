package com.mkassianney.demo.Model.DTOResponse;

import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Enumerations.ReservationStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservationDataList (
        @NotNull Long id,
        @NotNull LocalDate check_in,
        @NotNull int duration,
        @NotNull LocalDate check_out,
        @NotNull Integer number,
        @NotNull BigDecimal price,
        @NotNull ReservationStatus status,
        @NotNull Long client_id

){
        public ReservationDataList(Reservation reservation){
                this(reservation.getId(),reservation.getCheckInDate(), reservation.getDuration(),reservation.getCheckOutDate(),reservation.getRoomNumber(),reservation.getPrice(), reservation.getStatus(), reservation.getClient().getId());
        }
}
