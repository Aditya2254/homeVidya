package com.homevidya.app.homevidya.repository;

import com.homevidya.app.homevidya.entity.MobileVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileVerificationRepository extends JpaRepository<MobileVerification, Long> {
    boolean existsByMobileNumberAndVerifiedTrue(String mobileNumber);
}
