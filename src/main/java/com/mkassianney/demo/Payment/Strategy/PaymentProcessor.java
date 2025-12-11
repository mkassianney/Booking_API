package com.mkassianney.demo.Payment.Strategy;

import com.mkassianney.demo.Model.DTORequest.PaymentData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.math.BigDecimal;

public interface PaymentProcessor {
    PaymentIntent process(PaymentData paymentData, Reservation reservation, Client client) throws StripeException;
    String getMethod();
    BigDecimal calculateAmountInReais(Reservation reservation);
    Payment createPayment(PaymentData data, Reservation reservation, Client client, PaymentIntent intent);
    long toCents(BigDecimal amountInReais);
}
