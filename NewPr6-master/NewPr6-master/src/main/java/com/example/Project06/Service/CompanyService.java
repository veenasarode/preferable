package com.example.Project06.Service;

import com.example.Project06.Dto.Company.CompanyByUserIdDto;
import com.example.Project06.Dto.Company.NoSuchCompanyFoundException;
import com.example.Project06.Dto.Company.SingleCompanyDto;
import com.example.Project06.Dto.GetAllCompanies;

import java.util.List;

public interface CompanyService {

    List<GetAllCompanies> getAllCompanies(int pageNo, int pageSize);

    GetAllCompanies findByCompanyId(int companyId);

    CompanyByUserIdDto findByUserId(Integer userId);

}
