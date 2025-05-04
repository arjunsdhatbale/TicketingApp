package com.main.Dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@GetMapping
	public String dashboad() {
		logger.info("Request receive to view Dashboard...");
		return "dashboard";
	}
	
	
	
}
