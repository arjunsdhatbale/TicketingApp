package com.main.Employee;

import com.main.Enum.DesignationMaster;
import com.main.Enum.RoleMaster;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "employee_master")
public class EmployeeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emp_Id;
	 
	private String firstName; 
	private String lastName;
	private String email;
	@Enumerated(EnumType.STRING)
	private DesignationMaster designation;
	@Enumerated(EnumType.STRING)
	private RoleMaster role;
	private boolean active;
	/**
	 * 
	 */
	public EmployeeMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param emp_Id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param designation
	 * @param role
	 * @param active
	 */
	public EmployeeMaster(Long emp_Id, String firstName, String lastName, String email, DesignationMaster designation,
			RoleMaster role, boolean active) {
		super();
		this.emp_Id = emp_Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.designation = designation;
		this.role = role;
		this.active = active;
	}
	public Long getEmp_Id() {
		return emp_Id;
	}
	public void setEmp_Id(Long emp_Id) {
		this.emp_Id = emp_Id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DesignationMaster getDesignation() {
		return designation;
	}
	public void setDesignation(DesignationMaster designation) {
		this.designation = designation;
	}
	public RoleMaster getRole() {
		return role;
	}
	public void setRole(RoleMaster role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "EmloyeeMaster [emp_Id=" + emp_Id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", designation=" + designation + ", role=" + role + ", active=" + active + "]";
	}
	  
	
}
