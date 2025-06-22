package com.main.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		logger.info("Request receive to Login user ...");
		return "login";
	}

	@RequestMapping(value = "/error",method = RequestMethod.GET)
	public String handleError() {
		return "error"; // Create an error.jsp page for a user-friendly message
	}
 
}
