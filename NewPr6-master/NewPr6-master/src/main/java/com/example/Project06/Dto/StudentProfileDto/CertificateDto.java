package com.example.Project06.Dto.StudentProfileDto;

import com.example.Project06.Entity.Certificate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CertificateDto {

    private Integer certificateId;

    private String certificate;

    private String institute;

    private String durationFrom;

    private String durationTo;

    private String type;

    private String validUpto;

    private Integer userId;

    public CertificateDto(Certificate certificate) {
        this.certificateId = certificate.getCertificateId();
        this.certificate = certificate.getCertificate();
        this.institute = certificate.getInstitute();
        this.durationFrom = certificate.getDurationFrom();
        this.durationTo = certificate.getDurationTo();
        this.type = certificate.getType();
        this.validUpto = certificate.getValidUpto();
        this.userId = certificate.getUserId();
    }
}
