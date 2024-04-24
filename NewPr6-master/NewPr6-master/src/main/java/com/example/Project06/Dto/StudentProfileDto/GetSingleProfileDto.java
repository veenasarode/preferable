package com.example.Project06.Dto.StudentProfileDto;

import com.example.Project06.Entity.StudentProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class GetSingleProfileDto {

        private Integer StudentProfileId;

        private String experienceType;

        private String workExperience;

        private String lastWorkDuration;

        private String[] lastCompanies;

        private String lastSalary;

        private String previousDesignation;

        private String careerBreak;

        private String highestLevelOfEud;

        private String currentLocation;

        private String availableToJoin;

        private String specialization;

        private String course;

        private String courseDuration;

        private String[] skills;

        private String shortAboutYourself;

        private String preferredSalary;

    private List<CertificateDto> certificates;

    private List<DegreeDto> degrees;

        public GetSingleProfileDto(StudentProfile studentProfile) {
            this.StudentProfileId = studentProfile.getStudentProfileId();
            this.experienceType = studentProfile.getExperienceType();
            this.workExperience = studentProfile.getWorkExperience();
            this.lastWorkDuration = studentProfile.getLastWorkDuration();
            this.lastCompanies = studentProfile.getLastCompanies();
            this.lastSalary = studentProfile.getLastSalary();
            this.previousDesignation = studentProfile.getPreviousDesignation();
            this.careerBreak = studentProfile.getCareerBreak();
            this.highestLevelOfEud = studentProfile.getHighestLevelOfEud();
            this.currentLocation = studentProfile.getCurrentLocation();
            this.availableToJoin = studentProfile.getAvailableToJoin();
            this.specialization = studentProfile.getSpecialization();
            this.course = studentProfile.getCourse();
            this.courseDuration = studentProfile.getCourseDuration();
            this.skills = studentProfile.getSkills();
            this.shortAboutYourself = studentProfile.getShortAboutYourself();
            this.preferredSalary = studentProfile.getPreferredSalary();

            this.certificates = studentProfile.getCertificates().stream()
                    .map(certificate -> new CertificateDto(certificate))
                    .collect(Collectors.toList());

            this.degrees = studentProfile.getDegrees().stream()
                    .map(degree -> new DegreeDto(degree))
                    .collect(Collectors.toList());
        }
    }


