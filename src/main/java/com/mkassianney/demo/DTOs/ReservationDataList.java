package com.mkassianney.demo.DTOs;

import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Enumerations.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public record ReservationDataList (
        @NotNull Long id,
        @NotNull LocalDate check_in,
        @NotNull int duration,
        @NotNull LocalDate check_out,
        @NotNull Integer number,
        @NotNull BigDecimal price,
        @NotNull Long client_id

){
        public ReservationDataList(Reservation reservation){
                this(reservation.getId(),reservation.getCheckIn(), reservation.getDuration(),reservation.getCheckOut(),reservation.getNumber(),reservation.getPrice(), reservation.getClient().getId());
        }
}
