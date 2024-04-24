package com.example.Project06.Controller;

import com.example.Project06.Dto.BlogUpdateDto;
import com.example.Project06.Dto.ResponseDto;
import com.example.Project06.Dto.StudentProfileDto.CertificateDto;
import com.example.Project06.Dto.StudentProfileDto.ResponseAllCertificateDto;
import com.example.Project06.Dto.StudentProfileDto.ResponseAllDegreeDto;
import com.example.Project06.Service.CertificateService;
import com.example.Project06.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("certificate")
public class CertificateController {

    private final CertificateService certificateService;
    @PatchMapping("/updateCertificateDetails")
    public ResponseEntity<?> updateDetails(@RequestBody CertificateDto certificateDto, Integer certificateId) {
        try {
            certificateService.updateCertificateDetails(certificateDto, certificateId);
            BlogUpdateDto userupdateDTO = new BlogUpdateDto("success");
            userupdateDTO.setMessage("Certificate Details Updated Successfully");

            return ResponseEntity.status(HttpStatus.OK).body(userupdateDTO);
        } catch (RuntimeException e) {
            BlogUpdateDto userupdateDTO = new BlogUpdateDto("Unsuccess");
            userupdateDTO.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);

        }
    }

    @GetMapping("/getCertificate")
    public ResponseEntity<ResponseAllCertificateDto> getDegreesByUserId(@RequestParam Integer userId) {
        try {
            List<CertificateDto> allBlogs = certificateService.getCertificateById(userId);
            ResponseAllCertificateDto responseAllCompnayDto = new ResponseAllCertificateDto("Success");
            responseAllCompnayDto.setList(allBlogs);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllCompnayDto);
        } catch (RuntimeException e) {
            ResponseAllCertificateDto dto = new ResponseAllCertificateDto("Unsuccess");
            dto.setException("No Data Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteASSQ(@RequestParam Integer certificateId) {
        try {
            String result = certificateService.deleteCertificate(certificateId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result));
        } catch (RuntimeException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unSuccess", "Certificate not found"));
        }
    }
}
