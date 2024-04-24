package com.example.Project06.Dto.Company;

import lombok.Data;

@Data
public class CompanyByUserIdResponseDto {

    private String status;
    private CompanyByUserIdDto responseData;

    public CompanyByUserIdResponseDto(String status) {
        this.status = status;
    }

}
