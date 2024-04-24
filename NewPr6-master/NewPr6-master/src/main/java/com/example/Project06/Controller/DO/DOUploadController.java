package com.example.Project06.Controller.DO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.example.Project06.Dto.DocumentDto;
import com.example.Project06.Dto.ResponceDto;
import com.example.Project06.Dto.ResponseAllDocument;
import com.example.Project06.Service.IDocument;

import com.example.Project06.ServiceImpl.DOService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploadFile")
@RequiredArgsConstructor
@Tag(name = "DIGITAL_OCEAN_FOR_IMAGE_UPLOAD")
public class DOUploadController {
    private final IDocument iDocument;
    @Value("${do.CDN.No}")
    private String CDNNo;
    private final String uploadDir = "uploads";
    private com.example.Project06.ServiceImpl.DOService DOService = new DOService();
    private final String NODEJS_SERVER_URL = "https://digitaloceannodeservice.up.railway.app" ; // Change this to your Node.js server's URL
    private final RestTemplate restTemplate = new RestTemplate();


    @PostMapping("/add")
        public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam String documentType, @RequestParam Integer userId) throws InvalidKeyException, NoSuchAlgorithmException {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir, fileName);

            System.out.println("File path: " + filePath.toString());

            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            // Save the uploaded file to the specified directory
            file.transferTo(filePath);

            byte[] imageBytes = file.getBytes();
            Files.delete(filePath);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> payloadObject = new HashMap<>();
            payloadObject.put("imageBytes", imageBytes);
            payloadObject.put("contentType", file.getContentType());
            payloadObject.put("contentLength", imageBytes.length);
            String uniqueName = this.DOService.generateRandomString(15) + fileName;
            System.err.println(fileName.length());
            payloadObject.put("imageName", uniqueName);
            if (uniqueName.isEmpty()) {
                throw new RuntimeException("Document not found");
            }
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(payloadObject, httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(
                    NODEJS_SERVER_URL + "/forward-image",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

//            System.err.println();

//            String arr[] = documentDto.split(",");
//            System.out.println("1"+arr[0]);
//            System.out.println("2"+arr[1]);
//            System.out.println(response.getStatusCode());
            String serviceResponse = null;

            if (!response.getBody().isEmpty()) {
//                JSONArray jsonArray = new JSONArray(documentJSONArray);
//                JSONObject jsonArray = new JSONObject(documentJSONArray);

//                System.out.println(jsonArray.toString());
                DocumentDto documentDto = new DocumentDto();
                documentDto.setUserId(userId);

                documentDto.setDocumentType(documentType);

                documentDto.setDocumentLink(CDNNo + "/" + response.getBody());
                serviceResponse = iDocument.addDocument(documentDto);
            }


            return ResponseEntity.status(HttpStatus.OK).body(new ResponceDto("success", serviceResponse));
        } catch (RuntimeException e) {
//            System.err.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", String.valueOf(e)));

        } catch (IOException e) {

            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponceDto("unsuccess", "Failed to upload image"));
        } catch (Exception e) {

            System.err.println(e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponceDto("unsuccess", "Failed to upload image"));

        }

    }

    @GetMapping("/getDocuments")
    private ResponseEntity<?> getDocumentByUserIdAndDocId(@RequestParam Integer userId, @RequestParam String DocumentType) {
        try {
            ResponseAllDocument responseAllDocument = new ResponseAllDocument("success");
            responseAllDocument.setResponse(iDocument.getAllDocument(userId, DocumentType));
            return ResponseEntity.status(HttpStatus.OK).body(responseAllDocument);
        } catch (Exception e) {
            System.err.println(e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", String.valueOf(e)));

        }
    }

    @GetMapping("getByUserId")
        private ResponseEntity<?> getByUserId(@RequestParam Integer userId) {
        try {
            ResponseAllDocument responseDocument = new ResponseAllDocument("success");
            responseDocument.setResponse(iDocument.getByUserId(userId));
            return ResponseEntity.status(HttpStatus.OK).body(responseDocument);
        } catch (Exception e) {
            System.err.println(e);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponceDto("unsuccess", String.valueOf(e)));

        }
    }
}