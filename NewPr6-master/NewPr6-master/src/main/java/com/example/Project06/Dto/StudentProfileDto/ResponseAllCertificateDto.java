package com.example.Project06.Dto.StudentProfileDto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseAllCertificateDto {

    private String message;
    private List<CertificateDto> list;
    private String exception;
    public ResponseAllCertificateDto(String message){
        this.message=message;
    }
}
