package com.vastika.uise.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailUtil {

	private static final String FROM_EMAIL = "rokabimal2@gmail.com";

	public static void sendEmail(String toEmail, String msgBody, String subject, MailSender mailSender) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(FROM_EMAIL);
		mailMessage.setSubject(subject);
		mailMessage.setTo(toEmail);
		mailMessage.setText(msgBody);
		mailSender.send(mailMessage);
	}
}
