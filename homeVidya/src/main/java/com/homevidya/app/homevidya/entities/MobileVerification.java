package com.homevidya.app.homevidya.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mobile_verifications")
@Data
public class MobileVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String mobileNumber;
    
    @Column(nullable = false)
    private boolean verified;
}
