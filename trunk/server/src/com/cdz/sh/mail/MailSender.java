package com.cdz.sh.mail;

import java.net.ConnectException;
import java.util.List;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.mail.exception.EMailException;
import com.sun.mail.smtp.SMTPAddressFailedException;
import com.sun.mail.smtp.SMTPSendFailedException;

public class MailSender {

	private static final String HOST_GMAIL = "smtp.gmail.com";
	private static final String HOST_YAHOO = "smtp.mail.yahoo.co.in";
	private static final String HOST_PORT = "587";
	private static final String SMTP = "smtp";
	
	public void sendMail(String from, String password, List<String> toList, String subject, String body, boolean isHtml) throws EMailException {
		try {
			
			validateParameters(from, toList, body);
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
			
			if(isHtml){
				message.setText(body, "ISO-8859-1",	"html");
			}
			else{
				message.setText(body);
			}
			
			Transport transport = session.getTransport(SMTP);
			transport.connect(from, password);
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
			
		} catch (MessagingException e) {
			if(e.getNextException() instanceof ConnectException){
				throw new EMailException(ExceptionErrorCodes.INVALID_FROM_EMAIL, e.getMessage());
			}
			if(e instanceof SMTPSendFailedException || e instanceof AuthenticationFailedException){
				throw new EMailException(ExceptionErrorCodes.INVALID_SENDER, e.getMessage());
			}
			if(e.getNextException() instanceof SMTPAddressFailedException){
				throw new EMailException(ExceptionErrorCodes.INVALID_TO_EMAIL, e.getMessage());
			}
			
			throw new EMailException(ExceptionErrorCodes.EMAIL_NOT_SENT, e.getMessage());
		}
		
		
		
	}

	private void validateParameters(String from, List<String> toList, String body) throws EMailException {
		
		if(from == null){
			throw new EMailException(ExceptionErrorCodes.INVALID_FROM_EMAIL, "'from' email is empty");
		}
						
		if(toList == null || toList.isEmpty()){
			throw new EMailException(ExceptionErrorCodes.TO_LIST_EMPTY, "The 'To' list is empty.");
		}
		
		if(body == null){
			throw new EMailException(ExceptionErrorCodes.INVALID_BODY, "email body is empty");
		}
	}
}
