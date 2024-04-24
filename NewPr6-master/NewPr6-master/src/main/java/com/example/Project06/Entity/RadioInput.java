package com.example.Project06.Entity;


import com.example.Project06.Dto.StudentProfileDto.RadioInputDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class RadioInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    private Integer userId;


    public RadioInput(RadioInputDto radioInputDto) {
        this.exMilitary = radioInputDto.getExMilitary();
        this.differentlyAbled = radioInputDto.getDifferentlyAbled();
        this.handledTeam = radioInputDto.getHandledTeam();
        this.willingToRelocate = radioInputDto.getWillingToRelocate();
        this.willingnessToTravel = radioInputDto.getWillingnessToTravel();
        this.university = radioInputDto.getUniversity();
        this.timeSpan = radioInputDto.getTimeSpan();
        this.timeSpan1 = radioInputDto.getTimeSpan1();
        this.category = radioInputDto.getCategory();
        this.workable = radioInputDto.getWorkable();
    }
}
