package com.main.Employee;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public String viewAllEmployee(Model model) {
		logger.info("Request receive to view all Employees...");
		List<EmployeeMaster> listOfEmployees = employeeService.listOfEmployee();
		model.addAttribute("employees", listOfEmployees);
		return "employee/viewEmployee";
		 
	}
	
	@GetMapping("/add")
	public String getEmployeeForm(Model model) {
		logger.info("Request received to add Employee form...");
		model.addAttribute("employee", new EmployeeMaster()); 
		return "employee/employeeForm";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeMaster employeeMaster, RedirectAttributes redirectAttributes) {
		
		logger.info("Request receive to save Employee..");
		
		employeeService.saveEmployee(employeeMaster);
		
		redirectAttributes.addFlashAttribute("msg","Employee Saved Successfully...");
		
		return "redirect:/employee";
		
	}
	
	@PostMapping("/edit")
	public String editEmployee(@Valid @ModelAttribute("employee") EmployeeMaster employeeMaster,
	                           RedirectAttributes redirectAttributes) {
	    logger.info("Request received to Edit/Update Employee...");

	    Optional<EmployeeMaster> existingEmp = employeeService.findeByEmployeeId(employeeMaster.getEmpId());

	    if (existingEmp.isPresent()) {
	        EmployeeMaster updateEmp = existingEmp.get();
	        updateEmp.setFirstName(employeeMaster.getFirstName());
	        updateEmp.setLastName(employeeMaster.getLastName());

	        employeeService.saveEmployee(updateEmp);
	        redirectAttributes.addFlashAttribute("msg", "Employee updated successfully.");
	    } else {
	        redirectAttributes.addFlashAttribute("error", "Employee not found.");
	    }

	    return "redirect:/employee";  // Fix: use `/employee` instead of `redirect/employee`
	}

	
//	Connection conn = dataSource.getConnection();
//	CallableStatement stmt = conn.prepareCall("{call manage_employee(?, ?, ?, ?, ?)}");
//	stmt.setString(1, "CREATE");
//	stmt.setNull(2, Types.INTEGER);
//	stmt.setString(3, "John Doe");
//	stmt.setString(4, "john@example.com");
//	stmt.setDouble(5, 60000.00);
//	stmt.execute();
	
	
}
