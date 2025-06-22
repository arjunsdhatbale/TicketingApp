package com.main.TicketProgress;

import java.util.List;

import java.util.Optional;

import com.main.Ticket.TicketRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.main.Employee.EmployeeMaster;
import com.main.Employee.EmployeeService;
import com.main.Enum.PriorityMaster;
import com.main.Enum.ProjectMaster;
import com.main.Enum.StatusMaster;
import com.main.Ticket.TicketMaster;
import com.main.Ticket.TicketService;

@Controller
@RequestMapping("/progress")
public class TicketProgressController {

	private final static Logger logger = LoggerFactory.getLogger(TicketProgressController.class);

	private final TicketService ticketService;
	private final EmployeeService employeeService;
	private final TicketRepo ticketRepo;
	public TicketProgressController(TicketService ticketService,EmployeeService employeeService, TicketRepo ticketRepo) {
		this.ticketService = ticketService;
		this.employeeService = employeeService;
		this.ticketRepo = ticketRepo;
	}
	
	public void prepareView(Model model,List<TicketMaster> tickets) {
		model.addAttribute("tickets", tickets);
		model.addAttribute("priority", PriorityMaster.values());
		model.addAttribute("project", ProjectMaster.values());
		model.addAttribute("status", StatusMaster.values());
	
	}

	@GetMapping
	public String viewAllTickets(Model model) {
		logger.info("Request receive to view all tickets...");
		prepareView(model, ticketService.getAllTickets());
		return  "progress/viewAllTickets";
	}

	@GetMapping("/notAssigned")
	public String viewNotAssignedAllTickets(Model model) {
		logger.info("Request receive to view all not assigned tickets...");
		model.addAttribute("employees", employeeService.listOfEmployee());
		List<TicketMaster> listOfNotAssignedTickets = ticketService.getAllNotAssignedTickets();
		prepareView(model, listOfNotAssignedTickets);
		return "progress/notAssigned";
	}

	@GetMapping("/inProgress")
	public String viewAllInprogressTickets(Model model) {
		logger.info("Request receive to view all not assigned tickets...");
		prepareView(model, ticketService.getAllInprogressTickets());
		return "progress/inProgress";		 		
	}
	
	@GetMapping("/solved")
	public String viewAllSolvedTickets(Model model) {
		logger.info("Request receive to view all not assigned tickets...");
		prepareView(model, ticketService.getAllSolvedTickets());
		return "progress/solved";		 		
	}
	
	@PostMapping("/assign")
	public String assignTicket(
	        @RequestParam Long ticketId,
	        @RequestParam Long employeeId,
	        RedirectAttributes redirectAttributes) {
		logger.info("Request received to assigned ticket...");
	    try {
			boolean isAssigned = this.ticketService.assignedTicketToEmployee(ticketId,employeeId);
			if(isAssigned){
				redirectAttributes.addFlashAttribute("msg","Ticket assigned Successfully...");
			}else {
				redirectAttributes.addFlashAttribute("msg","Ticket or Employee not Found...");
			}
	    } catch (Exception e) {
			logger.error("Error while assiging ticket . " , e);

	        redirectAttributes.addFlashAttribute("msg", "Something went wrong: " + e.getMessage());
	    }
		return "redirect:/progress/notAssigned";
	}

	@PostMapping("/updateStatus")
	public String updateTicketStatus(@RequestParam Long ticketId,
									 @RequestParam StatusMaster status,
									 RedirectAttributes redirectAttributes) {
		TicketMaster ticket = ticketService.getTicketByid(ticketId);
		if (ticket != null) {
			ticket.setStatus(status);
			ticketRepo.save(ticket);
			redirectAttributes.addFlashAttribute("msg", "Ticket status updated successfully.");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Ticket not found.");
		}
		return "redirect:/progress/inProgress"; // adjust according to current tab
	}






}
