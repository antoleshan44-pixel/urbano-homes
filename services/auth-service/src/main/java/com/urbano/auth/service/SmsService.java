package com.urbano.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {

    public void sendActivationSms(String phone, String activationLink) {
        String message = String.format(
                "Welcome to Urbano Homes! Please activate your account by clicking this link: %s",
                activationLink
        );
        sendSms(phone, message);
    }

    public void sendRentReminder(String phone, String tenantName, double amount, String dueDate) {
        String message = String.format(
                "Dear %s, your rent of KES %.2f is due on %s. Please ensure timely payment. - Urbano Homes",
                tenantName, amount, dueDate
        );
        sendSms(phone, message);
    }

    private void sendSms(String phone, String message) {
        // In a real implementation, this would call Africa's Talking API
        log.info("SMS to {}: {}", phone, message);
    }
}