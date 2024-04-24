package com.example.Project06.ServiceImpl;

import com.example.Project06.Dto.StudentProfileDto.CertificateDto;
import com.example.Project06.Dto.StudentProfileDto.RadioInputDto;
import com.example.Project06.Entity.Certificate;
import com.example.Project06.Entity.RadioInput;
import com.example.Project06.Repository.RadioInputRepository;
import com.example.Project06.Service.RadioInputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RadioInputServiceImpl implements RadioInputService {

    private final RadioInputRepository radioInputRepository;

    @Override
    public RadioInputDto createRadioInput(RadioInputDto radioInputDto, Integer userId) {
        RadioInput plan = new RadioInput(radioInputDto);
        plan.setExMilitary(radioInputDto.getExMilitary());
        plan.setUserId(userId);
        RadioInput savedRadioInput = radioInputRepository.save(plan);
        return convertToDto(savedRadioInput);

    }

    @Override
    public RadioInputDto getRadioInputById(Integer userId) {
        return radioInputRepository.findByuserId(userId)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public void updateRadioInputDetails(RadioInputDto radioInputDto, Integer radioInputId) {

        Optional<RadioInput> rdOptional = radioInputRepository.findById(radioInputId);

        if (rdOptional.isPresent()) {
            RadioInput rdin = rdOptional.get();

            if (radioInputDto.getExMilitary() != null) {
                rdin.setExMilitary(radioInputDto.getExMilitary());
            }
            if (radioInputDto.getDifferentlyAbled() != null) {
                rdin.setDifferentlyAbled(radioInputDto.getDifferentlyAbled());
            }
            if (radioInputDto.getHandledTeam() != null) {
                rdin.setHandledTeam(radioInputDto.getHandledTeam());
            }
            if (radioInputDto.getWillingToRelocate() != null) {
                rdin.setWillingToRelocate(radioInputDto.getWillingToRelocate());
            }
            if (radioInputDto.getWillingnessToTravel() != null) {
                rdin.setWillingnessToTravel(radioInputDto.getWillingnessToTravel());
            }
            if (radioInputDto.getUniversity() != null) {
                rdin.setUniversity(radioInputDto.getUniversity());
            }
            if (radioInputDto.getTimeSpan() != null) {
                rdin.setTimeSpan(radioInputDto.getTimeSpan());
            }
            if (radioInputDto.getTimeSpan1() != null) {
                rdin.setTimeSpan1(radioInputDto.getTimeSpan1());
            }
            if (radioInputDto.getCategory() != null) {
                rdin.setCategory(radioInputDto.getCategory());
            }
            if (radioInputDto.getExMilitary() != null) {
                rdin.setExMilitary(radioInputDto.getExMilitary());
            }
            radioInputRepository.save(rdin);

        } else {
            throw new RuntimeException("No Matching Data found");
        }
    }



    @Override
    public boolean deleteRadioInput(Integer id) {
        if (radioInputRepository.existsById(id)) {
            radioInputRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    private RadioInputDto convertToDto(RadioInput radioInput) {
        return new RadioInputDto(radioInput);    }

    private RadioInput convertToEntity(RadioInputDto radioInputDto) {
        return new RadioInput();
    }
}
