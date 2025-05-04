package com.main.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.main.Employee.EmployeeMaster;
import com.main.Enum.DesignationMaster;
import com.main.Enum.RoleMaster;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeMaster, Long>{

	
	@Procedure(name = "insert_employee")
	void insertEmployee(String firstName,String lastName,String email,DesignationMaster designationMaster,RoleMaster roleMaster,boolean b);
	
	@Procedure(name = "update_employee")
	void updateEmployee(Long emp_Id,String firstName,String lastName,String email,DesignationMaster designation,RoleMaster role,Boolean active);
	
	@Procedure(name = "delete_employee")
	void deleteEmployee(Long emp_Id);
	
	@Procedure(name = "getAllEmployee")
	List<EmployeeMaster> getAllEmployee(Long emp_Id,String firstName,String lastName,String email,DesignationMaster designation,RoleMaster role,Boolean active);
	 
}
