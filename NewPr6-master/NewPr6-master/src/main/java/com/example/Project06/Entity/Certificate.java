package com.example.Project06.Entity;

import com.example.Project06.Dto.StudentProfileDto.CertificateDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Certificate {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer certificateId;

    @Column(length = 45)
    private String certificate;

    @Column(length = 45)
    private String institute;

    @Column
    private String durationFrom;

    @Column
    private String durationTo;

    @Column(length = 45)
    private String type;

    @Column
    private String validUpto;

    @Column
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    public Certificate(CertificateDto certificateDto) {
        this.certificate = certificateDto.getCertificate();
        this.institute = certificateDto.getInstitute();
        this.durationFrom = certificateDto.getDurationFrom();
        this.durationTo = certificateDto.getDurationTo();
        this.type = certificateDto.getType();
        this.validUpto = certificateDto.getValidUpto();
        this.userId=certificateDto.getUserId();
    }
}
