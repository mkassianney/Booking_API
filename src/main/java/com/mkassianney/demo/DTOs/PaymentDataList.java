package com.mkassianney.demo.DTOs;

import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDataList (
        @NotNull Long id,
        @NotNull Long clientId,
        @NotNull Long reservationId,
        @NotNull BigDecimal amount,
        @NotNull String currency,
        @Enumerated(EnumType.STRING) PaymentStatus paymentStatus,
        @NotNull String paymentMethod,
        @NotNull String transactionId,
        @NotNull LocalDateTime createdAt,
        @NotNull LocalDateTime updatedAt


){
    public PaymentDataList(Payment payment){
        this(payment.getId(),payment.getId(),payment.getReservation().getId(),
                payment.getAmount(),payment.getCurrency(),payment.getPaymentStatus(),payment.getPaymentMethod(),payment.getTransactionId(),
                payment.getCreatedAt(),payment.getUpdatedAt());
    }
}
