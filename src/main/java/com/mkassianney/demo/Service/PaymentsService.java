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
import java.time.Period;


@Service
public class PaymentsService {

    @Autowired
    private PaymentsRepository paymentRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClientRepository clientRepository;

    private Reservation reservation;
    private Client c;
    public Payment createPayment(PaymentData paymentData) {

        if (paymentData.reservation_id() == null) {
            throw new IllegalArgumentException("Reservation ID cannot be null");
        }
        reservation = reservationRepository.findById(paymentData.reservation_id())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + paymentData.reservation_id()));

        c = clientRepository.findByCpf(paymentData.client_cpf())
                .orElseThrow(() -> new EntityNotFoundException("Client not found with cpf: " + paymentData.client_cpf()));

        Payment payment = new Payment(reservation,paymentData);

        return paymentRepository.save(payment);
    }

    public PaymentsService(PaymentsRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment (PaymentData paymentData) throws Exception {

        Stripe.apiKey = "";

        Period p = Period.between(reservation.getCheckIn(),reservation.getCheckOut());
        int d = p.getDays();

        BigDecimal total = paymentData.getAmount()
                .multiply(BigDecimal.valueOf(d))
                .setScale(2); // multiplies the value of amount and the days;

        Long amountInCents = total.multiply(BigDecimal.valueOf(100)).longValue(); // convert to cents;
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency(paymentData.getCurrency())
                .setPaymentMethod(paymentData.getPaymentMethod())
                .setReceiptEmail(reservation.getClientEmail())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        try{
            Payment payment = new Payment();
            payment.setReservation(reservation);
            payment.setAmount(paymentData.amount());
            payment.setCurrency(paymentData.currency());
            payment.setPaymentMethod(paymentData.paymentMethod());
            payment.setTransactionId(paymentIntent.getId());
            payment.setPaymentStatus(PaymentStatus.PAID);

            return paymentRepository.save(payment);
        } catch(Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
