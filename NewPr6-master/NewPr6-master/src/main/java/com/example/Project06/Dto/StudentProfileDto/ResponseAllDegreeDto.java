package com.example.Project06.Dto.StudentProfileDto;

import lombok.Data;

import java.util.List;
@Data
public class ResponseAllDegreeDto {

    private String message;
    private List<SingleDegreeDto> list;
    private String exception;

    public ResponseAllDegreeDto(String message){
        this.message=message;
    }
}
