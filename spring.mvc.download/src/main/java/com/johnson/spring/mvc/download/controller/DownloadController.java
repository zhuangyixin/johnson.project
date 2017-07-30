package com.johnson.spring.mvc.download.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/download")
public class DownloadController {
	@RequestMapping()
	public String index() {
		return "download";
	}

	@RequestMapping(value = "/springDownload", method = RequestMethod.POST)
	public ResponseEntity<byte[]> downloadByte() throws UnsupportedEncodingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "test.txt");
		return new ResponseEntity<>("我是测试文件".getBytes("UTF-8"), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/httpDownload", method = RequestMethod.POST)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");// 设置强制下载不打开
		response.addHeader("Content-Disposition", "attachment;fileName=" + "test.txt");

		OutputStream os = response.getOutputStream();
		os.write("text".getBytes());
		os.flush();
	}

}
