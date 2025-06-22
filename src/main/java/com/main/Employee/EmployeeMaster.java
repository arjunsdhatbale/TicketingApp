package com.main.Employee;

import com.main.Enum.DesignationMaster;
import com.main.Enum.RoleMaster;
import com.main.Ticket.TicketMaster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_master")
public class EmployeeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_Id")
    private Long empId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "designation")
    @Enumerated(EnumType.STRING)
    private DesignationMaster designation;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleMaster role;

//    @OneToOne( )
//    @PrimaryKeyJoinColumn(name = "ticket_id")
//    private TicketMaster ticketMaster; 
    // Default constructor (required for JPA)
    public EmployeeMaster() {
        super();
    }

    // Parameterized constructor
    public EmployeeMaster(Long empId, String firstName, String lastName, DesignationMaster designation, RoleMaster role) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.role = role;
    }

    // Getters and Setters
    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
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

    // toString() method for debugging/logging purposes
    @Override
    public String toString() {
        return "EmployeeMaster [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName
                + ", designation=" + designation + ", role=" + role + "]";
    }
}
