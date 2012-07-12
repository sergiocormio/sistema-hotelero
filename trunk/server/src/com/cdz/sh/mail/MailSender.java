package com.cdz.sh.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cdz.sh.mail.exception.EMailException;

public class MailSender {

	private static final String HOST_GMAIL = "smtp.gmail.com";
	private static final String HOST_YAHOO = "smtp.mail.yahoo.co.in";
	private static final String HOST_PORT = "587";
	private static final String SMTP = "smtp";
	
	public void sendMail(String from, String password, List<String> toList, String subject, String body) throws EMailException {
		try {
			String host = "";
			// Get system properties
			Properties props = System.getProperties();
			
			if(from.endsWith("@gmail.com")){
				host = HOST_GMAIL;
			}
			else if(from.contains("@yahoo.com")){
				host = HOST_YAHOO;
			}
			props.setProperty("mail.smtp.host", host);
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", HOST_PORT);
			props.setProperty("mail.smtp.user", from);
			
			// Get session
			Session session = Session.getDefaultInstance(props);
			
			// Define message
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			for (String to : toList) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
						
			message.setSubject(subject);
			message.setText(body);
			
			/*
			 * message.setText("Mensajito con Java Mail<br>" + "<b>de</b> los <i>buenos</i>." + "poque si",
								"ISO-8859-1",
								"html");
			 */

			Transport transport = session.getTransport(SMTP);
			transport.connect(from, password);
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
			
		} catch (MessagingException e) {
			throw new EMailException(e.getMessage());
		}
		
		
		
	}
}
