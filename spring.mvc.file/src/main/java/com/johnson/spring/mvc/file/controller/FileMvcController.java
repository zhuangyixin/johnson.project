package com.johnson.spring.mvc.file.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.johnson.common.util.FileUtil;

@Controller
@RequestMapping("/file")
public class FileMvcController {
	
	private static final String DOWNLOAD_FILE_NAME = "download/Download.txt";
	private static final String UPLOAD_DIR = "upload";

	private final String PATH = getFilePath();

	@RequestMapping()
	public String index() {
		return "file";
	}

	@RequestMapping(value = "/request-entity", method = RequestMethod.POST)
	public ResponseEntity<byte[]> downloadByte() throws UnsupportedEncodingException, FileNotFoundException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "download.txt");
		String downloadFile = FileUtil.read(PATH, DOWNLOAD_FILE_NAME);
		return new ResponseEntity<>(downloadFile.getBytes(), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/servlet-download", method = RequestMethod.POST)
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");// 设置强制下载不打开
		response.addHeader("Content-Disposition", "attachment;fileName=" + "download.txt");
		String downloadFile = FileUtil.read(PATH, DOWNLOAD_FILE_NAME);
		OutputStream os = response.getOutputStream();
		os.write(downloadFile.getBytes());
		os.flush();
		os.close();
	}

	@RequestMapping(value = "/servlet-upload", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		// 在MvcConfigure中实例化了bean
		CommonsMultipartResolver resolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(100000L);
		resolver.setMaxInMemorySize(40960);
		
		// 判断是否包含文件
		if (resolver.isMultipart(request)) {
			// 将request转换为MultipartHttpServletRequest
			// 在MvcConfigure实例化CommonsMultipartResolver之后，直接强制转换就可以了
			// MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 得到文件名列表的迭代器
			Iterator<String> fileNameIterator = multiRequest.getFileNames();
			while (fileNameIterator.hasNext()) {
				// 根据表单内的name获取MultipartFile
				MultipartFile file = multiRequest.getFile(fileNameIterator.next());
				if (file != null) {
					// 获取文件名
					String fileName = file.getOriginalFilename();
					if (fileName.trim() != "") {
						// 获取存储路径
						String path = PATH + File.separator + UPLOAD_DIR + File.separator + fileName;
						File localFile = new File(path);
						if (!localFile.exists() || localFile.isDirectory()) {
							localFile.createNewFile();
						}
						// 写入文件
						file.transferTo(localFile);
					}
				}
			}
		}
	}

	@RequestMapping("/multipart-file")
	public void uploadFile(MultipartFile[] multipartfile) {

		for (MultipartFile file : multipartfile) {
			if (!file.isEmpty()) {
				try {
					File dir = new File(PATH + File.separator + UPLOAD_DIR);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					// 拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream(
							PATH + File.separator + UPLOAD_DIR + File.separator + file.getOriginalFilename());
					// 拿到上传文件的输入流
					InputStream in = file.getInputStream();
					// 以写字节的方式写文件
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String getFilePath() {
		String classpath = this.getClass().getResource("/").getPath();
		File file = new File(classpath);
		String webInfoPath = file.getParent();
		String filePath = webInfoPath + File.separator + "file";
		return filePath;
	}
}
