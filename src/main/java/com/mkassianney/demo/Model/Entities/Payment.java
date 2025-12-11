package com.mkassianney.demo.Model.Entities;

import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "Payment")
@Table(name = "payments")
@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private BigDecimal amount;
    private String currency;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    private String paymentMethod;
    @Column(unique = true)
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

