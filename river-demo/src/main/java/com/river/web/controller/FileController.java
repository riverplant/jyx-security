package com.river.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.river.dto.FileInfo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author riverplant
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	String folder = "J:\\repository\\river-demo\\src\\test\\java\\com\\river\\web\\controller";

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@PostMapping
	@ApiOperation(value = "upload MultipartFile")
	public FileInfo upload(MultipartFile file) throws Exception {

//		System.out.println(file.getName());
//		System.out.println(file.getOriginalFilename());
//		System.out.println(file.getSize());
//		InputStream is = null;
//
//		is = file.getInputStream();

		File localFile = new File(folder, new Date().getTime() + ".txt");
		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());

	}

	@GetMapping("/{id}")
	public void download(@ApiParam(value=" file's name ")@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {

		try (InputStream is = new FileInputStream(new File(folder, id + ".txt"));
				OutputStream os = response.getOutputStream();) {
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment;filename=test.txt");

			IOUtils.copy(is, os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
