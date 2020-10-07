package com.vastika.uise.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.vastika.uise.model.User;
import com.vastika.uise.service.UserService;
import com.vastika.uise.util.ImageUtil;

@Controller
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user_form")
	public String getUserForm() {
		log.info("create user page is clicked."); 
		return "createUser";
	}

	@RequestMapping(value = "/user_edit")
	public String getUserEditForm(@RequestParam int id, Model model) {
		log.info("User edit page is clicked.");
		model.addAttribute("user", userService.getUserById(id));
		return "editUser";
	}

	@RequestMapping(value = "/user_save", method = RequestMethod.POST)
	public String saveUser(@Valid @ModelAttribute User user, BindingResult result,
			@RequestParam CommonsMultipartFile file) {
		log.debug("Error may have occured.");
		if (result.hasErrors()) {
			log.error("Error had occured: "+ result.getFieldError().getDefaultMessage());
			return "createUser";
		}

		if (file != null) {
			String imageUrl = ImageUtil.writeImageToDisk(file);
			user.setImageUrl(imageUrl);
		}

		userService.saveUser(user);
		return "redirect:/user_list";
	}

	@RequestMapping(value = "/user_update", method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute User user, BindingResult result, Model model,
			@RequestParam CommonsMultipartFile file) {
		if (result.hasErrors()) {
			return "editUser";
		}

		log.warn("Here, file cannot be null.");
		if (file != null) {
			String imageUrl = ImageUtil.writeImageToDisk(file);
			user.setImageUrl(imageUrl);
		}

		userService.updateUser(user);
		return "redirect:/user_list";
	}

	@RequestMapping(value = "/user_delete", method = RequestMethod.GET)
	public String deleteUser(@RequestParam int id) {
		userService.deleteUser(id);
		return "redirect:/user_list";
	}

	@RequestMapping(value = "/user_list", method = RequestMethod.GET)
	public String getAllUser(Model model) {
		log.info("list page clicked.");
		model.addAttribute("users", userService.getAllUser());
		return "listUser";
	}

}
