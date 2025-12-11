package com.mkassianney.demo.Repository;

import com.mkassianney.demo.Model.DTOResponse.PaymentDataList;
import com.mkassianney.demo.Model.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment,Long> {

    List<PaymentDataList> findAllByClientId(@Param("id") Long id);

}
