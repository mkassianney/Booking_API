package com.mkassianney.demo.Model.Repository;

import com.mkassianney.demo.Model.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payment,Long> {
}
