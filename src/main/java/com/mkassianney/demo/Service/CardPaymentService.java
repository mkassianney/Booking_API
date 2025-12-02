package com.mkassianney.demo.Service;

import com.mkassianney.demo.DTOs.PaymentData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Period;

@Service
public class CardPaymentService {
    @Value("${stripe.api.key}")
    private String secretKey;

    @Transactional
    public PaymentIntent createPayment(Reservation reservation, Client client, PaymentData data) throws Exception {
        Stripe.apiKey = secretKey;

        long days = Period.between(reservation.getCheckIn(), reservation.getCheckOut()).getDays();
        BigDecimal total = data.amount().multiply(BigDecimal.valueOf(days)).setScale(2);
        long amountInCents = total.multiply(BigDecimal.valueOf(100)).longValue();

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency(data.currency().toLowerCase())
                .addPaymentMethodType("card")
                .setCaptureMethod(PaymentIntentCreateParams.CaptureMethod.AUTOMATIC)
                .setReceiptEmail(client.getEmail())
                .build();

        return PaymentIntent.create(params);
    }
}

