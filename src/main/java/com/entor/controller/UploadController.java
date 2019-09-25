package com.entor.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@RequestMapping("/uploadFile")
	public String uploadFile(String name,MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("姓名："+name);
		String path = request.getServletContext().getRealPath("/upload/");
		if(file!=null) {
			//文件类型
			String contentType = file.getContentType();
			//文件名称
			String fileName = file.getOriginalFilename();
			//文件大小
			long size = file.getSize();
			System.out.println("文件类型："+contentType);
			System.out.println("文件名称："+fileName);
			System.out.println("文件大小："+size);
			//上传文件
			file.transferTo(new File(path,fileName));
		}
		return "index";
	}
	@RequestMapping("/uploadMoreFiles")
	public String uploadMoreFiles(String name,MultipartFile[] file,HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("姓名："+name);
		String path = request.getServletContext().getRealPath("/upload/");
		if(file.length>0) {
			for(MultipartFile f:file) {
				if(f!=null) {
					//文件类型
					String contentType = f.getContentType();
					//文件名称
					String fileName = f.getOriginalFilename();
					//文件大小
					long size = f.getSize();
					System.out.println("文件类型："+contentType);
					System.out.println("文件名称："+fileName);
					System.out.println("文件大小："+size);
					if(!"".equals(fileName)) {
						//上传文件
						f.transferTo(new File(path,fileName));
					}
				}
			}
		}
		return "index";
	}
}
