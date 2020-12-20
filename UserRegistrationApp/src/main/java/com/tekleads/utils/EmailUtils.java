package com.tekleads.utils;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.tekleads.entity.UserDetailsEntity;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmail01(UserDetailsEntity entity) {
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(entity.getEmail());
			msg.setSubject("User Registration");
			msg.setText(formatEmail(entity));
			javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(UserDetailsEntity entity) {
		try {
			MimeMessage msg =javaMailSender.createMimeMessage();
			MimeMessageHelper header=new MimeMessageHelper(msg);
			header.setTo(entity.getEmail());
			header.setSubject("User Registration");
			header.setText(formatEmail(entity),true);
			javaMailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		public void sendEmail2(String email,String pazzword) {
			try {
				MimeMessage msg =javaMailSender.createMimeMessage();
				MimeMessageHelper header=new MimeMessageHelper(msg);
				header.setTo(email);
				header.setSubject("User Password");
				header.setText("Your Password is: "+pazzword,true);
				javaMailSender.send(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public String formatEmail(UserDetailsEntity entity) throws Exception {
		StringBuffer sb = new StringBuffer();

		FileReader fr = new FileReader("EMAIL_TEMPLATE.txt");
		BufferedReader br = new BufferedReader(fr);
		String line="";
		while ((line=br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		String mail = sb.toString();
		mail=mail.replace("{FNAME}",entity.getFname());
		mail=mail.replace("{LNAME}", entity.getLname());
		mail=mail.replace("{Pwd}", entity.getPazzword());
		mail=mail.replace("{EMAIL}", entity.getEmail());
		return mail;
	}

}
