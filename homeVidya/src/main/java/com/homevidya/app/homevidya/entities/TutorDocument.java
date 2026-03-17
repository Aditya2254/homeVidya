package com.homevidya.app.homevidya.entities;

import com.homevidya.app.homevidya.enums.DocumentType;
import com.homevidya.app.homevidya.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tutor_documents")
@Getter @Setter
public class TutorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id")
    private TutorProfile tutor;

    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    private String documentNumber;

    private String fileUrl;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;
}
