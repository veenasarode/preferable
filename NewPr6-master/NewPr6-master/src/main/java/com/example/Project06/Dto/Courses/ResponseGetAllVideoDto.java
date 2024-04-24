package com.example.Project06.Dto.Courses;
import lombok.Data;

import java.util.List;

@Data
public class ResponseGetAllVideoDto {

    private String message;
    private List<VideoEntityDto> list;
    private String exception;

    public ResponseGetAllVideoDto(String message) {
        this.message = message;
    }
}
