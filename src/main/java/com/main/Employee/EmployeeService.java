package com.main.Employee;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo; 
	
	@PersistenceContext
	 private EntityManager entityManager;
	
	public List<EmployeeMaster> listOfEmployee(){
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(
				"getAllEmployee", EmployeeMaster.class);
		query.registerStoredProcedureParameter("empId",Long.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("firstName",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("lastName",String.class,ParameterMode.IN);
		query.setParameter("empId",null);
		query.setParameter("firstName",null);
		query.setParameter("lastName", null);
		return query.getResultList();
	}
	
	@Transactional
	public void saveEmployee(@Valid EmployeeMaster employeeMaster) {
		
		StoredProcedureQuery query =
				entityManager.createStoredProcedureQuery("insert_employee",EmployeeMaster.class);
				
		
		query.registerStoredProcedureParameter("firstName",String.class,ParameterMode.IN);
		query.registerStoredProcedureParameter("lastName",String.class,ParameterMode.IN);
		
		query.setParameter("firstName",employeeMaster.getFirstName());
		query.setParameter("lastName", employeeMaster.getLastName());
 
		query.execute();
	}

	public Optional<EmployeeMaster> findeByEmployeeId(Long empId) {
		Optional<EmployeeMaster> emp = employeeRepo.findById(empId);
		return emp;
	}
}
