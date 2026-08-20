package com.urbano.notification.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    @NotNull
    private UUID userId;

    @NotBlank
    private String channel;

    @NotBlank
    private String type;

    @NotBlank
    @Email
    private String recipient;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;
}
