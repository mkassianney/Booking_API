package com.mkassianney.demo.Controller;

import com.mkassianney.demo.Model.Entities.Payment;
import com.mkassianney.demo.Model.Enumerations.PaymentStatus;
import com.mkassianney.demo.Service.PaymentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments/status")
public class PaymentStatusController {

    private final PaymentStatusService statusService;

    @Autowired
    public PaymentStatusController(PaymentStatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/{id}")
    public PaymentStatus getStatus(@PathVariable Long id) {
        return statusService.getPaymentStatus(id);
    }

    @PutMapping("/{id}")
    public Payment updateStatus(@PathVariable Long id, @RequestParam PaymentStatus status) {
        return statusService.updatePaymentStatus(id, status);
    }
}

