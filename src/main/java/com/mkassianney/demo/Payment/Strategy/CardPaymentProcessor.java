package com.mkassianney.demo.Payment.Strategy;

import com.mkassianney.demo.Model.DTORequest.PaymentData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CardPaymentProcessor implements PaymentProcessor {
    @Value("${stripe.api.key}")
    private String secretKey;

    @Override
    public Payment createPayment(PaymentData data, Reservation reservation, Client client, PaymentIntent intent) {

        BigDecimal amount = calculateAmountInReais(reservation);

        return Payment.builder()
                .reservation(reservation)
                .client(client)
                .amount(amount)
                .currency(data.currency())
                .paymentMethod(data.paymentMethod())
                .transactionId(intent.getId())
                .paymentStatus(PaymentStatus.PAID)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Transactional
    public PaymentIntent process(PaymentData data, Reservation reservation, Client client) throws StripeException {
        Stripe.apiKey = secretKey;

        BigDecimal amount = calculateAmountInReais(reservation);
        long amountInCents = toCents(amount);

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency(data.currency().toLowerCase())
                .addPaymentMethodType("card")
                .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.AUTOMATIC)
                .setReceiptEmail(client.getEmail())
                .build();

        return PaymentIntent.create(params);
    }

    @Override
    public String getMethod() {
        return "card";
    }

    @Override
    public BigDecimal calculateAmountInReais(Reservation reservation) {
        long days = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
        if (days <= 0) days = 1;

        return reservation.getPrice()
                .multiply(BigDecimal.valueOf(days))
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public long toCents(BigDecimal amountInReais) {
        return amountInReais.multiply(BigDecimal.valueOf(100)).longValueExact();
    }


}

