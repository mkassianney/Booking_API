package com.mkassianney.demo.Model.Entities;

import com.mkassianney.demo.Model.DTOs.PaymentData;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Payment")
@Table(name = "payments")
@Getter
@Setter
@EqualsAndHashCode
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    @Column(name = "reservation_number")
    private @NotNull Integer reservation_number;
    private BigDecimal amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String paymentMethod;
    @Column(unique = true)
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Payment() {
    }

    public Payment(Reservation reservation, PaymentData paymentData) {

        this.reservation = reservation;
        this.reservation_number = paymentData.reservation_number();
        this.amount = paymentData.amount();
        this.currency = paymentData.currency();
        this.paymentStatus = PaymentStatus.valueOf("PENDING");
        this.paymentMethod = paymentData.paymentMethod();
        this.transactionId = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    @Transient
    public Integer getReservationNumber() {
        return reservation != null ? reservation.getNumber() : null;
    }
}
