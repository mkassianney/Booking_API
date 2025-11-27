package com.mkassianney.demo.Controller;

import com.mkassianney.demo.DTOs.PaymentData;
import com.mkassianney.demo.DTOs.PaymentDataList;
import com.mkassianney.demo.Repository.PaymentsRepository;
import com.mkassianney.demo.Service.PaymentsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsRepository paymentsRepository;
    @Autowired
    private PaymentsService paymentsService;

    @PostMapping("/toPay")
    @Transactional
    public void newPayment(@RequestBody @Valid PaymentData paymentData) throws Exception {
        paymentsService.createPayment(paymentData);
        paymentsService.processPayment(paymentData);
    }

    @GetMapping("/paymentList")
    public Page<PaymentDataList> paymentList(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        return paymentsRepository.findAll(pageable).map(PaymentDataList::new);
    }
}
