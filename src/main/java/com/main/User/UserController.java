package com.main.User;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

 
 
 

@Controller
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepo userRepo; 
	
	@GetMapping("/add")
	public String showAddUserForm(Model model) {
		logger.info("Request receive to show add user form...");
		model.addAttribute("user", new User());
		return "user/addUser";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute("user") User user) {
		logger.info("Request receive to save user...");
		userRepo.save(user);
		return "redirect:/users/view";
	}

	@GetMapping("/view")
	public String viewUsers(Model model) {
		logger.info("Request receive to view all users...");
		List<User> userList  = userRepo.findAll();
		model.addAttribute("users", userList);
		return "user/viewUserList";
	}
	
	@GetMapping("/edit/{id}")
	public String showEditUserForm(@PathVariable("id") Long id, Model model ) {
		logger.info("Request receive to update userform ...");
		User user = userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid User id : " + id));
		model.addAttribute("user", user);
		return "user/editUser";
		
	}
	
	@PostMapping("/update")
	public String updateUser(@ModelAttribute("user") User user) {
		logger.info("Request receive to update user by use id : " + user.getId());
		User existingUser = userRepo.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("Invalide user id : " + user.getId()));
		existingUser.setUserName(user.getUserName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setActive(user.isActive());
		userRepo.save(existingUser);
		return "redirect:/users/view";
		
	}
	
	@GetMapping("/users/view")
	public String getListOfUpdatedUsers(Model model,@RequestParam(value = "success",required = false) String success) {
		logger.info("Request receive to view all Users page...");
		List<User> listOfUsrs = userRepo.findAll();
		model.addAttribute("users", listOfUsrs);
		if("true".equals(success)) {
			model.addAttribute("msg","User Deleted Successfully.");
		}
		return "viewUserList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id,Model model) {
		logger.info("Request receive to delte user by user id : " + id);
		userRepo.deleteById(id);
		List<User> listOfUsers = userRepo.findAll();
		model.addAttribute("users", listOfUsers);
		return "redirect:/users/view?success=true";
	}
	
}
