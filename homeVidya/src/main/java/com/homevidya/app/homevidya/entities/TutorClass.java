package com.homevidya.app.homevidya.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tutor_classes")
@Getter @Setter
public class TutorClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private TutorProfile tutor;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity clazz;
}
