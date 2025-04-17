package com.mkassianney.demo.Model.DTOs;

import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Enumerations.roomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public record ReservationDataList (
        @NotNull
        Long id,
        @NotNull
        LocalDate checkIn,
        @NotNull
        LocalDate checkOut,
        @NotNull
        Integer number,
        @Enumerated(EnumType.STRING)
        roomType type,
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
                this(reservation.getId(),reservation.getCheckIn(),reservation.getCheckOut(),reservation.getNumber(),reservation.getType(),reservation.getPrice(), reservation.getClientName(), reservation.getClientCpf(),reservation.getClientEmail());
        }
}
