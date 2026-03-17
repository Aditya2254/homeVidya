package com.homevidya.app.homevidya.entities;

import com.homevidya.app.homevidya.enums.WorkType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tutor_experience")
@Getter @Setter
public class TutorExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private TutorProfile tutor;

    private Double totalExperience;

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    @Column(columnDefinition = "TEXT")
    private String description;
}
