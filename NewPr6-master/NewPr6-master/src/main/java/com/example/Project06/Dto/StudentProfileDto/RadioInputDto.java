package com.example.Project06.Dto.StudentProfileDto;

import com.example.Project06.Entity.RadioInput;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RadioInputDto {

    private String exMilitary;

    private String differentlyAbled;

    private String handledTeam;

    private String willingToRelocate;

    private String willingnessToTravel;

    private String university;

    private String timeSpan;

    private String timeSpan1;

    private String category;

    private String workable;

    public RadioInputDto(RadioInput radioInput) {
        this.exMilitary = radioInput.getExMilitary();
        this.differentlyAbled = radioInput.getDifferentlyAbled();
        this.handledTeam = radioInput.getHandledTeam();
        this.willingToRelocate = radioInput.getWillingToRelocate();
        this.willingnessToTravel = radioInput.getWillingnessToTravel();
        this.university = radioInput.getUniversity();
        this.timeSpan = radioInput.getTimeSpan();
        this.timeSpan1 = radioInput.getTimeSpan1();
        this.category = radioInput.getCategory();
        this.workable = radioInput.getWorkable();
    }
}
