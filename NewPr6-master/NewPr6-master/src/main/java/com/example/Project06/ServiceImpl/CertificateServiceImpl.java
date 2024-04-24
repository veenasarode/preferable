package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.StudentProfileDto.CertificateDto;
import com.example.Project06.Dto.StudentProfileDto.SingleDegreeDto;
import com.example.Project06.Entity.Certificate;
import com.example.Project06.Entity.Courses;
import com.example.Project06.Entity.Degree;
import com.example.Project06.Repository.CertificateRepo;
import com.example.Project06.Service.CertificateService;
import com.example.Project06.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

     private final CertificateRepo certificateRepo;
    @Override
    public void updateCertificateDetails(CertificateDto certificateDto, Integer certificateId) {

            Optional<Certificate> certificateOptional = certificateRepo.findById(certificateId);

            if (certificateOptional.isPresent()) {
                Certificate cert = certificateOptional.get();

                if (certificateDto.getCertificate() != null) {
                    cert.setCertificate(certificateDto.getCertificate());
                }
                if (certificateDto.getType() != null) {
                    cert.setType(certificateDto.getType());
                }
                if (certificateDto.getInstitute() != null) {
                    cert.setInstitute(certificateDto.getInstitute());
                }
                if (certificateDto.getDurationFrom() != null) {
                    cert.setDurationFrom(certificateDto.getDurationFrom());
                }
                if (certificateDto.getDurationTo() != null) {
                    cert.setDurationTo(certificateDto.getDurationTo());
                }
                if (certificateDto.getValidUpto() != null) {
                    cert.setValidUpto(certificateDto.getValidUpto());
                }

                certificateRepo.save(cert);
            } else {
                throw new RuntimeException("Certificate not found for the user with certificateId " + certificateId);
            }
        }

    @Override
    public List<CertificateDto> getCertificateById(Integer userId) {
        List<Certificate> degrees = certificateRepo.findByuserId(userId);

        if (degrees.isEmpty()) {
            throw new RuntimeException("No Degrees Found for the given user");
        }

        return degrees.stream()
                .map(CertificateDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteCertificate(Integer certificateId) {
        Optional<Certificate> courses = certificateRepo.findById(certificateId);
        if (courses.isEmpty()) {
            throw new RuntimeException("Certificate not found");
        }
        certificateRepo.deleteById(certificateId);
        return "Certificate Details deleted";
    }
}


