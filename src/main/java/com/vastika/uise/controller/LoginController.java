package com.vastika.uise.controller;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vastika.uise.model.User;
import com.vastika.uise.service.UserService;
import com.vastika.uise.util.EmailUtil;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailSender mailSender;

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/accessdenied")
	public String accessDeined(ModelMap model) {
		model.addAttribute("error", "true");
		return "accessDenied";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "login";
	}

	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
	public String forgetPassword(@RequestParam String email) {
		User user = userService.getUserByEmail(email);
		if (user != null) {
			String newPassword = getNewPassword();
			user.setPassword(newPassword);
			String subject = "forgot-password";
			String msgBody = "Your new password is :" + newPassword;
			EmailUtil.sendEmail(email, msgBody, subject, mailSender);
			userService.updateUser(user);
		}
		return "login";
	}

	private String getNewPassword() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(50, random).toString(32);
	}

}
