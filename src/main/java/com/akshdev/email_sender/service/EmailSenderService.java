package com.akshdev.email_sender.service;

import com.akshdev.emain_sender.dto.MailObject;

public interface EmailSenderService {

	public void sendSimpleMessage(String to, String subject,  String text);
	public void sendMailWithTemplate(String to, String subject,  String text);
	public boolean isValid(MailObject mailDetails);
}
