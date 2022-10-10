package com.restapibookdb.helper;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public final String UPLOAD_DIR="P:\\SpringBoot\\RestAPIBookDB\\RestAPIBookDB\\src\\main\\resources\\static\\images";


    public boolean uploadFile(MultipartFile f){
        boolean isUploaded =false;

        try {
//            //using old api
//            InputStream inputStream = f.getInputStream();
//            byte data[] = new byte[inputStream.available()];
//            inputStream.read(data);
//
//            //write
//            FileOutputStream fileOutputStream = new FileOutputStream(UPLOAD_DIR+"\\"+f.getOriginalFilename());
//            fileOutputStream.write(data);
//
//            fileOutputStream.close();


            Files.copy(f.getInputStream(), Paths.get(UPLOAD_DIR+ File.separator+f.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);



            isUploaded = true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return isUploaded;
    }


}
