package com.entor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadController {

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(String fileName,HttpServletRequest request) throws IOException{
		String path = request.getServletContext().getRealPath("/upload/");
		FileInputStream fis = new FileInputStream(new File(path,fileName));
		//把文件读入到字节数组中
		byte[] b = new byte[fis.available()];
		fis.read(b);
		fis.close();
		//设置响应头
		HttpHeaders headers=new HttpHeaders();
		//下载文件名是中文的时候避免出现乱码
		headers.add("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
		return new ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
	}
	@RequestMapping("/download2")
	public void download2(String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path,fileName);
		
		//设置响应头文件内容,文件类型、弹出下载对话框、文件大小
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-disposition", "attachment; filename=" +URLEncoder.encode(fileName, "utf-8") );
        response.setHeader("Content-Length", String.valueOf(file.length()));
        //打开文件输入流读取文件
        FileInputStream fis = new FileInputStream(file);
        //打开文件输出流下载文件
		OutputStream os = response.getOutputStream();
		byte[] b = new byte[1024];
		int len;
		while((len=fis.read(b))!=-1) {
			os.write(b, 0, len);
		}
		fis.close();
		os.close();
	}
	@RequestMapping("/download3")
	@ResponseBody
	public void download3(String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path,fileName);
		
		//设置响应头文件内容,文件类型、弹出下载对话框、文件大小
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-disposition", "attachment; filename=" +URLEncoder.encode(fileName, "utf-8") );
        response.setHeader("Content-Length", String.valueOf(file.length()));
        //文件下载
        Files.copy(file.toPath(), response.getOutputStream());
	}
}
