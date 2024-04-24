package com.example.Project06.Service;

import com.example.Project06.Dto.StudentProfileDto.CertificateDto;
import com.example.Project06.Dto.StudentProfileDto.RadioInputDto;

public interface RadioInputService {

    RadioInputDto createRadioInput(RadioInputDto radioInputDto, Integer userId);
    RadioInputDto getRadioInputById(Integer userId);
    public void updateRadioInputDetails (RadioInputDto radioInputDto, Integer radioInputId);
    boolean deleteRadioInput(Integer id);
}
