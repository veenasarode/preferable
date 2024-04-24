package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.Courses.CoursesDto;
import com.example.Project06.Entity.Courses;
import com.example.Project06.Repository.CoursesRepo;
import com.example.Project06.Service.CoursesServices;
import com.example.Project06.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CoursesServesImpl implements CoursesServices {
//    @Autowired
    private final CoursesRepo coursesRepo;




    @Override
    public String AddCourses(CoursesDto coursesDto) {
        Courses courses= new Courses(coursesDto);
        coursesRepo.save(courses);
        return "Course added...";
    }

    @Override
    public String EditCourses(CoursesDto coursesDto, UUID coursesId) {
        return null;
    }

    @Override
    public String updateCourses(CoursesDto coursesDto, UUID coursesId) {

        Courses courses = coursesRepo.findById(coursesId)
                .orElseThrow(() -> new PlanNotFoundException("course Not Found", HttpStatus.NOT_FOUND));

        if (coursesDto.getCourseDetails() != null) {
            courses.setCourseDetails(coursesDto.getCourseDetails());
        }
        if (coursesDto.getCourseName() != null) {
            courses.setCourseName(coursesDto.getCourseName());
        }
        if (coursesDto.getPrice() != null) {
            courses.setPrice(coursesDto.getPrice());
        }
        if (coursesDto.getTopic() != null) {
            courses.setTopic(coursesDto.getTopic());
        }


        coursesRepo.save(courses);

        return "Plan Updated" ;
    }

    @Override
    public List<Courses> getAllCourse() {return this.coursesRepo.findAll();}


    @Override
    public CoursesDto findById01(UUID coursesId) {
        Optional<Courses> courses = coursesRepo.findById(coursesId);
        if (courses.isEmpty()) {
            throw new PlanNotFoundException("courses not found", HttpStatus.NOT_FOUND);
        }
        CoursesDto coursesDto =new CoursesDto(courses.get());

        coursesDto.setCoursesId(coursesId);
        return coursesDto;


    }
//@Override
//public CoursesDto findById01(UUID coursesId) {
//    Optional<Courses> courses = coursesRepo.findById(coursesId);
//    if (courses.isEmpty()) {
//        throw new PlanNotFoundException("Plan not found", HttpStatus.NOT_FOUND);
//    }
//
//    CoursesDto coursesDto = new CoursesDto(courses.get());
//
//    Optional<VideoEntity> videoEntityOptional = videoEntityRepo.findByCoursesId(coursesId);
//    VideoEntityDto videoEntityDto = videoEntityOptional.map(VideoEntityDto::new).orElse(null);
//    coursesDto.setVideo(videoEntityDto);
//
//    return coursesDto;
//}

    @Override
    public String deletePlan(UUID coursesId) {
        Optional<Courses> courses = coursesRepo.findById(coursesId);
        if (courses.isEmpty()) {
            throw new PlanNotFoundException("courses not found", HttpStatus.NOT_FOUND);
        }
        coursesRepo.deleteById(coursesId);
        return "courses deleted";
    }
}
