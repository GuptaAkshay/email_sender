# README #

This README documents details about application and steps that are necessary to get your application up and running.

### What is this repository for? ###
**Application Name**: Email Sender App

**Description**: Email Sender App is Spring boot restful application which allows you to send email by providing details like To, Subject, Messages in json format

**Version**: V1.0


### How do I get set up? ###

##### Summary of set up #####
+ Download application import in eclipse.
+ Modify following application.properties file under src/main/resource/ folder
	
	spring.mail.username=username
	spring.mail.password=password
+ Run application as Spring Boot or Java Application. Application will be running at http://localhost:8080
+ To test application visit http://localhost:8080/mail which will provide response as below

	{
		"code": 200,
    	"type": "success",
    	"message": "success"	
	}	
		
##### Working With Actual Data #####

Note: Need to have rest client tool like Postman, etc.

+ Send a POST request over http://localhost:8080/mail/send with following header and body
	
    Header
    	Content-Type: application/json
    
    Body
		{
			"to":"akshgupta547@gmail.com",
			"subject":"TEST MAIL",
			"message":"Sending a test Mail"
		}
	 
+ You will recieve a response if all values are:

    VALID
		{
			"code": 201,
    		"type": "success",
    		"message": "Mail has been sent"
		}
	
    INVALID
		{
       	"code": 400,
    		"type": "error",
    		"message": "Please provide all valid values"
		}


