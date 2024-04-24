package com.example.Project06.Entity;


import com.example.Project06.Dto.Courses.VideoEntityDto;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "videoEntity")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoEntityId;

    @Column(name = "Topic")
    private String topic;

    @Column(name = "CoursesId")
    private UUID coursesId;

    @Column(name = "VideoLinks",nullable = true)
    private String[] VideoLinks;

    public VideoEntity() {
    }

    public VideoEntity(VideoEntityDto videoEntityDto) {
        this.videoEntityId = videoEntityDto.getVideoEntityId();
        this.topic = videoEntityDto.getTopic();
        this.coursesId = videoEntityDto.getCoursesId();
        VideoLinks = videoEntityDto.getVideoLinks();
    }
}
