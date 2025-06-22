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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.Enum.DesignationMaster;
import com.main.Enum.RoleMaster;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/viewEmployee")
	public String viewAllEmployee(Model model) {
		logger.info("Request receive to view all Employees...");
		List<EmployeeMaster> listOfEmployees = employeeService.listOfEmployee();
		model.addAttribute("employees", listOfEmployees);
		model.addAttribute("role", RoleMaster.values());
		model.addAttribute("designation", DesignationMaster.values());
		return "employee/viewEmployee";
		 
	}
	
	@GetMapping("/add")
	public String getEmployeeForm(Model model) {
	    logger.info("Request received to add Employee form...");
	    
	    // Passing enum values to the model
	    model.addAttribute("designations", DesignationMaster.values());
	    model.addAttribute("roles", RoleMaster.values());

	    model.addAttribute("employee", new EmployeeMaster()); 
	    return "employee/employeeForm";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") EmployeeMaster employeeMaster,
			RedirectAttributes redirectAttributes,
			BindingResult bindingResult,
			Model model) {
		
		logger.info("Request receive to save Employee..");
		if(bindingResult.hasErrors()) {
			model.addAttribute("msg", "Error occured during save the employeee");
			return "redirect:/employee/employeeForm";
		}
		
		employeeService.saveEmployee(employeeMaster);
		
		redirectAttributes.addFlashAttribute("msg","Employee Saved Successfully...");
		
		return "redirect:/employee/viewEmployee";
		
	}
	
	@GetMapping("/edit/{empId}")
	public String editEmployee(@PathVariable("empId") Long empId, Model model) {
	    logger.info("Request received to Edit/Update Employee...");

	    EmployeeMaster existingEmp = employeeService.findeByEmployeeId(empId)
	            .orElseThrow(() -> new IllegalArgumentException("Employee not found by Employee Id : " + empId));

	    model.addAttribute("employee", existingEmp);

	    // Passing enum values for dropdowns
	    model.addAttribute("designations", DesignationMaster.values());
	    model.addAttribute("roles", RoleMaster.values());

	    return "employee/employeeForm"; 
	}
	@GetMapping("/delete/{empId}")
	public String deleteEmployee(@PathVariable("empId") Long empId, RedirectAttributes redirectAttributes) {
		logger.info("Request receive to Delete employee...");
		employeeService.deleteByEmployeeId(empId);
		redirectAttributes.addFlashAttribute("msg", "Employee Deleted Successfully...");
		return "redirect:/employee/viewEmployee";
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
