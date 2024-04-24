package com.example.Project06.Scheduler;

import com.example.Project06.Entity.SelectQuestionAnsStatus;
import com.example.Project06.Repository.SelectQuestionAnsStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SelectQuestionScheduler {

    private final SelectQuestionAnsStatusRepo selectQuestionAnsStatusRepo;
    @Scheduled(fixedRate = 1000)
    public void SelectQuestionSchedulerAns(){
        Boolean flag = false;
        List<SelectQuestionAnsStatus> selectQuestionAnsStatusList = selectQuestionAnsStatusRepo.findByAnsStatus();
        System.out.println(selectQuestionAnsStatusList.size());

        for (int counter = 0;counter< selectQuestionAnsStatusList.size();counter++){
            selectQuestionAnsStatusList.get(counter).setAnsStatus(false);
            flag=true;
        }
        if (flag) selectQuestionAnsStatusRepo.saveAll(selectQuestionAnsStatusList);



//        if (selectQuestionAnsStatusList.size() != 0) {
//            LocalDateTime localDateTimeNow = LocalDateTime.now();
//            for (int counter = 0;counter< selectQuestionAnsStatusList.size();counter++){
//
//                if (localDateTimeNow.isAfter(selectQuestionAnsStatusList.get(counter).getDateTimeExamStart())) {
//                    System.err.println(localDateTimeNow + " is after " + selectQuestionAnsStatusList.get(counter).getDateTimeExamStart());
//                }else if (localDateTimeNow.isBefore(selectQuestionAnsStatusList.get(counter).getDateTimeExamStart())) {
//                    System.err.println(localDateTimeNow + " is before " + selectQuestionAnsStatusList.get(counter).getDateTimeExamStart());
//                } else {
//                    System.err.println(localDateTimeNow + " is equal to " + selectQuestionAnsStatusList.get(counter).getDateTimeExamStart());
//                }
//            }
//        }



    }

}
