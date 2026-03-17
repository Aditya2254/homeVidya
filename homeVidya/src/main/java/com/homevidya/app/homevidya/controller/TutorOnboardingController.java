package com.homevidya.app.homevidya.controller;

import com.homevidya.app.homevidya.dto.*;
import com.homevidya.app.homevidya.service.TutorOnboardingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/tutor")
@RequiredArgsConstructor
@Slf4j
public class TutorOnboardingController {

    private final TutorOnboardingService tutorOnboardingService;

    /**
     * Save tutor profile information
     * POST /api/tutor/profile
     */
    @PostMapping("/profile")
    public ResponseEntity<ApiResponse<String>> saveTutorProfile(
            @Valid @RequestBody TutorProfileRequest request,
            Authentication authentication) {
        log.info("Request to save tutor profile");
        
        Long userId = Long.parseLong(authentication.getName());
        tutorOnboardingService.saveTutorProfile(userId, request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<String>builder()
                        .success(true)
                        .message("Tutor profile saved successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    /**
     * Save tutor education information
     * POST /api/tutor/education
     */
    @PostMapping("/education")
    public ResponseEntity<ApiResponse<String>> saveTutorEducation(
            @Valid @RequestBody TutorEducationRequest request,
            Authentication authentication) {
        log.info("Request to save tutor education");
        
        Long userId = Long.parseLong(authentication.getName());
        tutorOnboardingService.saveTutorEducation(userId, request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<String>builder()
                        .success(true)
                        .message("Tutor education saved successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    /**
     * Save tutor experience information
     * POST /api/tutor/experience
     */
    @PostMapping("/experience")
    public ResponseEntity<ApiResponse<String>> saveTutorExperience(
            @Valid @RequestBody TutorExperienceRequest request,
            Authentication authentication) {
        log.info("Request to save tutor experience");
        
        Long userId = Long.parseLong(authentication.getName());
        tutorOnboardingService.saveTutorExperience(userId, request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<String>builder()
                        .success(true)
                        .message("Tutor experience saved successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    /**
     * Save tutor pricing information
     * POST /api/tutor/pricing
     */
    @PostMapping("/pricing")
    public ResponseEntity<ApiResponse<String>> saveTutorPricing(
            @Valid @RequestBody TutorPricingRequest request,
            Authentication authentication) {
        log.info("Request to save tutor pricing");
        
        Long userId = Long.parseLong(authentication.getName());
        tutorOnboardingService.saveTutorPricing(userId, request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<String>builder()
                        .success(true)
                        .message("Tutor pricing saved successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    /**
     * Upload tutor documents
     * POST /api/tutor/documents
     */
    @PostMapping("/documents")
    public ResponseEntity<ApiResponse<String>> saveTutorDocument(
            @Valid @RequestBody TutorDocumentRequest request,
            Authentication authentication) {
        log.info("Request to save tutor document");
        
        Long userId = Long.parseLong(authentication.getName());
        tutorOnboardingService.saveTutorDocument(userId, request);
        
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<String>builder()
                        .success(true)
                        .message("Tutor document saved successfully")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    /**
     * Submit tutor application with validation
     * POST /api/tutor/submit
     */
    @PostMapping("/submit")
    public ResponseEntity<ApiResponse<String>> submitTutorApplication(
            @Valid @RequestBody TutorSubmitRequest request,
            Authentication authentication) {
        log.info("Request to submit tutor application");
        
        Long userId = Long.parseLong(authentication.getName());
        tutorOnboardingService.submitTutorApplication(userId, request);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<String>builder()
                        .success(true)
                        .message("Tutor application submitted successfully. Awaiting verification.")
                        .timestamp(LocalDateTime.now())
                        .build());
    }

    /**
     * Get profile completion percentage
     * GET /api/tutor/profile-completion
     */
    @GetMapping("/profile-completion")
    public ResponseEntity<ApiResponse<Double>> getProfileCompletion(
            Authentication authentication) {
        log.info("Request to get profile completion");
        
        Long userId = Long.parseLong(authentication.getName());
        double completion = tutorOnboardingService.getProfileCompletion(userId);
        
        return ResponseEntity.ok(ApiResponse.<Double>builder()
                .success(true)
                .message("Profile completion retrieved successfully")
                .data(completion)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
