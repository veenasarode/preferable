package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.Courses.VideoEntityDto;
import com.example.Project06.Entity.VideoEntity;
import com.example.Project06.Repository.CoursesRepo;
import com.example.Project06.Repository.VideoEntityRepo;
import com.example.Project06.Service.VideoEntityServices;
import com.example.Project06.exception.PlanNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoEntityServicesImpl implements VideoEntityServices {

    private final VideoEntityRepo videoEntityRepo;
    private final CoursesRepo coursesRepo;

    @Override
    public List<VideoEntityDto> findByCoursesId(UUID coursesId) {
//        List<VideoEntity> videoEntities = (List<VideoEntity>) videoEntityRepo.findByCoursesId(coursesId)
//                .orElseThrow(() -> new PlanNotFoundException("Courses not found"));
//
//        return videoEntities.stream()
//                .map(VideoEntityDto::new)
//                .collect(Collectors.toList());
//    }
        List<VideoEntity> videoEntities = videoEntityRepo.findByCoursesId(coursesId);

        if (videoEntities.isEmpty()) {
            throw new PlanNotFoundException("No videos found for the given coursesId");
        }

        return videoEntities.stream().map(VideoEntityDto::new).collect(Collectors.toList());
    }

    @Override
    public String AddCV(VideoEntityDto videoEntityDto) {
        if (!coursesRepo.existsById(videoEntityDto.getCoursesId())) {
            throw new PlanNotFoundException("Courses not found for the given coursesId");
        }

        VideoEntity videoEntity = new VideoEntity(videoEntityDto);
        videoEntityRepo.save(videoEntity);

        return "VideoEntity Added by coursesId";
    }

    @Override
    public String updatCSFields(VideoEntityDto videoEntityDto, Integer videoEntityId) {
        VideoEntity videoEntity = videoEntityRepo.findById(videoEntityId)
                .orElseThrow(() -> new PlanNotFoundException("VideoEntity not found"));

        BeanUtils.copyProperties(videoEntityDto, videoEntity, "videoEntityId");

        videoEntityRepo.save(videoEntity);

        return "VideoEntity Updated: " + videoEntityId;
    }

    @Override
    public VideoEntityDto findById(Integer videoEntityId) {
        VideoEntity videoEntity = videoEntityRepo.findById(videoEntityId)
                .orElseThrow(() -> new PlanNotFoundException("VideoEntity not found"));

        return new VideoEntityDto(videoEntity);
    }

    @Override
    public String DeleteById(Integer videoEntityId) {
        VideoEntityDto videoEntityDto = findById(videoEntityId);
        if (videoEntityDto == null) {
            throw new PlanNotFoundException("VideoEntity not found");
        }

        videoEntityRepo.deleteById(videoEntityId);
        return videoEntityId + " VideoEntity has been deleted successfully";
    }
}
