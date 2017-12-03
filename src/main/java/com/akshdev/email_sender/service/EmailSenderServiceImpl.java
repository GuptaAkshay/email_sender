package com.akshdev.email_sender.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.akshdev.emain_sender.dto.MailObject;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	
	private JavaMailSender mail;
	
	@Autowired
	private Configuration config;
	
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		
		try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mail.send(message);
            log.info("Prepared Mail "+ mail.toString());
        } catch (MailException exception) {
            //exception.printStackTrace();
        	log.error("Error occured "+exception);
            
        }
		
	}

	@Override
	public void sendMailWithTemplate(String to, String subject, String text) {
		// TODO Auto-generated method stub
		try {
			MimeMessage msg = mail.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(msg);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", "Anup");
			
			config.setClassForTemplateLoading(this.getClass(), "/");
			
			Template temp = config.getTemplate("hello-world.ftl");
			String text1 = FreeMarkerTemplateUtils.processTemplateIntoString(temp, model);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text1, true);
			
			mail.send(msg);
			
		} catch(Exception exception) {
			log.error("Error occured "+exception);
		}
		
	}
	
	@Override
	public boolean isValid(MailObject mailDetails) {
		boolean result = true;
		String mail_regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
				+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pat = Pattern.compile(mail_regex);
		Matcher match = pat.matcher(mailDetails.getTo());
		log.info("To=>"+mailDetails.getTo()+
				"\n Subject=>"+mailDetails.getSubject()+
				"\n Message=>"+mailDetails.getMessage());
		if(!(mailDetails.getTo()!=null) || !(mailDetails.getSubject()!=null) || !(mailDetails.getMessage()!=null)) {
			result = false;
		}
		if(!match.matches()) {
			result = false;
		}
		
		return result;
	}

}
