package com.cdz.sh.service.impl;

import com.cdz.sh.mail.MailSender;
import com.cdz.sh.mail.exception.EMailException;
import com.cdz.sh.model.request.EmailRequest;
import com.cdz.sh.service.EMailService;

public class EMailServiceImpl implements EMailService {

	@Override
	public void sendEmail(EmailRequest request) throws EMailException {
		
		MailSender sender = new MailSender();
		
		sender.sendMail(request);

	}

}
