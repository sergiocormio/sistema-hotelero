package com.cdz.sh.mail;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.mail.exception.EMailException;

public class TestSendMailFromGMail {

	private String fromGMail = "test.cds.001@gmail.com";
	private String passwordGMail = "001cdstest";	// es como el mail, pero sin puntos y al reves
	
		
	/*
	 * GMAIL 
	 */
	
	@Test
	public void testFromGmailSuccessfully() throws EMailException {
		
		String to = "test.cds.001@gmail.com";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from GMail";
		String body = "Body chabon! Cree cuenta de GMail de prueba :)";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(fromGMail, passwordGMail, toList, subject, body, false);
		
	}
	
	@Test
	public void testFromGmail_BodyHTML_Successfully() throws EMailException {
		
		String to = "test.cds.001@gmail.com";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from GMail";
		String body = "<!DOCTYPE html>" +
						"<html>" +
							"<body style='background-color:PowderBlue;'>" +
								"<h1>Look! Styles and colors</h1>" +
								"<p style='font-family:verdana;color:red;''>" +
									"This text is in Verdana and red</p>" +
								"<p style='font-family:times;color:green;'>" +
									"This text is in Times and green</p>" +
								"<p style='font-size:30px;'>This text is 30 pixels high</p>" +
							"</body>" +
						"</html>";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(fromGMail, passwordGMail, toList, subject, body, true);
		
	}

	
	@Test(expected=EMailException.class)
	public void testFromGmailFromNull() throws EMailException {
		try{
			
			String to = "test.cds.001@gmail.com";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from GMail";
			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(null, passwordGMail, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.INVALID_FROM_EMAIL));
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromGmailInvalidFrom() throws EMailException {
		try{
			
			String to = "test.cds.001@gmail.com";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from GMail";
			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail("fruta", passwordGMail, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.INVALID_FROM_EMAIL));
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromGmailPasswordNull() throws EMailException {
		try{
			
			String to = "test.cds.001@gmail.com";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from GMail";
			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromGMail, null, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.INVALID_FROM_PASSWORD));
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromGmailInvalidPassword() throws EMailException {
		
		try{
			
			String to = "test.cds.001@gmail.com";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from GMail";
			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromGMail, "fruta", toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.INVALID_FROM_PASSWORD));
			throw e;
		}
		
	}
	
	
	@Test(expected= EMailException.class)
	public void testFromGmailToNull() throws EMailException {
		
		try{
			String to = "test.cds.001@gmail.com";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from GMail";
			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromGMail, passwordGMail, null, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.TO_LIST_EMPTY));
			throw e;
		}
		
	}
	
	
	@Test(expected=EMailException.class)
	public void testFromGmailToNoOne() throws EMailException {
		
		try{
			List<String> toList = new ArrayList<String>();
			
			String subject = "Mail from GMail";
			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromGMail, passwordGMail, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.TO_LIST_EMPTY));
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromGmailInvalidTo() throws EMailException {
		
		try{
			String to = "fruta";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from GMail";
			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromGMail, passwordGMail, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.INVALID_TO_EMAIL));
			throw e;
		}
	}
	
	@Test
	public void testFromGmailNoSubject() throws EMailException {
		
		String to = "test.cds.001@gmail.com";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
//		String subject = "Mail from GMail";
		String body = "Body chabon! Cree cuenta de GMail de prueba :)";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(fromGMail, passwordGMail, toList, null, body, false);
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromGmailNoBody() throws EMailException {
		
		try{
			String to = "test.cds.001@gmail.com";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from GMail";
//			String body = "Body chabon! Cree cuenta de GMail de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromGMail, passwordGMail, toList, subject, null, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode().equals(ExceptionErrorCodes.INVALID_BODY));
			throw e;
		}
	}
	
		
	
}
