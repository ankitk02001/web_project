package com.webapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	private JavaMailSender javaMailSender;

	@Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
	
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(to);// entering in gmail UI
		sm.setSubject(subject);
		sm.setText(message);
		javaMailSender.send(sm);
	}
}
