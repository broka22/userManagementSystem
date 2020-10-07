package com.vastika.uise.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vastika.uise.service.UserService;
import com.vastika.uise.util.ImageUtil;

@Controller
public class ImageDisplayController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/display_image")
	public void displayImage(@RequestParam int id, HttpServletResponse response) {
		String imageUrl = userService.getUserById(id).getImageUrl();
		ImageUtil.displayImage(imageUrl, response);
	}

}
