package com.example.Project06.Dto.Courses;

import lombok.Data;

@Data
public class ResponseSingleCoursesDto {

    private String message;
    private Object object;
    private String exception;

    public ResponseSingleCoursesDto(String message, Object object) {
        this.message = message;
        this.object = object;
    }


    public ResponseSingleCoursesDto(String success) {
    }
}
