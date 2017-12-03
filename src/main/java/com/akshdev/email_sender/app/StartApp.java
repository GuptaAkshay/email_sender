package com.akshdev.email_sender.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.akshdev.email_sender"})
@EnableAutoConfiguration
public class StartApp {
	
	private static Logger log = LoggerFactory.getLogger(StartApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StartApp.class, args);
		log.info("Mail App Started");
		//System.out.println("Mail App Started");
	}

}
