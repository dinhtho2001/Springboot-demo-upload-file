package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	private Path foundFile;
	
	@Value("${file.upload-dir}")
	private Resource resource;
	
	public String saveFile(String fileName, MultipartFile multipartFile) throws IOException, URISyntaxException {
		try {
			
			Path path = Paths.get(resource.getURI().toString() + "assets");
			System.out.println(path);
			if (!Files.exists(path)) {
				init(path);
			}
			try (InputStream inputStream = multipartFile.getInputStream()) {
				
				Path filePath = path.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				return filePath.toString();
			} catch (IOException e) {
				throw new IOException("Could not save file: " + fileName, e);
			}
		} catch (Exception e) {
			throw new IOException("Could not save file: " + fileName, e);
		}
		
	}
	
	  public void init(Path path) throws IOException {
		    try {
		      Files.createDirectories(path);
		    } catch (IOException e) {
		      throw new RuntimeException("Could not initialize folder for upload!");
		    }
		  }

	public Resource getFileAsResource(String filename) throws IOException {
		URI uri = resource.getURI();
		Path path = Paths.get(uri);
		Files.list(path).forEach(file -> {
			if (file.getFileName().toString().startsWith(filename)) {
				foundFile = file;
				return;
			}
		});
		if (foundFile != null) {
			return new UrlResource(foundFile.toUri());
		}
		return null;
	}
	
    public byte[] downloadImageFromSystem(String fileName) throws IOException{  
    	URI uri = resource.getURI();
		Path path = Paths.get(uri);
		String filePath = (path + "\\" + fileName);
    	byte[] image = Files.readAllBytes(new File(filePath).toPath());    	
    	return image;
    }
}
