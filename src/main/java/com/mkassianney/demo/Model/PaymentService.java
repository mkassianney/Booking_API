package com.mkassianney.demo.Model;

import com.mkassianney.demo.Model.DTOs.PaymentData;
import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.mkassianney.demo.Model.Repository.PaymentsRepository;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class PaymentService {

    private final PaymentsRepository paymentRepository;

    public PaymentService(PaymentsRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(PaymentData paymentData) throws Exception {

        Stripe.apiKey = "";

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(paymentData.getAmount().multiply(new BigDecimal(100)).longValue()) // Stripe usa centavos
                .setCurrency(paymentData.getCurrency())
                .setPaymentMethod(String.valueOf(java.util.List.of("card","pix","boleto")))
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        
        Payment payment = new Payment();
        payment.setAmount(paymentData.getAmount());
        payment.setCurrency(paymentData.getCurrency());
        payment.setPaymentMethod(paymentData.getPaymentMethod());
        payment.setTransactionId(paymentIntent.getId());
        payment.setPaymentStatus(PaymentStatus.valueOf("PENDING"));

        return paymentRepository.save(payment);
    }
}

