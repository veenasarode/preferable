package com.example.Project06.Dto.Courses;

import com.example.Project06.Dto.Plan.PlanDto;
import com.example.Project06.Entity.Courses;
import lombok.Data;

import java.util.List;
@Data
public class ResponseGetAllCoursesDto {

    private String message;
    private List<Courses> list;
    private String exception;

    public ResponseGetAllCoursesDto(String message) {
        this.message = message;
    }
}
