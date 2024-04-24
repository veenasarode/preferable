package com.example.Project06.Service;

import com.example.Project06.Dto.StudentProfileDto.DegreeDto;
import com.example.Project06.Dto.StudentProfileDto.GetSingleDegreeDto;
import com.example.Project06.Dto.StudentProfileDto.SingleDegreeDto;

import java.util.List;

public interface DegreeService {
    public void updateDegreeDetails (DegreeDto degreeDto,Integer degreeId);

     List<SingleDegreeDto> getDegreeById(Integer userId);

    public String deleteDegree(Integer degreeId);
}
