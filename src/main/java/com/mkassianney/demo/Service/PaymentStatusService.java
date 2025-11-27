package com.mkassianney.demo.Service;

import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.mkassianney.demo.Repository.PaymentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusService {

    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentStatusService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    public PaymentStatus getPaymentStatus(Long paymentId) {
        Payment payment = paymentsRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + paymentId));
        return payment.getPaymentStatus();
    }

    public Payment updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Payment payment = paymentsRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id: " + paymentId));
        payment.setPaymentStatus(status);
        return paymentsRepository.save(payment);
    }
}
