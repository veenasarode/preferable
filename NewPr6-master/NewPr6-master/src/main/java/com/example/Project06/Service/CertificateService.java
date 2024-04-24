package com.example.Project06.Service;

import com.example.Project06.Dto.StudentProfileDto.CertificateDto;

import java.util.List;
import java.util.UUID;

public interface CertificateService {
    public void updateCertificateDetails (CertificateDto profileDto, Integer certificateId);

    List<CertificateDto> getCertificateById(Integer userId);

    public String deleteCertificate(Integer certificateId);

}
