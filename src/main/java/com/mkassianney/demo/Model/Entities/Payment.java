package com.mkassianney.demo.Model.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mkassianney.demo.Model.DTOs.PaymentData;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.mkassianney.demo.Model.Repository.ReservationRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;
    private BigDecimal amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String paymentMethod;
    @Column(unique = true)
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Payment(){}
    @JsonCreator
    public Payment(@JsonProperty("value") @Valid Reservation reservation, PaymentData paymentData){

        this.reservation = reservation;
        this.amount = paymentData.amount();
        this.currency = paymentData.currency();
        this.paymentStatus = PaymentStatus.valueOf("PENDING");
        this.paymentMethod = paymentData.paymentMethod();
        this.transactionId = null;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Reservation getReservation() {
        return reservation;
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

    public void setReservation(Reservation reservation) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(reservation, payment.reservation) && Objects.equals(transactionId, payment.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservation, transactionId);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
