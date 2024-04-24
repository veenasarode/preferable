package com.example.Project06.Dto.Courses;

import com.example.Project06.Entity.VideoEntity;
import lombok.Data;
import java.util.UUID;

@Data
public class VideoEntityDto {

    private Integer videoEntityId;
    private String topic;
    private UUID coursesId;
    private String[] videoLinks;

    public VideoEntityDto() {
    }

    public VideoEntityDto(VideoEntity videoEntity) {
        this.videoEntityId = videoEntity.getVideoEntityId();
        this.topic = videoEntity.getTopic();
        this.coursesId = videoEntity.getCoursesId();
        this.videoLinks = videoEntity.getVideoLinks();
    }
}
