package com.homevidya.app.homevidya.entities;

import com.homevidya.app.homevidya.enums.ApplicationStatus;
import com.homevidya.app.homevidya.enums.Gender;
import com.homevidya.app.homevidya.enums.TeachingMode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tutor_profiles")
@Getter
@Setter
public class TutorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;
    private String profilePhoto;

    private String city;
    private String area;
    private String fullAddress;
    private String pinCode;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Enumerated(EnumType.STRING)
    private TeachingMode teachingMode;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    // Relationships
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorSubject> subjects;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorClass> classes;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorBoard> boards;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorEducation> educationList;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorExperience> experiences;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorAvailability> availabilityList;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorPricing> pricingList;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<TutorDocument> documents;

}
