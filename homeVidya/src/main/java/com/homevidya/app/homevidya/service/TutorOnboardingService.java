package com.homevidya.app.homevidya.service;

import com.homevidya.app.homevidya.dto.*;
import com.homevidya.app.homevidya.entities.*;
import com.homevidya.app.homevidya.enums.ApplicationStatus;
import com.homevidya.app.homevidya.exception.ResourceNotFoundException;
import com.homevidya.app.homevidya.exception.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TutorOnboardingService {
    
    void saveTutorProfile(Long userId, TutorProfileRequest request);
    
    void saveTutorEducation(Long userId, TutorEducationRequest request);
    
    void saveTutorExperience(Long userId, TutorExperienceRequest request);
    
    void saveTutorPricing(Long userId, TutorPricingRequest request);
    
    void saveTutorDocument(Long userId, TutorDocumentRequest request);
    
    void submitTutorApplication(Long userId, TutorSubmitRequest request);
    
    TutorProfile getTutorProfile(Long userId);
    
    double getProfileCompletion(Long userId);
}
