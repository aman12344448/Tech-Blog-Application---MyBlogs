package com.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//File Name
		String name=file.getOriginalFilename();
		//random name generator
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		//Full Path 
		String filePath = path+File.separator+fileName1;
		
		//Create Folder if not create 
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		//File 
		
		Files.copy(file.getInputStream(),Paths.get(filePath));	
		return fileName1;
	}

	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream is = new FileInputStream(fullPath);
		//db logic to return inputstream 
		return is;
	}

	

}
