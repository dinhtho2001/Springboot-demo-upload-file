package com.example.demo.Controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.payload.Response.FileUploadResponse;
import com.example.demo.service.FileService;

@RestController
public class FileUploadController {

	@Autowired
	FileService service;
	
	@PostMapping("/uploadFile")
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile)
			throws IOException, URISyntaxException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		long size = multipartFile.getSize();		
		String filePath = service.saveFile(fileName, multipartFile);
		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(fileName);
		response.setSize(size);
		response.setDownloadUri("/downloadFile/" + fileName);
		response.setFilePath(filePath);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
