package com.yndg.blog.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {
	
	@Value("${file.path}")
	private String fileRealPath;
	
	@PutMapping("/image/upload")	
	public @ResponseBody String imageUpload(@RequestParam("imgFile") MultipartFile imgFile) {
		// 1번 imgFile 출력

		System.out.println(imgFile.getOriginalFilename());
		System.out.println(imgFile.getName());
		System.out.println(imgFile.getSize());
		System.out.println(imgFile.getContentType());
		System.out.println(imgFile.getResource());
		System.out.println();

		UUID uuid = UUID.randomUUID();
		String uuidFilename = uuid + "_" + imgFile.getOriginalFilename();
			
		Path filePath = Paths.get(fileRealPath+uuidFilename);
		
		try {
			Files.write(filePath, imgFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "ok";
	}
	
}

