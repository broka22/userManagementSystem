package com.vastika.uise.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageUtil {

	private static final Logger log = Logger.getLogger(ImageUtil.class);

	public static final String IMAGE_UPLOAD_PATH = "/Users/bimalroka/Desktop/Mac/Java Training Videos/";
	private static final int BUFFER_SIZE = 1024;

	public static String writeImageToDisk(CommonsMultipartFile file) {
		
		String imageUrl = IMAGE_UPLOAD_PATH + file.getOriginalFilename();
		
		log.info("Image path is: " + imageUrl);
		
		try (FileOutputStream out = new FileOutputStream(imageUrl);) {

			out.write(file.getBytes());
			out.flush();

		} catch (IOException e) {
			log.error("Exception is: " + e);
			e.printStackTrace();
		}
		return imageUrl;
	}

	public static void displayImage(String imageUrl, HttpServletResponse response) {
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(imageUrl), BUFFER_SIZE);
				BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream(), BUFFER_SIZE);) {

			int len;
			byte[] bytes = new byte[BUFFER_SIZE];
			while ((len = in.read(bytes)) != -1) {
				out.write(bytes, 0, len);
				out.flush();
			}

		} catch (IOException e) {
			log.fatal("Error is: " + e);
			e.printStackTrace();
		}
	}

}
