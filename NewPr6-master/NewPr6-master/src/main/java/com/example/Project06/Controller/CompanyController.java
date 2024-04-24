package com.example.Project06.Controller;

import com.example.Project06.Dto.Company.CompanyByUserIdDto;
import com.example.Project06.Dto.Company.CompanyByUserIdResponseDto;
import com.example.Project06.Dto.Company.NoSuchCompanyFoundException;
import com.example.Project06.Dto.Company.SingleCompanyDto;
import com.example.Project06.Dto.GetAllCompanies;
import com.example.Project06.Dto.ResponseAllCompnayDto;
import com.example.Project06.Dto.StudentProfileDto.ResponseAllDegreeDto;
import com.example.Project06.Dto.StudentProfileDto.SingleDegreeDto;
import com.example.Project06.Dto.UserupdateDTO;
import com.example.Project06.Service.CompanyService;
import com.example.Project06.exception.PageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("company")
public class CompanyController {

    private final CompanyService companyService;
    @GetMapping("/GetAllCompanies")
    public ResponseEntity<ResponseAllCompnayDto> getAllCompanies(@RequestParam int  pageNo,
                                                                 @RequestParam(value = "pageSize" , defaultValue = "10") int pageSize) {
        try {
            List<GetAllCompanies> companies = companyService.getAllCompanies(pageNo, pageSize);
            ResponseAllCompnayDto responseAllCompnayDto = new ResponseAllCompnayDto("Success");
            responseAllCompnayDto.setList(companies);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllCompnayDto);
        }catch(PageNotFoundException e) {
         ResponseAllCompnayDto dto = new ResponseAllCompnayDto("Unsuccess");
         dto.setException("Page Not Found");
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }

    }

    @GetMapping("GetCompanyById")
    public ResponseEntity<?> GetCompanyById (@RequestParam Integer companyId){
      try {
          SingleCompanyDto singleCompany = new SingleCompanyDto("Success");
          singleCompany.setResponse(companyService.findByCompanyId(companyId));
          return ResponseEntity.status(HttpStatus.OK).body(singleCompany);
      } catch (NoSuchCompanyFoundException e){
          UserupdateDTO userupdateDTO = new UserupdateDTO("Unsuccess");
          userupdateDTO.setException("No Such Company Found");
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);
      }

    }
    @GetMapping("/GetCompanyByUserId")
    public ResponseEntity<?> getCompanyByUserId(@RequestParam Integer userId) {
        try {
            CompanyByUserIdDto companyByUserIdDto = companyService.findByUserId(userId);
            CompanyByUserIdResponseDto responseDto = new CompanyByUserIdResponseDto("Success");
            responseDto.setResponseData(companyByUserIdDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (NoSuchCompanyFoundException e) {
            UserupdateDTO userupdateDTO = new UserupdateDTO("Unsuccess");
            userupdateDTO.setException("No Such Company Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);
        }
    }
}

