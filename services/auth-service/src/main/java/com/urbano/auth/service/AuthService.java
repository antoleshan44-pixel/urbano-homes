package com.urbano.auth.service;

import com.urbano.auth.dto.AuthRequest;
import com.urbano.auth.dto.AuthResponse;
import com.urbano.auth.dto.RefreshTokenRequest;
import com.urbano.auth.dto.RefreshTokenResponse;
import com.urbano.auth.dto.RegisterRequest;
import com.urbano.auth.dto.TenantActivateRequest;
import com.urbano.auth.dto.TenantRegistrationRequest;
import com.urbano.auth.dto.TenantRegistrationResponse;
import com.urbano.auth.entity.User;
import com.urbano.auth.repository.UserRepository;
import com.urbano.common.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    /**
     * Register a new user
     */
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Split full name into first and last name
        String[] nameParts = request.getFullName().split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";

        // Create user with version initialized
        User user = User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .firstName(firstName)
                .lastName(lastName)
                .role(UserRole.TENANT)
                .isActive(true)
                .emailVerified(false)
                .createdAt(LocalDateTime.now())
                .version(0L)  // Initialize version for optimistic locking
                .build();

        user = userRepository.save(user);
        log.info("User registered successfully: {}", user.getEmail());

        // Generate tokens
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .tokenType("Bearer")
                .expiresIn(900)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (tokenBlacklistService.isTokenBlacklisted(request.getEmail())) {
            throw new RuntimeException("User account is locked");
        }

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .tokenType("Bearer")
                .expiresIn(900)
                .build();
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        if (tokenBlacklistService.isTokenBlacklisted(request.getRefreshToken())) {
            throw new RuntimeException("Refresh token is blacklisted");
        }

        String email = jwtService.extractEmail(request.getRefreshToken());
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newToken = jwtService.generateToken(user);

        return RefreshTokenResponse.builder()
                .accessToken(newToken)
                .tokenType("Bearer")
                .expiresIn(900)
                .build();
    }

    public void logout(String token) {
        tokenBlacklistService.blacklistToken(token);
        log.info("User logged out");
    }

    @Transactional
    public TenantRegistrationResponse registerTenant(TenantRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .id(UUID.randomUUID())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .firstName("Tenant")
                .lastName("User")
                .role(UserRole.TENANT)
                .isActive(true)
                .emailVerified(false)
                .createdAt(LocalDateTime.now())
                .version(0L)  // Initialize version for optimistic locking
                .build();

        user = userRepository.save(user);

        return TenantRegistrationResponse.builder()
                .tenantId(user.getId().toString())
                .message("Tenant registered successfully. Please verify your email.")
                .build();
    }

    @Transactional
    public void activateTenant(TenantActivateRequest request) {
        // In a real implementation, validate the token and activate the user
        log.info("Tenant activated with token: {}", request.getToken());
    }
}