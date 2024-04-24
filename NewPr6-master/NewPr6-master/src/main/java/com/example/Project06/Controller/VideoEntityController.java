package com.example.Project06.Controller;

import com.example.Project06.Dto.Courses.ResponseGetAllVideoDto;
import com.example.Project06.Dto.Courses.ResponseSingleCoursesDto;
import com.example.Project06.Dto.Courses.VideoEntityDto;
import com.example.Project06.Dto.ResponseDto;
import com.example.Project06.Service.VideoEntityServices;
import com.example.Project06.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/video-entities")
@RequiredArgsConstructor
public class VideoEntityController {

    private final VideoEntityServices videoEntityServices;

    @GetMapping("/findByCoursesId")
    public ResponseEntity<ResponseGetAllVideoDto> findByCoursesId(@RequestParam UUID coursesId) {
        try {
            List<VideoEntityDto> videoEntities = videoEntityServices.findByCoursesId(coursesId);
            ResponseGetAllVideoDto responseDto = new ResponseGetAllVideoDto("Success");
            responseDto.setList(videoEntities);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (PlanNotFoundException e) {
            ResponseGetAllVideoDto responseDto = new ResponseGetAllVideoDto("Unsuccess");
            responseDto.setException("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addVideoEntity(@RequestBody VideoEntityDto videoEntityDto) {
        try {
            String result = videoEntityServices.AddCV(videoEntityDto);
            ResponseDto responseDto = new ResponseDto("success", result);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (PlanNotFoundException e) {
            ResponseDto responseDto = new ResponseDto("error", "Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateVideoEntityFields(
            @RequestBody VideoEntityDto videoEntityDto,
            @RequestParam Integer videoEntityId) {
        try {
            String result = videoEntityServices.updatCSFields(videoEntityDto, videoEntityId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (PlanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseSingleCoursesDto> findById(@RequestParam Integer videoEntityId) {
        try {
            VideoEntityDto videoEntityDto = videoEntityServices.findById(videoEntityId);
            ResponseSingleCoursesDto responseDto = new ResponseSingleCoursesDto("Success", videoEntityDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (PlanNotFoundException e) {
            ResponseSingleCoursesDto responseDto = new ResponseSingleCoursesDto("Unsuccess");
            responseDto.setException("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam Integer videoEntityId) {
        try {
            String result = videoEntityServices.DeleteById(videoEntityId);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (PlanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }
}
