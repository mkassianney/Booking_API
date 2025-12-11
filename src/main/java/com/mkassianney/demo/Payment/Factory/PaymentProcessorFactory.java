package com.mkassianney.demo.Payment.Factory;


import com.mkassianney.demo.Payment.Strategy.CardPaymentProcessor;
import com.mkassianney.demo.Payment.Strategy.PaymentProcessor;
import com.stripe.model.PaymentMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PaymentProcessorFactory {

    private final List<PaymentProcessor> processors;

    public PaymentProcessorFactory(List<PaymentProcessor> processors) {
        this.processors = processors;
    }

    public PaymentProcessor getProcessor(String method) {
        return processors.stream()
                .filter(p -> p.getMethod().equalsIgnoreCase(method))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unsupported payment method: " + method));
    }
}



