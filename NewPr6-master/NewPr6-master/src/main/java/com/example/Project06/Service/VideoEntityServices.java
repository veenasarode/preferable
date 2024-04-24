package com.example.Project06.Service;

import com.example.Project06.Dto.Courses.VideoEntityDto;
import com.example.Project06.Dto.Job.JobDto;

import java.util.List;
import java.util.UUID;

public interface VideoEntityServices {
    public List<VideoEntityDto> findByCoursesId(UUID coursesId);


    public String AddCV(VideoEntityDto videoEntityDto);

    public String updatCSFields(VideoEntityDto videoEntityDto,Integer videoEntityId);


    public VideoEntityDto findById(Integer videoEntityId);

    public String DeleteById(Integer videoEntityId);
}
