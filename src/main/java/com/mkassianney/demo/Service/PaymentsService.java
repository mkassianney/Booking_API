package com.mkassianney.demo.Service;

import com.mkassianney.demo.DTOs.PaymentData;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.mkassianney.demo.Repository.ClientRepository;
import com.mkassianney.demo.Repository.PaymentsRepository;
import com.mkassianney.demo.Repository.ReservationRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;

@Service
public class PaymentsService {

    private final CardPaymentService cardPaymentService;
    private final PaymentsRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PaymentsService(CardPaymentService cardPaymentService,
                           PaymentsRepository paymentRepository,
                           ReservationRepository reservationRepository,
                           ClientRepository clientRepository) {
        this.cardPaymentService = cardPaymentService;
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    public Payment processPayment(PaymentData paymentData) throws Exception {
        // 1. Busca reservation e client
        Reservation reservation = reservationRepository.findById(paymentData.reservationId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
        Client client = clientRepository.findByCpf(paymentData.clientCpf())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));


        PaymentIntent paymentIntent;
        switch (paymentData.paymentMethod().toLowerCase()) {
            case "card":
                paymentIntent = cardPaymentService.createPayment(reservation, client, paymentData);
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentData.paymentMethod());
        }


        Payment payment = new Payment();
        payment.setReservation(reservation);
        payment.setClient(reservation.getClient());
        payment.setAmount(paymentData.amount());
        payment.setCurrency(paymentData.currency());
        payment.setPaymentMethod(paymentData.paymentMethod());
        payment.setTransactionId(paymentIntent.getId());
        payment.setPaymentStatus(PaymentStatus.PAID);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
}
