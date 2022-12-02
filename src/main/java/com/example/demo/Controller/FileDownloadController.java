package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FileService;

@RestController
public class FileDownloadController {
     

	@Autowired
	FileService service;
	
    @GetMapping("/downloadFile/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename) {   
        Resource resource = null;
        try {
            resource = service.getFileAsResource(filename);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }        
        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }      
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
         
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);       
    }
    
    @GetMapping("/file/{filename}")
    public ResponseEntity<?> getfile(@PathVariable("filename") String filename) throws IOException	{
    	byte [] imageData = service.downloadImageFromSystem(filename);
    	return ResponseEntity.status(HttpStatus.OK)
    			.contentType(MediaType.valueOf("image/png"))
    			.body(imageData);
    }
    
}
