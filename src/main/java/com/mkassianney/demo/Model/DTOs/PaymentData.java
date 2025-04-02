package com.mkassianney.demo.Model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public record PaymentData(
        @NotNull
        Long reservation_id,
        @NotNull
        BigDecimal amount,
        @NotBlank
        String currency,
        @NotBlank
        String paymentMethod
) {}
