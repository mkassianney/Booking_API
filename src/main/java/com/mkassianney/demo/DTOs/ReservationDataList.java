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
        @NotNull
        Long id,
        @NotNull
        LocalDate check_in,
        @NotNull
        Period duration,
        @NotNull
        LocalDate check_out,
        @NotNull
        Integer number,
        @Enumerated(EnumType.STRING)
        RoomType type,
        @NotNull
        BigDecimal price,
        @NotNull
        String client_name,
        @NotNull
        String client_cpf,
        @NotNull
        String client_email

){
        public ReservationDataList(Reservation reservation){
                this(reservation.getId(),reservation.getCheckIn(), reservation.getDuration(),reservation.getCheckOut(),reservation.getNumber(),reservation.getType(),reservation.getPrice(), reservation.getClientName(), reservation.getClientCpf(),reservation.getClientEmail());
        }
}
