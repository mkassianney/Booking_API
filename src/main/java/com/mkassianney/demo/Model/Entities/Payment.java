package com.mkassianney.demo.Model.Entities;

import com.mkassianney.demo.DTOs.PaymentData;
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
    private @NotNull Integer reservationNumber;
    @Column(name  = "client_name")
    private String clientName;
    @Column(name  = "client_email")
    private String clientEmail;
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
        this.reservationNumber = paymentData.reservation_number();
        this.clientName = reservation.getClientName();
        this.clientEmail = reservation.getClientEmail();
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
    public void setReservationNumber(Integer reservation_number){
        this.reservationNumber = reservation_number;
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

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Integer getReservationNumber() {
        return reservationNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
