package com.akshdev.email_sender.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akshdev.email_sender.service.EmailSenderService;
import com.akshdev.emain_sender.dto.MailObject;

@RestController
@RequestMapping("/mail")
public class EmailSenderApi {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EmailSenderService emailService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/send", method= RequestMethod.POST)
	
	public ResponseEntity sendMail(@RequestBody MailObject mailDetails) {
		if(emailService.isValid(mailDetails)) {
			log.info("Sending mail");
			emailService.sendSimpleMessage(mailDetails.getTo(), mailDetails.getSubject(), mailDetails.getMessage());
			return new ResponseEntity(new Message(HttpStatus.CREATED.value(),"success", "Mail has been sent"), HttpStatus.CREATED);
		}
		log.info("Validation Fails for mail Object");
		return new ResponseEntity(new Message(HttpStatus.BAD_REQUEST.value(),"error", "Please provide all valid values"), HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method= RequestMethod.GET)
	
	public ResponseEntity testMail() {
		return new ResponseEntity(new Message(HttpStatus.OK.value(), "success", "It works"), HttpStatus.OK);
	}
	
	
	/*@RequestMapping(value="/sendTemplate", method= RequestMethod.GET)
	public String sendMailNewsLetter() {
		log.info("Sending mail with newsLetter");
		emailService.sendMailWithTemplate("im.anupgupta03@gmail.com", "Test", "Test mail");
		return "Please check your Mail!";
	}*/
	

}
