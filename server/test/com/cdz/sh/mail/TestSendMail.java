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
		
		String from = "test.cds.001@gmail.com";
		String password = "001cdstest";	// es como el mail, pero sin puntos y al reves
		
		String to = "test.cds.001@gmail.com";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from GMail";
		String body = "Body chabon! Cree cuenta de GMail de prueba :)";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(from, password, toList, subject, body);
		
	}

	@Test
	public void testFromYahoo() throws AddressException, MessagingException, EMailException{
	
		String from = "test.cds001@yahoo.com.ar";	//yahoo permite un solo punto
		String password = "001cdstest";	// es como el mail, pero sin puntos y al reves
		
		String to = "test.cds001@yahoo.com.ar";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from Yahoo";
		String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(from, password, toList, subject, body);
	}
	
}
