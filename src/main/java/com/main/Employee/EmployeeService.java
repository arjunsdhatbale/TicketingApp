package com.main.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo; 
	
	public void addEmployee(EmployeeMaster emp) {
		employeeRepo.insertEmployee(emp.getFirstName(),
				emp.getLastName(), emp.getEmail(),emp.getDesignation(), emp.getRole(), emp.isActive());
	}
	
	public void updateEmployee(EmployeeMaster emp) {
		employeeRepo.updateEmployee(emp.getEmp_Id(), emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getDesignation(), emp.getRole(), emp.isActive());
	}
	
	public void deleteEmployee(EmployeeMaster emp) {
		employeeRepo.deleteEmployee(emp.getEmp_Id());
	}
	
	public List<EmployeeMaster> getAllListOfEmployee(EmployeeMaster emp) {
		if(emp == null) emp = new EmployeeMaster();
		return employeeRepo.getAllEmployee(emp.getEmp_Id(), emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getDesignation(), emp.getRole(), emp.isActive());
	}
	
	
}
