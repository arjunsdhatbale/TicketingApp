package com.main.User;


import jakarta.validation.constraints.Email;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

 

 
@Entity
@Table(name = "user_master")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id ; 
	@Column(name ="user_name",nullable = false,unique = true,length = 100 )
	private String userName ; 
	@Column(name ="password",nullable = false,length = 100 )
	private String password;
	
	@Column(name = "email_id",nullable = false,unique = true)
	@Email(message = "Invalide email format...")
	private String email;
	
	@Column(name = "active",nullable = false)
	private Boolean active = true;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param userName
	 * @param password
	 */
	public User(Long id, String userName, String password,String email,Boolean active) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email; 
		this.active = active;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
	public boolean isActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password +", email=" + email +", active=" + active + "]";
	}
	
	 
}
