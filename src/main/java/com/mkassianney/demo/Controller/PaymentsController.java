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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/payments")
public class PaymentsController {

    @Autowired
    private PaymentsRepository repository;
    @Autowired
    private PaymentsService service;

    @PostMapping("/toPay")
    @Transactional
    public void newPayment(@RequestBody @Valid PaymentData paymentData) throws Exception {
        service.processPayment(paymentData);
    }

    @GetMapping("/paymentList")
    public Page<PaymentDataList> paymentList(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){
        return repository.findAll(pageable).map(PaymentDataList::new);
    }

    @GetMapping("/paymentsByClient/{cpf}")
    public List<PaymentDataList> paymentListByClient (@PathVariable String cpf){
        return service.clientPayments(cpf);
    }

    @PutMapping("/cancelPayment/{id}")
    public ResponseEntity<String> cancelPayment(@PathVariable Long id){
        String message =  service.cancelPayment(id);
        return ResponseEntity.ok(message);
    }

}
