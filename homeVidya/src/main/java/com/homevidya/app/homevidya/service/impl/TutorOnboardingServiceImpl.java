package com.homevidya.app.homevidya.service.impl;

import com.homevidya.app.homevidya.dto.*;
import com.homevidya.app.homevidya.entities.*;
import com.homevidya.app.homevidya.enums.ApplicationStatus;
import com.homevidya.app.homevidya.enums.VerificationStatus;
import com.homevidya.app.homevidya.exception.ResourceNotFoundException;
import com.homevidya.app.homevidya.exception.ValidationException;
import com.homevidya.app.homevidya.repository.*;
import com.homevidya.app.homevidya.service.MobileVerificationService;
import com.homevidya.app.homevidya.service.TutorOnboardingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorOnboardingServiceImpl implements TutorOnboardingService {

    private final UserRepository userRepository;
    private final TutorProfileRepository tutorProfileRepository;
    private final TutorEducationRepository tutorEducationRepository;
    private final TutorExperienceRepository tutorExperienceRepository;
    private final TutorPricingRepository tutorPricingRepository;
    private final TutorDocumentRepository tutorDocumentRepository;
    private final TutorSubjectRepository tutorSubjectRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRepository classRepository;
    private final MobileVerificationService mobileVerificationService;

    @Override
    @Transactional
    public void saveTutorProfile(Long userId, TutorProfileRequest request) {
        log.info("Saving tutor profile for user: {}", userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Verify mobile number
        if (!mobileVerificationService.isMobileVerified(request.getMobileNumber())) {
            throw new ValidationException("Mobile number is not verified", "MOBILE_NOT_VERIFIED");
        }

        TutorProfile tutorProfile = tutorProfileRepository.findByUser(user)
                .orElse(new TutorProfile());


        tutorProfile.setUser(user);
        tutorProfile.setGender(request.getGender());
        tutorProfile.setDateOfBirth(request.getDateOfBirth());
        tutorProfile.setCity(request.getCity());
        tutorProfile.setArea(request.getArea());
        tutorProfile.setFullAddress(request.getFullAddress());
        tutorProfile.setPinCode(request.getPinCode());
        tutorProfile.setBio(request.getBio());
        tutorProfile.setTeachingMode(request.getTeachingMode());
        tutorProfile.setProfilePhoto(request.getProfilePhotoUrl());
        tutorProfile.setApplicationStatus(ApplicationStatus.PENDING);
        
        tutorProfileRepository.save(tutorProfile);
        log.info("Tutor profile saved successfully for user: {}", userId);
    }

    @Override
    @Transactional
    public void saveTutorEducation(Long userId, TutorEducationRequest request) {
        log.info("Saving tutor education for user: {}", userId);
        
        TutorProfile tutorProfile = getTutorProfileOrThrow(userId);
        
        TutorEducation education = new TutorEducation();
        education.setTutor(tutorProfile);
        education.setHighestQualification(request.getHighestQualification());
        education.setDegreeName(request.getDegreeName());
        education.setUniversity(request.getUniversity());
        education.setPassingYear(request.getPassingYear());
        education.setCertificateUrl(request.getCertificateUrl());
        
        tutorEducationRepository.save(education);
        log.info("Tutor education saved successfully for user: {}", userId);
    }

    @Override
    @Transactional
    public void saveTutorExperience(Long userId, TutorExperienceRequest request) {
        log.info("Saving tutor experience for user: {}", userId);
        
        TutorProfile tutorProfile = getTutorProfileOrThrow(userId);
        
        TutorExperience experience = new TutorExperience();
        experience.setTutor(tutorProfile);
        experience.setTotalExperience(request.getTotalExperience());
        experience.setWorkType(request.getWorkType());
        experience.setDescription(request.getDescription());
        
        tutorExperienceRepository.save(experience);
        log.info("Tutor experience saved successfully for user: {}", userId);
    }

    @Override
    @Transactional
    public void saveTutorPricing(Long userId, TutorPricingRequest request) {
        log.info("Saving tutor pricing for user: {}", userId);
        
        TutorProfile tutorProfile = getTutorProfileOrThrow(userId);
        
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));
        
        ClassEntity classEntity = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        
        TutorPricing pricing = new TutorPricing();
        pricing.setTutor(tutorProfile);
        pricing.setSubject(subject);
        pricing.setClazz(classEntity);
        pricing.setFeePerHour(request.getFeePerHour());
        pricing.setMonthlyFee(request.getMonthlyFee());
        
        tutorPricingRepository.save(pricing);
        log.info("Tutor pricing saved successfully for user: {}", userId);
    }

    @Override
    @Transactional
    public void saveTutorDocument(Long userId, TutorDocumentRequest request) {
        log.info("Saving tutor document for user: {}", userId);
        
        TutorProfile tutorProfile = getTutorProfileOrThrow(userId);
        
        TutorDocument document = new TutorDocument();
        document.setTutor(tutorProfile);
        document.setDocumentType(request.getDocumentType());
        document.setFileUrl(request.getDocumentUrl());
        document.setDocumentNumber(request.getDocumentNumber());
        document.setVerificationStatus(VerificationStatus.PENDING);
        
        tutorDocumentRepository.save(document);
        log.info("Tutor document saved successfully for user: {}", userId);
    }

    @Override
    @Transactional
    public void submitTutorApplication(Long userId, TutorSubmitRequest request) {
        log.info("Submitting tutor application for user: {}", userId);
        
        TutorProfile tutorProfile = getTutorProfileOrThrow(userId);
        
        // Validate all requirements
        validateApplicationSubmission(tutorProfile, request);
        
        tutorProfile.setApplicationStatus(ApplicationStatus.SUBMITTED);
        tutorProfileRepository.save(tutorProfile);
        
        log.info("Tutor application submitted successfully for user: {}", userId);
    }

    @Override
    @Transactional(readOnly = true)
    public TutorProfile getTutorProfile(Long userId) {
        return getTutorProfileOrThrow(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public double getProfileCompletion(Long userId) {
        log.info("Calculating profile completion for user: {}", userId);
        
        TutorProfile tutorProfile = getTutorProfileOrThrow(userId);
        
        int totalFields = 0;
        int completedFields = 0;
        
        // Profile fields
        if (tutorProfile.getGender() != null) completedFields++;
        totalFields++;
        
        if (tutorProfile.getDateOfBirth() != null) completedFields++;
        totalFields++;
        
        if (tutorProfile.getCity() != null && !tutorProfile.getCity().isEmpty()) completedFields++;
        totalFields++;
        
        if (tutorProfile.getArea() != null && !tutorProfile.getArea().isEmpty()) completedFields++;
        totalFields++;
        
        if (tutorProfile.getFullAddress() != null && !tutorProfile.getFullAddress().isEmpty()) completedFields++;
        totalFields++;
        
        if (tutorProfile.getPinCode() != null && !tutorProfile.getPinCode().isEmpty()) completedFields++;
        totalFields++;
        
        if (tutorProfile.getProfilePhoto() != null && !tutorProfile.getProfilePhoto().isEmpty()) completedFields++;
        totalFields++;
        
        // Education
        if (tutorProfile.getEducationList() != null && !tutorProfile.getEducationList().isEmpty()) completedFields++;
        totalFields++;
        
        // Experience
        if (tutorProfile.getExperiences() != null && !tutorProfile.getExperiences().isEmpty()) completedFields++;
        totalFields++;
        
        // Subjects
        if (tutorProfile.getSubjects() != null && !tutorProfile.getSubjects().isEmpty()) completedFields++;
        totalFields++;
        
        // Pricing
        if (tutorProfile.getPricingList() != null && !tutorProfile.getPricingList().isEmpty()) completedFields++;
        totalFields++;
        
        // Documents
        if (tutorProfile.getDocuments() != null && !tutorProfile.getDocuments().isEmpty()) completedFields++;
        totalFields++;
        
        double completion = (completedFields / (double) totalFields) * 100;
        log.info("Profile completion for user {}: {}%", userId, completion);
        
        return completion;
    }

    private TutorProfile getTutorProfileOrThrow(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return tutorProfileRepository.findByUser(user)
                .orElseThrow(() -> new ValidationException("Tutor profile not found. Please create profile first.", "PROFILE_NOT_FOUND"));
    }

    private void validateApplicationSubmission(TutorProfile tutorProfile, TutorSubmitRequest request) {
        // Check mobile verification
        User user = tutorProfile.getUser();
        if (!user.isMobileVerified()) {
            throw new ValidationException("Mobile number must be verified", "MOBILE_NOT_VERIFIED");
        }
        
        // Check minimum subjects
        if (request.getSubjectIds() == null || request.getSubjectIds().isEmpty()) {
            throw new ValidationException("Minimum one subject must be selected", "NO_SUBJECTS");
        }
        
        // Check minimum pricing (fee entries)
        if (tutorProfile.getPricingList() == null || tutorProfile.getPricingList().isEmpty()) {
            throw new ValidationException("Minimum one fee entry is required", "NO_PRICING");
        }
        
        // Check mandatory documents
        Set<String> requiredDocuments = Set.of("AADHAAR", "EDUCATION_CERTIFICATE", "PROFILE_PHOTO");
        if (tutorProfile.getDocuments() == null || tutorProfile.getDocuments().isEmpty()) {
            throw new ValidationException("Mandatory documents must be uploaded", "NO_DOCUMENTS");
        }
        
        Set<String> uploadedDocuments = new HashSet<>();
        for (TutorDocument doc : tutorProfile.getDocuments()) {
            uploadedDocuments.add(doc.getDocumentType().toString());
        }
        
        for (String required : requiredDocuments) {
            if (!uploadedDocuments.contains(required)) {
                throw new ValidationException("Document " + required + " is mandatory", "MISSING_DOCUMENT");
            }
        }
        
        // Check profile completion >= 80%
        double completion = getProfileCompletion(tutorProfile.getUser().getId());
        if (completion < 80.0) {
            throw new ValidationException(
                    String.format("Profile completion must be at least 80%%. Current: %.2f%%", completion),
                    "INCOMPLETE_PROFILE"
            );
        }
        
        // Assign subjects
        for (Long subjectId : request.getSubjectIds()) {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found: " + subjectId));
            
            TutorSubject tutorSubject = new TutorSubject();
            tutorSubject.setTutor(tutorProfile);
            tutorSubject.setSubject(subject);
            
            tutorSubjectRepository.save(tutorSubject);
        }
    }
}
