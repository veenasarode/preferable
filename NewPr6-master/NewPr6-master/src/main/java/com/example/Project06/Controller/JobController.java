package com.example.Project06.Controller;

import com.example.Project06.Dto.ItTraining.ItTrainingDTO;
import com.example.Project06.Dto.ItTraining.ResponseAllItTrainingDTO;
import com.example.Project06.Dto.ItTrianningBooking.ItTrianningBookingDto;
import com.example.Project06.Dto.ItTrianningBooking.ResponseAllItTrainingBookingDTO;
import com.example.Project06.Dto.Job.JobDto;
import com.example.Project06.Dto.Job.ResponseGetAllJobDto;
import com.example.Project06.Dto.Job.ResponseSingleJobDto;
import com.example.Project06.Dto.ResponseDto;
import com.example.Project06.Entity.Job;
import com.example.Project06.Service.JobService;
import com.example.Project06.exception.ItTrainingBookingException;
import com.example.Project06.exception.ItTrainingNotFoundException;
import com.example.Project06.exception.JobNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/job")
@RestController
@RequiredArgsConstructor
public class JobController {


    private final JobService jobService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseDto> jobadded(@RequestBody JobDto jobDto) {
        try {
            String result = jobService.AddJob(jobDto);
            return (ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result)));
        } catch (JobNotFoundException jobNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", "job Not found"));

        }
    }



    @GetMapping("/getJob")
    public ResponseEntity<ResponseSingleJobDto> FindJobById(@RequestParam int JobId) {
        try {
            ResponseSingleJobDto responseSingleJobDto = new ResponseSingleJobDto("success");
             JobDto job = jobService.findById(JobId);
            responseSingleJobDto.setObject(job);
            return ResponseEntity.status(HttpStatus.OK).body(responseSingleJobDto);
        } catch (JobNotFoundException jobNotFoundException) {
            ResponseSingleJobDto responseSingleJobDto = new ResponseSingleJobDto("unsuccess");
            responseSingleJobDto.setException("Job not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseSingleJobDto);
        }
    }


    @PatchMapping("/update")
    public ResponseEntity<ResponseDto> updateJobFields(@RequestBody JobDto jobDto, @RequestParam Integer JobId) {
        try {
            String result = jobService.updateJobFields(jobDto, JobId);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("success", result));
        } catch (JobNotFoundException jobNotFoundException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto("unsuccess", "Job not found"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseGetAllJobDto> getAllJob() {
        try {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            List<JobDto> jobList = jobService.getAlljob();
            responseGetAllJobDto.setList(jobList);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        }catch (ItTrainingNotFoundException itTrainingNotFoundException)
        {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccessful");
            responseGetAllJobDto.setException("Job not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
    }

    @GetMapping("/getByStatus")
    public ResponseEntity<ResponseGetAllJobDto> getByStatus0(@RequestParam  String status) {
        try {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto ("success");
            List<JobDto> itTrainingBookings = jobService.getJobsByStatus(status);
            responseGetAllJobDto.setList(itTrainingBookings);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        } catch (ItTrainingBookingException itTrainingBookingException){
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            responseGetAllJobDto.setException("Job not found");
            return (ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto));
        }
    }



    @GetMapping("/getByUserId")
    public ResponseEntity<ResponseGetAllJobDto> getJobsByUserId(@RequestParam Integer userId) {
        try {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            List<JobDto> jobList = jobService.getByUserId(userId);
            responseGetAllJobDto.setList(jobList);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        } catch (JobNotFoundException jobNotFoundException) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccessful");
            responseGetAllJobDto.setException("Jobs not found for user with ID: " + userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        }
    }

    @GetMapping("/count-by-company")
    public ResponseEntity<Long> getCountByUserId(@RequestParam Integer userId) {
        Long count = jobService.countJobsByUserId(userId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }



    @GetMapping("/getByRefNo")
    public ResponseEntity<ResponseGetAllJobDto> getByRefNo(@RequestParam String refNo) {
        try {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("success");
            List<JobDto> jobList = jobService.getByRefNo(refNo);
            responseGetAllJobDto.setList(jobList);
            return ResponseEntity.status(HttpStatus.OK).body(responseGetAllJobDto);
        }catch (JobNotFoundException jobNotFoundException) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccessful");
            responseGetAllJobDto.setException("Jobs not found for ref no");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseGetAllJobDto);
        } catch (Exception e) {
            ResponseGetAllJobDto responseGetAllJobDto = new ResponseGetAllJobDto("unsuccessful");
            responseGetAllJobDto.setException("An error occurred while fetching jobs by reference number");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseGetAllJobDto);
        }
    }


}
