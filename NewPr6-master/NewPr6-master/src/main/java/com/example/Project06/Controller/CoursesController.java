package com.example.Project06.Controller;
import com.example.Project06.Dto.Courses.CoursesDto;
import com.example.Project06.Dto.Courses.ResponseGetAllCoursesDto;
import com.example.Project06.Dto.Courses.ResponseSingleCoursesDto;
import com.example.Project06.Dto.ResponseDto;
import com.example.Project06.Service.CoursesServices;
import com.example.Project06.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("Courses")
@RequiredArgsConstructor
public class CoursesController {

    private final CoursesServices coursesServices;


    @PostMapping(value = "/add")
    public ResponseEntity<ResponseDto> cAdded(@RequestBody CoursesDto coursesDto) {
        try {
            String result = coursesServices.AddCourses(coursesDto);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result)));
        } catch (PlanNotFoundException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", "courses Not found"));

        }
    }

    @GetMapping("/getbyId")
    public ResponseEntity<ResponseSingleCoursesDto> FindCById(@RequestParam UUID coursesId) {
        try {
            ResponseSingleCoursesDto responseSinglePlanDto = new ResponseSingleCoursesDto("success");
            CoursesDto plan = coursesServices.findById01(coursesId);
            responseSinglePlanDto.setObject(plan);
            return ResponseEntity.status(HttpStatus.OK).body(responseSinglePlanDto);
        } catch (PlanNotFoundException planNotFoundException) {
            ResponseSingleCoursesDto responseSinglePlanDto = new ResponseSingleCoursesDto("unsuccess");
            responseSinglePlanDto.setException("course not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSinglePlanDto);
        }
    }
//     @GetMapping("/getbyId")
//     public ResponseEntity<ResponseSingleCoursesDto> FindCById(@RequestParam UUID coursesId) {
//         try {
//             CoursesDto coursesDto = coursesServices.findById01(coursesId);
//
//
//             VideoEntityDto videoDto = videoEntityServices.findByCoursesId(coursesId);
////             System.out.println(videoDto.toString());
//              coursesDto.setVideo(videoDto);
//             ResponseSingleCoursesDto responseDto = new ResponseSingleCoursesDto("success", coursesDto);
//             return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//         } catch (PlanNotFoundException planNotFoundException) {
//             ResponseSingleCoursesDto responseDto = new ResponseSingleCoursesDto("unsuccess", null);
//             responseDto.setException("course not found");
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
//         }
//     }

//    private Map<String, Object> arrangeObject(CoursesDto coursesDto, VideoEntityDto videoDto) {
//        Map<String, Object> objectMap = new HashMap<>();
//        objectMap.put("coursesId", coursesDto.getCoursesId());
//        objectMap.put("courseName", coursesDto.getCourseName());
//        objectMap.put("topic", coursesDto.getTopic());
//        objectMap.put("courseDetails", coursesDto.getCourseDetails());
//        objectMap.put("price", coursesDto.getPrice());
//
//        // Include video information if available
//        if (videoDto != null) {
//            objectMap.put("video", videoDto.toMap());
//        }

//        return objectMap;
//    }


    @GetMapping("/getAllCourse")

    public ResponseEntity<?> getById() {
        try {
            ResponseGetAllCoursesDto responseObjectDto = new ResponseGetAllCoursesDto("success");
            responseObjectDto.setList(coursesServices.getAllCourse());
            return ResponseEntity.status(HttpStatus.OK).body(responseObjectDto);
        }catch (Exception e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess",e.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updatePlanFields(@RequestBody CoursesDto coursesDto, @RequestParam UUID coursesId) {
        try {
            String result = coursesServices.updateCourses(coursesDto,coursesId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result));
        }catch (PlanNotFoundException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", "courses Not found"));

        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteASSQ(@RequestParam UUID coursesId) {
        try {
            String result = coursesServices.deletePlan(coursesId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result));
        } catch (PlanNotFoundException planNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unSuccess", "courses not found"));
        }
    }

}
