package com.mkassianney.demo.Model.DTOs;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PaymentData(
        @NotNull
        Long reservation_id,
        @NotNull
        Integer reservation_number,
        @NotNull
        BigDecimal amount,
        @NotNull
        String currency,
        @NotNull
        String paymentMethod
) {
    public Long getReservation_Id(){
        return reservation_id;
    }

    public Integer getReservation_Number(){
        return reservation_number;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    public String getCurrency(){
        return currency;
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }
}
