package com.example.Project06.Entity;

import com.example.Project06.Dto.StudentProfileDto.DegreeDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Degree {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer degreeId;

    @Column(length = 45)
    private String institute;

    @Column(length = 4)
    private String batchFrom;

    @Column(length = 4)
    private String batchTo;

    @Column(length = 45)
    private String course;

    @Column(length = 45)
    private String degree;

    @Column
    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    public Degree(DegreeDto degreeDto) {
        this.institute = degreeDto.getInstitute();
        this.batchFrom = degreeDto.getBatchFrom();
        this.batchTo = degreeDto.getBatchTo();
        this.course = degreeDto.getCourse();
        this.degree = degreeDto.getDegree();
        this.userId = degreeDto.getUserId();
    }
}
