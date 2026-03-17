package com.homevidya.app.homevidya.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tutor_education")
@Getter @Setter
public class TutorEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private TutorProfile tutor;

    private String highestQualification;
    private String degreeName;
    private String university;
    private Integer passingYear;

    private String certificateUrl;
}
