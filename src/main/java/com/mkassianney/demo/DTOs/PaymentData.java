package com.mkassianney.demo.DTOs;

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
        String paymentMethod,
        @NotNull
        String client_cpf
) {
    public Long getReservationId(){
        return reservation_id;
    }

    public Integer getReservationNumber(){
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
    public String getClientCpf(){
        return client_cpf;
    }
}
