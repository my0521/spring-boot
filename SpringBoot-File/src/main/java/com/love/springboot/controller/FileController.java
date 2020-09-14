package com.love.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author my
 * @description
 * @date 2020/5/18
 */
@RestController
public class FileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

	/**
	 * 如果单个文件大小超出1MB，抛出异常 FileSizeLimitExceededException: The field file exceeds its
	 * maximum permitted size of 1048576 bytes.
	 */
	@RequestMapping("/upload1")
	public String uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		Map<String, String[]> paramMap = request.getParameterMap();
		if (!paramMap.isEmpty()) {
			LOGGER.info("paramMap:" + paramMap);
		}
		try {
			if (!file.isEmpty()) {
				// 打印文件基本信息
				LOGGER.info("Name：" + file.getName());
				LOGGER.info("OriginalFilename == >>{}", file.getOriginalFilename());
				LOGGER.info("ContentType == >>{}", file.getContentType());
				LOGGER.info("Size == >>{}", file.getSize());
				// 文件输出地址
				String filePath = "D:/logs/file/";
				new File(filePath).mkdirs();
				File writeFile = new File(filePath, file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("\\") + 1));
				file.transferTo(writeFile);
			}
			return "success";
		} catch (IOException e) {
			e.printStackTrace();
			return "系统异常！";
		}
	}

	/**
	 * 如果上传文件总大小超过6MB，抛出异常 SizeLimitExceededException: the request was rejected
	 * because its size (9052616) exceeds the configured maximum (6291456)
	 */
	@RequestMapping("/upload2")
	public String upload2(HttpServletRequest request, @RequestParam("file") MultipartFile[] fileList) {
		Map<String, String[]> paramMap = request.getParameterMap();
		if (!paramMap.isEmpty()) {
			LOGGER.info("paramMap == >>{}", paramMap);
		}
		try {
			if (fileList.length > 0) {
				for (MultipartFile file : fileList) {
					// 打印文件基础信息
					LOGGER.info("Name == >>{}", file.getName());
					LOGGER.info("OriginalFilename == >>{}", file.getOriginalFilename());
					LOGGER.info("ContentType == >>{}", file.getContentType());
					LOGGER.info("Size == >>{}", file.getSize());
					// 文件输出地址
					String filePath = "D:/logs/file/";
					new File(filePath).mkdirs();
					File writeFile = new File(filePath,
							file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("\\") + 1));
					file.transferTo(writeFile);
				}
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

}
