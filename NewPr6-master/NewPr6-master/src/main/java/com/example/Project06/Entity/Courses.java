package com.example.Project06.Entity;
import com.example.Project06.Dto.Courses.CoursesDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Courses")
public class Courses {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "courses_id", columnDefinition = "BINARY(16)")
    private UUID coursesId;

    @Column(name = "CourseName")
    private String courseName;

    @Column(name = "Topic")
    private String topic;

    @Column(name = "CourseDetails")
    private String courseDetails;

    @Column(name = "Price")
    private String price;




    public Courses(CoursesDto coursesDto) {
        this.coursesId = coursesDto.getCoursesId();
        this.courseName = coursesDto.getCourseName();
        this.topic = coursesDto.getTopic();
        this.courseDetails = coursesDto.getCourseDetails();
        this.price = coursesDto.getPrice();

    }
}
