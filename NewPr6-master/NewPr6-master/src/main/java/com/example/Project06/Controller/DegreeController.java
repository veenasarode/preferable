package com.example.Project06.Controller;

import com.example.Project06.Dto.*;
import com.example.Project06.Dto.Event.EventUpdateDto;
import com.example.Project06.Dto.Event.SingleEventDto;
import com.example.Project06.Dto.StudentProfileDto.DegreeDto;
import com.example.Project06.Dto.StudentProfileDto.GetSingleDegreeDto;
import com.example.Project06.Dto.StudentProfileDto.ResponseAllDegreeDto;
import com.example.Project06.Dto.StudentProfileDto.SingleDegreeDto;
import com.example.Project06.Entity.Blogs;
import com.example.Project06.Service.DegreeService;
import com.example.Project06.exception.BlogNotFoundException;
import com.example.Project06.exception.EventNotFoundException;
import com.example.Project06.exception.PageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("degree")
public class DegreeController {

    private final DegreeService degreeService;

    @PatchMapping("/updateDegreeDetails")
    public ResponseEntity<?> updateDetails(@RequestBody DegreeDto degreeDto, Integer degreeId) {
        try {
            degreeService.updateDegreeDetails(degreeDto, degreeId);
            BlogUpdateDto userupdateDTO = new BlogUpdateDto("success");
            userupdateDTO.setMessage("Degree Details Updated Successfully");

            return ResponseEntity.status(HttpStatus.OK).body(userupdateDTO);
        } catch (RuntimeException e) {
            BlogUpdateDto userupdateDTO = new BlogUpdateDto("Unsuccess");
            userupdateDTO.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);

        }
    }

    @GetMapping("/getDegree")
    public ResponseEntity<ResponseAllDegreeDto> getDegreesByUserId(@RequestParam Integer userId) {
        try {
            List<SingleDegreeDto> allBlogs = degreeService.getDegreeById(userId);
            ResponseAllDegreeDto responseAllCompnayDto = new ResponseAllDegreeDto("Success");
            responseAllCompnayDto.setList(allBlogs);
            return ResponseEntity.status(HttpStatus.OK).body(responseAllCompnayDto);
        } catch (RuntimeException e) {
            ResponseAllDegreeDto dto = new ResponseAllDegreeDto("Unsuccess");
            dto.setException("No Data Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteASSQ(@RequestParam Integer degreeId) {
        try {
            String result = degreeService.deleteDegree(degreeId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result));
        } catch (RuntimeException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unSuccess", "Degree not found"));
        }
    }

}
