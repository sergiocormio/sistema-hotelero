package com.cdz.sh.service;

import com.cdz.sh.mail.exception.EMailException;
import com.cdz.sh.model.request.EmailRequest;

public interface EMailService {

	/**
	 * Sends one email from one source account to one or more target accounts
	 * The body could be either a plain-text or html format.
	 * 
	 * @param request 
	 * @throws EMailException  
	 */
	public void sendEmail(EmailRequest request) throws EMailException;

}
