package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.Company.CompanyByUserIdDto;
import com.example.Project06.Dto.Company.NoSuchCompanyFoundException;
import com.example.Project06.Dto.Company.SingleCompanyDto;
import com.example.Project06.Dto.GetAllCompanies;
import com.example.Project06.Entity.Company;
import com.example.Project06.Repository.CompanyRepository;
import com.example.Project06.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;


    @Override
    public List<GetAllCompanies> getAllCompanies(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Company> allcompanies = companyRepository.findAll(pageable);

        List<GetAllCompanies> getAllCompanies = new ArrayList<>();

        for (Company payment : allcompanies) {
            GetAllCompanies paymentDto = convertToDto(payment);
            getAllCompanies.add(paymentDto);
        }

        return getAllCompanies;
    }

    @Override
    public GetAllCompanies findByCompanyId(int companyId) {
        Optional<Company> companyByid = companyRepository.findById(companyId);

        if (companyByid.isEmpty()){
            throw new NoSuchCompanyFoundException("No Such Company Found");
        }

        Company company = companyByid.get();
        return convertToDto(company);
    }

    @Override
    public CompanyByUserIdDto findByUserId(Integer userId) {
        Optional<Company> companyByUserId = companyRepository.findByUserId(userId);

        if (companyByUserId.isEmpty()) {
            throw new NoSuchCompanyFoundException("No Such Company Found for User ID: " + userId);
        }

        Company company = companyByUserId.get();
        return convertToDtoForUserId(company);
    }

    private CompanyByUserIdDto convertToDtoForUserId(Company company) {
        CompanyByUserIdDto companyDto = new CompanyByUserIdDto();
        companyDto.setCompanyServices(company.getCompanyServices());
        companyDto.setCompanyType(company.getCompanyType());
        companyDto.setCompanyLevel(company.getCompanyLevel());
        companyDto.setCompanyStatus(company.getCompanyStatus());
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setCompanyLocations(company.getCompanyLocations());
        companyDto.setLogo(company.getLogo());
        companyDto.setGestnNo(company.getGestnNo());
        companyDto.setOneCopmpanyDoc(company.getOneCopmpanyDoc());
        companyDto.setOfficeAddress(company.getOfficeAddress());
        companyDto.setRefNo(company.getRefNo());
        return companyDto;
    }

    private GetAllCompanies convertToDto(Company company) {
        GetAllCompanies companiesDto = new GetAllCompanies();
        companiesDto.setCompanyServices(company.getCompanyServices());
        companiesDto.setCompanyType(company.getCompanyType());
        companiesDto.setCompanyLevel(company.getCompanyLevel());
        companiesDto.setCompanyStatus(company.getCompanyStatus());
        companiesDto.setCompanyName(company.getCompanyName());
        companiesDto.setCompanyLocations(company.getCompanyLocations());
        companiesDto.setLogo(company.getLogo());
        companiesDto.setGestnNo(company.getGestnNo());
        companiesDto.setOneCompanyDoc(company.getOneCopmpanyDoc());
        companiesDto.setOfficeAddress(company.getOfficeAddress());
        companiesDto.setRefNo(company.getRefNo());
        return companiesDto;
    }

}
