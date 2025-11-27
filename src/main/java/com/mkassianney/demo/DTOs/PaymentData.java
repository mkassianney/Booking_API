package com.mkassianney.demo.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

public record PaymentData(
        @JsonProperty("reservation_id")
        @NotNull Long reservationId,
        @JsonProperty("reservation_number")
        @NotNull Integer reservationNumber,
        @NotNull @Positive BigDecimal amount,
        @NotNull String currency,
        @JsonProperty("payment_method")
        @NotNull String paymentMethod,
        @JsonProperty("client_cpf")
        @CPF(message = "CPF isn't valid") @NotBlank @Valid String clientCpf
) {
    public Long getReservationId(){
        return reservationId;
    }

    public Integer getReservationNumber(){
        return reservationNumber;
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
        return clientCpf;
    }
}
