package com.example.Project06.Service;

import com.example.Project06.Dto.Courses.CoursesDto;
import com.example.Project06.Entity.Courses;

import java.util.List;
import java.util.UUID;

public interface CoursesServices {

    public String AddCourses(CoursesDto coursesDto);

    public String EditCourses(CoursesDto coursesDto, UUID coursesId);

    public String updateCourses(CoursesDto coursesDto, UUID coursesId);

    public List<Courses> getAllCourse();

    public CoursesDto findById01(UUID coursesId);

    public String deletePlan(UUID coursesId);


}
