package com.main.Employee;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private EmployeeService employeeService; 

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public String viewAllEmployee(Model model) {
		logger.info("Request receive to view all Employees...");
		
		List<EmployeeMaster> listOfEmployee = employeeService.getAllListOfEmployee(new EmployeeMaster());
		model.addAttribute("employees", listOfEmployee);
		return "viewEmployee";
		 
	}
	
	
}
