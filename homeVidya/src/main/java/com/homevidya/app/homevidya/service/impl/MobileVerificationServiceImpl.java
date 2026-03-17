package com.homevidya.app.homevidya.service.impl;

import com.homevidya.app.homevidya.repository.MobileVerificationRepository;
import com.homevidya.app.homevidya.service.MobileVerificationService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MobileVerificationServiceImpl implements MobileVerificationService {
    private final MobileVerificationRepository repository;
    
    @Override
    public boolean isMobileVerified(String mobileNumber) {
        return repository.existsByMobileNumberAndVerifiedTrue(mobileNumber);
    }
}
