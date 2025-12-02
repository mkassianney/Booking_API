package com.mkassianney.demo.Service;

import com.mkassianney.demo.DTOs.PaymentData;
import com.mkassianney.demo.DTOs.PaymentDataList;
import com.mkassianney.demo.Model.Entities.Client;
import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Entities.Reservation;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.mkassianney.demo.Repository.ClientRepository;
import com.mkassianney.demo.Repository.PaymentsRepository;
import com.mkassianney.demo.Repository.ReservationRepository;
import com.stripe.model.PaymentIntent;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public Payment processPayment(PaymentData paymentData) throws Exception {

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

    // 1 - pesquisar cliente pelo cpf e pegar o id
    // 2 - pesquisar na tabela payments se esse cliente tem algum pagamento pelo id

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

        pay.setPaymentStatus(PaymentStatus.CANCEL);
        paymentRepository.save(pay);

        return "Payment is now canceled";
    }
}
