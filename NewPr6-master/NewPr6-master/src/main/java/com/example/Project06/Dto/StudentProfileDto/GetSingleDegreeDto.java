package com.example.Project06.Dto.StudentProfileDto;

import com.example.Project06.Dto.Event.GetSingleEventDto;
import lombok.Data;

@Data
public class GetSingleDegreeDto {


    private String status;
    private SingleDegreeDto Response;

    public GetSingleDegreeDto(String status) {
        this.status = status;
    }
}
