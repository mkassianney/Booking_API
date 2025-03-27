package com.mkassianney.demo.Model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ReservationData(
        @NotNull
        Date checkIn,
        @NotNull
        Date checkOut) {
}
