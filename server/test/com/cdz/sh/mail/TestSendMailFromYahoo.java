package com.cdz.sh.mail;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import com.cdz.sh.constants.ExceptionErrorCodes;
import com.cdz.sh.mail.exception.EMailException;

public class TestSendMailFromYahoo {

	String fromYahoo = "test.cds001@yahoo.com.ar";	//yahoo permite un solo punto
	String passwordYahoo = "001cdstest";	// es como el mail, pero sin puntos y al reves

	
	/*
	 * YAHOO
	 */
	
	@Test
	public void testFromYahooSuccessfully() throws AddressException, MessagingException, EMailException{
	
		String to = "test.cds001@yahoo.com.ar";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from Yahoo";
		String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(fromYahoo, passwordYahoo, toList, subject, body, false);
	}
	
	@Test
	public void testFromYahoo_BodyHTML_Successfully() throws AddressException, MessagingException, EMailException{
	
		String to = "test.cds001@yahoo.com.ar";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
		String subject = "Mail from Yahoo";
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
		mailSender.sendMail(fromYahoo, passwordYahoo, toList, subject, body, true);
	}
	
	@Test(expected=EMailException.class)
	public void testFromYahooFromNull() throws EMailException {
		try{
			
			String to = "test.cds001@yahoo.com.ar";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from Yahoo";
			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(null, passwordYahoo, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.INVALID_FROM_EMAIL);
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromYahooInvalidFrom() throws EMailException {
		try{
			
			String to = "test.cds001@yahoo.com.ar";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from Yahoo";
			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail("fruta", passwordYahoo, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.INVALID_FROM_EMAIL);
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromYahooPasswordNull() throws EMailException {
		try{
			
			String to = "test.cds001@yahoo.com.ar";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from Yahoo";
			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromYahoo, null, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.INVALID_FROM_PASSWORD);
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromYahooInvalidPassword() throws EMailException {
		
		try{
			
			String to = "test.cds001@yahoo.com.ar";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from Yahoo";
			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromYahoo, "fruta", toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.INVALID_FROM_PASSWORD);
			throw e;
		}
		
	}
	
	
	@Test(expected= EMailException.class)
	public void testFromYahooToNull() throws EMailException {
		
		try{
			String to = "test.cds001@yahoo.com.ar";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from Yahoo";
			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromYahoo, passwordYahoo, null, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.TO_LIST_EMPTY);
			throw e;
		}
		
	}
	
	
	@Test(expected=EMailException.class)
	public void testFromYahooToNoOne() throws EMailException {
		
		try{
			List<String> toList = new ArrayList<String>();
			
			String subject = "Mail from Yahoo";
			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromYahoo, passwordYahoo, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.TO_LIST_EMPTY);
			throw e;
		}
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromYahooInvalidTo() throws EMailException {
		
		try{
			String to = "fruta";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from Yahoo";
			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromYahoo, passwordYahoo, toList, subject, body, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.INVALID_TO_EMAIL);
			throw e;
		}
	}
	
	@Test
	public void testFromYahooNoSubject() throws EMailException {
		
		String to = "test.cds001@yahoo.com.ar";
		List<String> toList = new ArrayList<String>();
		toList.add(to);
		
//		String subject = "Mail from Yahoo";
		String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
		
		MailSender mailSender = new MailSender();
		mailSender.sendMail(fromYahoo, passwordYahoo, toList, null, body, false);
		
	}
	
	@Test(expected=EMailException.class)
	public void testFromYahooNoBody() throws EMailException {
		
		try{
			String to = "test.cds001@yahoo.com.ar";
			List<String> toList = new ArrayList<String>();
			toList.add(to);
			
			String subject = "Mail from Yahoo";
//			String body = "Body chabon! Cree cuenta de Yahoo de prueba :)";
			
			MailSender mailSender = new MailSender();
			mailSender.sendMail(fromYahoo, passwordYahoo, toList, subject, null, false);
		}
		catch (EMailException e) {
			assertTrue(e.getErrorCode() == ExceptionErrorCodes.INVALID_BODY);
			throw e;
		}
	}
	
}
