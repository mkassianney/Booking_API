package com.mkassianney.demo.Repository;

import com.mkassianney.demo.Model.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment,Long> {
}
