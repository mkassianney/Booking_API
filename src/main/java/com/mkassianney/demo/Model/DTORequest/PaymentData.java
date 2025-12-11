package com.mkassianney.demo.Model.DTORequest;

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
}
