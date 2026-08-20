package com.urbano.payment.controller;

import com.urbano.payment.dto.DarajaCallbackRequest;
import com.urbano.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/callbacks")
@RequiredArgsConstructor
public class CallbackController {

    private final PaymentService paymentService;

    @PostMapping("/daraja")
    public ResponseEntity<String> processDarajaCallback(@RequestBody DarajaCallbackRequest request) {
        log.info("Received Daraja callback: {}", request);

        // Process the callback - update payment status based on the callback
        try {
            // In a real implementation, you would process the callback and update payment status
            log.info("Daraja callback processed successfully");
            return ResponseEntity.ok("Callback processed successfully");
        } catch (Exception e) {
            log.error("Error processing Daraja callback: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Error processing callback");
        }
    }
}
