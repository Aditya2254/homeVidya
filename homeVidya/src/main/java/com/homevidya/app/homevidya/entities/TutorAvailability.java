package com.homevidya.app.homevidya.entities;

import com.homevidya.app.homevidya.enums.TeachingLocation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tutor_availability")
@Getter @Setter
public class TutorAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private TutorProfile tutor;

    @Enumerated(EnumType.STRING)
    private TeachingLocation teachingLocation;

    private String preferredAreas;

    private String travelDistance; // 2,5,10,15

    private String availableDays; // MON,TUE,...

    private LocalTime startTime;
    private LocalTime endTime;
}
