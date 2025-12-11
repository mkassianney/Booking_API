package com.mkassianney.demo.Service;

import com.mkassianney.demo.Model.DTORequest.PaymentData;
import com.mkassianney.demo.Model.DTOResponse.PaymentDataList;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.mkassianney.demo.Model.Enumerations.ReservationStatus;
import com.mkassianney.demo.Payment.Factory.PaymentProcessorFactory;
import com.mkassianney.demo.Payment.Strategy.CardPaymentProcessor;
import com.mkassianney.demo.Payment.Strategy.PaymentProcessor;
import com.mkassianney.demo.Repository.ClientRepository;
import com.mkassianney.demo.Repository.PaymentsRepository;
import com.mkassianney.demo.Repository.ReservationRepository;
import com.stripe.model.PaymentIntent;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentsService {

    private final PaymentProcessorFactory factory;
    private final PaymentsRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PaymentsService(PaymentProcessorFactory factory,
                           PaymentsRepository paymentRepository,
                           ReservationRepository reservationRepository,
                           ClientRepository clientRepository) {
        this.factory = factory;
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public Payment processPayment(PaymentData paymentData) throws Exception {

        Reservation reservation = reservationRepository.findById(paymentData.reservationId())
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        Client client = clientRepository.findByCpf(paymentData.clientCpf())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        PaymentProcessor processor = factory.getProcessor(paymentData.paymentMethod());

        PaymentIntent intent = processor.process(paymentData, reservation, client);

        Payment payment = processor.createPayment(paymentData, reservation, client, intent);

        paymentRepository.save(payment);

        return payment;
    }

    public List<PaymentDataList> clientPayments(String cpf){
        Client client = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with this info: " + cpf));

        List<PaymentDataList> allPayments = paymentRepository.findAllByClientId(client.getId());
        return allPayments;
    }

    @Transactional
    public String cancelPayment(Long id){
        Payment pay = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with this info: " + id));

        Reservation res = pay.getReservation();
        pay = pay.toBuilder()
                .paymentStatus(PaymentStatus.CANCEL)
                .updatedAt(LocalDateTime.now())
                .build();

        paymentRepository.save(pay);
        res.setStatus(ReservationStatus.CANCELED);

        return "Payment is now canceled";
    }
}
