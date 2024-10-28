package com.example.exe201.config;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class UploadImage {
    public File handerUpLoadFile(MultipartFile uploadFile) {
        String folderPath = "D:\\SOF3021_SD17307_SU23\\ASS_SOF3021_PH19850\\src\\main\\resources\\static\\images";
        File myUpLoadFolder = new File(folderPath);
        // thiếu thì tạo
        if (!myUpLoadFolder.exists()) {
            myUpLoadFolder.mkdirs();
        }
        File saveFile = null;
        try {
            saveFile = new File(myUpLoadFolder, uploadFile.getOriginalFilename());
            uploadFile.transferTo(saveFile);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return saveFile;
    }
}
