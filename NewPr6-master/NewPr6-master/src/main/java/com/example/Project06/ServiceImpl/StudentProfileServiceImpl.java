package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.StudentProfileDto.GetSingleProfileDto;
import com.example.Project06.Dto.StudentProfileDto.StudentProfileDto;
import com.example.Project06.Entity.*;
import com.example.Project06.Repository.StudentProfRepo;
import com.example.Project06.Repository.UserRepository;
import com.example.Project06.Service.RadioInputService;
import com.example.Project06.Service.StudentProfileService;
import com.example.Project06.exception.UserNotFoundExceptions;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfRepo studentProfRepo;

    private final UserRepository userRepository;

    private final RadioInputService radioInputService;

    @Override
    @Transactional
    public String AddProfile(StudentProfileDto studentProfileDto, Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User user1 = user.get();

            Optional<StudentProfile> existingProfile = studentProfRepo.findByUserUserId(userId);
            if (existingProfile.isPresent()) {
                throw new RuntimeException("Profile already exists for User with Id:" + userId);
            }

            StudentProfile studentProfile = new StudentProfile(studentProfileDto);
            studentProfile.setUserUser(user1);
            studentProfile.setDegrees(studentProfileDto.getDegrees().stream()
                    .map(degreeDto -> {
                        Degree degree = new Degree(degreeDto);
                        degree.setUserId(userId);
                        degree.setStudentProfile(studentProfile);
                        return degree;
                    })
                    .collect(Collectors.toList()));

            studentProfile.setCertificates(studentProfileDto.getCertificates().stream()
                    .map(certDto -> {
                        Certificate certificate = new Certificate(certDto);
                        certificate.setUserId(userId);
                        certificate.setStudentProfile(studentProfile);
                        return certificate;
                    })
                    .collect(Collectors.toList()));

            studentProfRepo.save(studentProfile);

            return "Profile Created Successfully";
        } else {
            throw new UserNotFoundExceptions("User Not Found with Id:" + userId);
        }
    }


    @Override
    public GetSingleProfileDto getProfileById(Integer userId) {

        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User user1 = user.get();
            Optional<StudentProfile> profile = studentProfRepo.findByUserUser(user1);

            if (profile.isPresent()) {
                return convertToDto(profile.get());
            } else {
                throw new RuntimeException("Profile Not Found for User with userId " + userId);
            }
        } else {
            throw new UserNotFoundExceptions("User Not Found with Id:" + userId);
        }
    }

    @Override
    public List<GetSingleProfileDto> getAllProfiles(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<StudentProfile> allProfiles = studentProfRepo.findAll(pageable);

        List<GetSingleProfileDto> getallCalls = new ArrayList<>();

        for (StudentProfile profile : allProfiles) {
            GetSingleProfileDto profileDto = convertToDto(profile);
            getallCalls.add(profileDto);
        }

        return getallCalls;
    }

    @Override
    public void updateProfileDetails(GetSingleProfileDto profileDto, Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<StudentProfile> profileOptional = studentProfRepo.findByUserUser(user);

            if (profileOptional.isPresent()) {
                StudentProfile profile = profileOptional.get();

                if (profileDto.getCourse() != null) {
                    profile.setCourse(profileDto.getCourse());
                }
                if (profileDto.getExperienceType() != null) {
                    profile.setExperienceType(profileDto.getExperienceType());
                }
                if (profileDto.getWorkExperience() != null) {
                    profile.setWorkExperience(profileDto.getWorkExperience());
                }
                if (profileDto.getLastWorkDuration() != null) {
                    profile.setLastWorkDuration(profileDto.getLastWorkDuration());
                }
                if (profileDto.getLastCompanies() != null) {
                    profile.setLastCompanies(profileDto.getLastCompanies());
                }
                if (profileDto.getLastSalary() != null) {
                    profile.setLastSalary(profileDto.getLastSalary());
                }
                if (profileDto.getPreviousDesignation() != null) {
                    profile.setPreviousDesignation(profileDto.getPreviousDesignation());
                }
                if (profileDto.getCareerBreak() != null) {
                    profile.setCareerBreak(profileDto.getCareerBreak());
                }
                if (profileDto.getHighestLevelOfEud() != null) {
                    profile.setHighestLevelOfEud(profileDto.getHighestLevelOfEud());
                }
                if (profileDto.getCurrentLocation() != null) {
                    profile.setCurrentLocation(profileDto.getCurrentLocation());
                }
                if (profileDto.getAvailableToJoin() != null) {
                    profile.setAvailableToJoin(profileDto.getAvailableToJoin());
                }
                if (profileDto.getSpecialization() != null) {
                    profile.setSpecialization(profileDto.getSpecialization());
                }
                if (profileDto.getCourseDuration() != null) {
                    profile.setCourseDuration(profileDto.getCourseDuration());
                }
                if (profileDto.getSkills() != null) {
                    profile.setSkills(profileDto.getSkills());
                }
                if (profileDto.getShortAboutYourself() != null) {
                    profile.setShortAboutYourself(profileDto.getShortAboutYourself());
                }

                if (profileDto.getPreferredSalary() != null) {
                    profile.setPreferredSalary(profileDto.getPreferredSalary());
                }

                studentProfRepo.save(profile);
            } else {
                throw new RuntimeException("Profile not found for the user with userId " + userId);
            }
        } else {
            throw new UserNotFoundExceptions("User not found with Id:" + userId);
        }
    }


    @Override
    public String deleteProfileById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User user1 = user.get();
            Optional<StudentProfile> profile = studentProfRepo.findByUserUser(user1);

            if (profile.isPresent()) {
                studentProfRepo.deleteById(profile.get().getStudentProfileId());
                return "Profile deleted Successfully ";
            } else {
                throw new RuntimeException("Profile Not Found for User with userId " + userId);
            }
        } else {
            throw new UserNotFoundExceptions("User Not Found with Id:" + userId);
        }
    }

    private GetSingleProfileDto convertToDto(StudentProfile studentProfile) {
        GetSingleProfileDto profile = new GetSingleProfileDto();
        profile.setCourse(studentProfile.getCourse());
        profile.setCareerBreak(studentProfile.getCareerBreak());
        profile.setAvailableToJoin(studentProfile.getAvailableToJoin());
        profile.setCourseDuration(studentProfile.getCourseDuration());
        profile.setExperienceType(studentProfile.getExperienceType());
        profile.setCurrentLocation(studentProfile.getCurrentLocation());
        profile.setStudentProfileId(studentProfile.getStudentProfileId());
        profile.setLastSalary(studentProfile.getLastSalary());
        profile.setLastCompanies(studentProfile.getLastCompanies());
        profile.setSkills(studentProfile.getSkills());
        profile.setSpecialization(studentProfile.getSpecialization());
        profile.setPreferredSalary(studentProfile.getPreferredSalary());
        profile.setWorkExperience(studentProfile.getWorkExperience());
        profile.setLastWorkDuration(studentProfile.getLastWorkDuration());
        profile.setPreviousDesignation(studentProfile.getPreviousDesignation());
        profile.setHighestLevelOfEud(studentProfile.getHighestLevelOfEud());
        profile.setShortAboutYourself(studentProfile.getShortAboutYourself());
        return profile;
    }
}

