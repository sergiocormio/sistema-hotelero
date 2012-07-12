package com.cdz.sh.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import com.cdz.sh.mail.exception.EMailException;

public class TestSendMail {

	@Test
	public void testFromGmail() throws EMailException {
		
		String from = "federico.deseta@gmail.com";
		String password = "";	// PONER CONTRASEÑA DEL FROM!
		String to = "federico.deseta@gmail.com";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from GMail";
		String body = "Body chabon!";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(from, password, toList, subject, body);
		
	}

	@Test
	public void testFromYahoo() throws AddressException, MessagingException, EMailException{
	
		String from = "federicodeseta@yahoo.com.ar";
		String password = "";	// PONER CONTRASEÑA DEL FROM!
		String to = "federico.deseta@gmail.com";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from Yahoo";
		String body = "Body chabon!";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(from, password, toList, subject, body);
	}
	
}
