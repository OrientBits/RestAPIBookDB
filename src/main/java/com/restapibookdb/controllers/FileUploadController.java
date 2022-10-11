package com.restapibookdb.controllers;

import com.restapibookdb.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("fileKey") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println(file.getName());
        System.out.println(file.getContentType());

        //validation
        if (file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
        }

        if (!file.getContentType().equals("image/jpeg")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only JPEG allowed");
        }

        try {
            //now uploading.....
            boolean isSuccess = fileUploadHelper.uploadFile(file);

            if (isSuccess){
                //return ResponseEntity.ok("File is successfully uploaded");
                return ResponseEntity.ok(ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/images/")
                        .path(file.getOriginalFilename()).toUriString());
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong try again!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        return ResponseEntity.ok("working");
    }

}
