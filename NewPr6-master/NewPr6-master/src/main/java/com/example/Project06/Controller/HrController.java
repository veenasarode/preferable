package com.example.Project06.Controller;

import com.example.Project06.Dto.Company.CompanyByUserIdDto;
import com.example.Project06.Dto.Company.CompanyByUserIdResponseDto;
import com.example.Project06.Dto.Company.NoSuchCompanyFoundException;
import com.example.Project06.Dto.ResponseDto;
import com.example.Project06.Dto.UserupdateDTO;
import com.example.Project06.Dto.hr.HrDto;
import com.example.Project06.Dto.hr.ResponseAllHrDtos;
import com.example.Project06.Dto.hr.ResponseHrSingleDto;
import com.example.Project06.Service.IHr;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
@AllArgsConstructor
public class HrController {

    private final IHr iHr;

    @PostMapping("/post")
    public ResponseEntity<?> saveBootcampDetails(@RequestBody HrDto hrDto)
    {
        try {

            iHr.saveHrDetails(hrDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success","hr details added"));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("unsuccess",e.getMessage()));

        }
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(Integer hrId)
    {


        try {
            ResponseHrSingleDto responseSingleDto = new ResponseHrSingleDto("success");
            responseSingleDto.setResponse(iHr.getBootcampDetailsById(hrId));
            return ResponseEntity.status(HttpStatus.OK).body(responseSingleDto);
        }catch (RuntimeException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

    @GetMapping("/getAllHrDetails")

    public ResponseEntity<?> getById() {
        try {
            ResponseAllHrDtos responseObjectDto = new ResponseAllHrDtos("success");
            responseObjectDto.setResponse(iHr.getAllBootcampDetails());
            return ResponseEntity.status(HttpStatus.OK).body(responseObjectDto);
        }catch (RuntimeException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateById(

            @RequestParam String digignastion,

            @RequestParam String status,

            @RequestParam String refNoOfCompany,

            @RequestParam Integer hrId



    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",iHr.updateBootcampDetails(
                    digignastion,status,refNoOfCompany,hrId

            )));
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById( @RequestParam Integer hrId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success",iHr.deleteById(hrId)));
        }catch (RuntimeException e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }catch (Exception e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

    @GetMapping("/GetHrByRefId")
    public ResponseEntity<?> getCompanyByUserId(@RequestParam String refNoOfCompany) {
        try {
            ResponseAllHrDtos responseSingleDto = new ResponseAllHrDtos("success");
            responseSingleDto.setResponse(iHr.findByRefId(refNoOfCompany));
            return ResponseEntity.status(HttpStatus.OK).body(responseSingleDto);
        } catch (NoSuchCompanyFoundException e) {
            UserupdateDTO userupdateDTO = new UserupdateDTO("Unsuccess");
            userupdateDTO.setException("No Such Company Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);
        }
    }

}
