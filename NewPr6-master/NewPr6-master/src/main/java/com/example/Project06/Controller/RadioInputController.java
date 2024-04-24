package com.example.Project06.Controller;

import com.example.Project06.Dto.BlogUpdateDto;
import com.example.Project06.Dto.StudentProfileDto.RadioInputDto;
import com.example.Project06.Service.RadioInputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("radioinputs")
public class RadioInputController {

        private final RadioInputService radioInputService;

        @PostMapping("/addRadioDetails")
        public ResponseEntity<RadioInputDto> createRadioInput(@RequestBody RadioInputDto radioInputDto, Integer userId) {
            RadioInputDto createdRadioInput = radioInputService.createRadioInput(radioInputDto, userId);
            return new ResponseEntity<>(createdRadioInput, HttpStatus.CREATED);
        }


        @GetMapping("/getRdInput")
        public ResponseEntity<RadioInputDto> getRadioInput(@RequestParam Integer userId) {
            RadioInputDto radioInputDto = radioInputService.getRadioInputById(userId);
            if (radioInputDto != null) {
                return new ResponseEntity<>(radioInputDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    @PatchMapping("/updateCertificateDetails")
    public ResponseEntity<?> updateDetails(@RequestBody RadioInputDto radioInputDto, Integer radioInputId) {
        try {
            radioInputService.updateRadioInputDetails(radioInputDto, radioInputId);
            BlogUpdateDto userupdateDTO = new BlogUpdateDto("success");
            userupdateDTO.setMessage("Certificate Details Updated Successfully");

            return ResponseEntity.status(HttpStatus.OK).body(userupdateDTO);
        } catch (RuntimeException e) {
            BlogUpdateDto userupdateDTO = new BlogUpdateDto("Unsuccess");
            userupdateDTO.setException(String.valueOf(e));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userupdateDTO);

        }
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteRadioInput(@PathVariable Integer id) {
            boolean deleted = radioInputService.deleteRadioInput(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }


