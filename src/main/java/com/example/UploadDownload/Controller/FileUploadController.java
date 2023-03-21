package com.example.UploadDownload.Controller;

import com.example.UploadDownload.Service.UploadDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
     private UploadDownloadService uploadDownloadService;


    @PostMapping("/file")
    public List<String> uploadMultiple(@RequestParam MultipartFile[] files) throws IOException, IOException {
        List<String> filesNames = new ArrayList<>();
        for (MultipartFile file: files) {
            String fileName = uploadDownloadService.upload(file);
            filesNames.add(fileName);
        }
        return filesNames;
    }

}
