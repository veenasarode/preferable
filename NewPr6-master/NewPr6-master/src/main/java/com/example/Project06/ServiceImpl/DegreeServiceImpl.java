package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.StudentProfileDto.DegreeDto;
import com.example.Project06.Dto.StudentProfileDto.GetSingleDegreeDto;
import com.example.Project06.Dto.StudentProfileDto.SingleDegreeDto;
import com.example.Project06.Entity.Certificate;
import com.example.Project06.Entity.Degree;
import com.example.Project06.Repository.DegreeRepo;
import com.example.Project06.Service.DegreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {

    private final DegreeRepo degreeRepo;

    @Override
    public void updateDegreeDetails(DegreeDto degreeDto, Integer degreeId) {
        Optional<Degree> degreeOptional = degreeRepo.findById(degreeId);

        if (degreeOptional.isPresent()) {
            Degree deg = degreeOptional.get();

            if (degreeDto.getDegree() != null) {
                deg.setDegree(degreeDto.getDegree());
            }
            if (degreeDto.getCourse() != null) {
                deg.setCourse(degreeDto.getCourse());
            }

            if (degreeDto.getBatchTo() != null) {
                deg.setBatchTo(degreeDto.getBatchTo());
            }
            if (degreeDto.getBatchFrom() != null) {
                deg.setBatchFrom(degreeDto.getBatchFrom());
            }
            if (degreeDto.getInstitute() != null) {
                deg.setInstitute(degreeDto.getInstitute());
            }
            degreeRepo.save(deg);
        } else {
            throw new RuntimeException("Degree Details not found");
        }
    }

    @Override
    public List<SingleDegreeDto> getDegreeById(Integer userId) {

        List<Degree> degrees = degreeRepo.findByuserId(userId);

        if (degrees.isEmpty()) {
            throw new RuntimeException("No Degrees Found for the given user");
        }

        return degrees.stream()
                .map(SingleDegreeDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteDegree(Integer degreeId) {
        Optional<Degree> courses = degreeRepo.findById(degreeId);
        if (courses.isEmpty()) {
            throw new RuntimeException("Degree not found");
        }
        degreeRepo.deleteById(degreeId);
        return "Degree Details deleted";
    }
}

