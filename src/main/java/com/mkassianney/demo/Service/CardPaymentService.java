package com.mkassianney.demo.Service;

import com.mkassianney.demo.DTOs.PaymentData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Period;

@Service
public class CardPaymentService {

    public PaymentIntent createPayment(Reservation reservation, Client client, PaymentData data) throws Exception {
        Stripe.apiKey = "sk_test_51R950lGbY04CYewiISSrT9Og83aslbJ8fcDhJf3S4vSwJ052L1LNyZKysrw5cIgmQyJ9PxpYkBNs47gdlvcrs1Um00VJvsqwG9";

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

