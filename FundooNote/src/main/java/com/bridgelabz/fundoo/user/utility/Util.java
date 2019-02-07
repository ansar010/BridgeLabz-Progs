package com.bridgelabz.fundoo.user.utility;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Util 
{
//	@Autowired
//	JavaMailSender javaMailSender;

	
	/**
	 * 
	 * @param to user mail id
	 * @param subject email message
	 * @param body consist the body of the message
	 * @return true 
	 * @throws MessagingException
	 */
//	public void sendMail(String to,String subject,String body) throws MessagingException
//	{
//		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//		//class for create mime-message and provide in-line support
//		MimeMessageHelper helper;
//		// true indicates multipart message
//		helper = new MimeMessageHelper(mimeMessage, true);
//
//		helper.setTo(to);
//		helper.setSubject(subject);
//		helper.setText(body);
//
//		//return true;
		
		@Autowired
		private JavaMailSender javaMailSender;	
		
		public void send(String to, String subject, String body) throws MessagingException {
			
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;
			
			helper = new MimeMessageHelper(message, true); // true indicates
														   // multipart message
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body, true); // true indicates html
			// continue using helper object for more functionalities like adding attachments, etc.  
			
			javaMailSender.send(message);
	}
}
