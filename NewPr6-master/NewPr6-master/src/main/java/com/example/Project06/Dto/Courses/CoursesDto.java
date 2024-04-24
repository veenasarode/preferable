package com.example.Project06.Dto.Courses;

import com.example.Project06.Entity.Courses;
import com.example.Project06.Entity.VideoEntity;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class CoursesDto {

    private UUID coursesId;
    private String courseName;
    private String topic;
    private String courseDetails;
    private String price;

    public CoursesDto() {
    }

    public CoursesDto(Courses courses) {
        this.coursesId = courses.getCoursesId();
        this.courseName = courses.getCourseName();
        this.topic = courses.getTopic();
        this.courseDetails = courses.getCourseDetails();
        this.price = courses.getPrice();
    }


    public void setVideo(VideoEntityDto videoEntityDto) {
    }
}
