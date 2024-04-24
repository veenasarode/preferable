package com.example.Project06.Dto.Company;

import com.example.Project06.Dto.GetAllCompanies;
import lombok.Data;

@Data
public class CompanyByUserIdDto  {

    private String companyServices;
    private String companyType;
    private String companyLevel;
    private String companyStatus;
    private String companyName;
    private String companyLocations;
    private String logo;
    private String gestnNo;
    private String oneCopmpanyDoc;
    private String officeAddress;
    private String refNo;
}
