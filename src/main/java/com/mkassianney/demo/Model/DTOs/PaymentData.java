package com.mkassianney.demo.Model.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;


public record PaymentData(
        @NotNull
        Long reservation_id,
        @NotNull
        BigDecimal amount,
        @NotBlank
        String currency,
        @NotBlank
        String paymentMethod
) {
        public Long getReservation_Id(){
                return reservation_id;
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
