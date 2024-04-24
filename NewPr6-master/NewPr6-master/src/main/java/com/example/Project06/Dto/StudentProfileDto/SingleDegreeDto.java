package com.example.Project06.Dto.StudentProfileDto;

import com.example.Project06.Entity.Degree;
import lombok.Data;

@Data
public class SingleDegreeDto {

    private Integer degreeId;

    private String institute;

    private String batchFrom;

    private String batchTo;

    private String course;

    private String degree;

    private Integer userId;

    public SingleDegreeDto(Degree degree) {
        this.degreeId = degree.getDegreeId();
        this.institute = degree.getInstitute();
        this.batchFrom = degree.getBatchFrom();
        this.batchTo = degree.getBatchTo();
        this.course = degree.getCourse();
        this.degree = degree.getDegree();
        this.userId = degree.getUserId();
    }
}
