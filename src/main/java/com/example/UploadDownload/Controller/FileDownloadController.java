package com.example.UploadDownload.Controller;

import com.example.UploadDownload.Service.UploadDownloadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/download")
public class FileDownloadController {

    @Autowired
     private UploadDownloadService uploadDownloadService;

    @GetMapping("/file")
    public @ResponseBody byte[] download(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        System.out.println("Downloading " + fileName);
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension) {
            case "jpg", "jpeg" -> response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            case "png" -> response.setContentType(MediaType.IMAGE_PNG_VALUE);
            case "gif" -> response.setContentType(MediaType.IMAGE_GIF_VALUE);
            default -> throw new IOException("Unknown file extension.");
        }
        response.setHeader("Content-Disposition", "attachment; filename = \""+fileName+"\"");
        return uploadDownloadService.download(fileName);
    }


}
