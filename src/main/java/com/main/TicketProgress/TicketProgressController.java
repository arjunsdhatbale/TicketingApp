package com.main.TicketProgress;

import java.util.List;

import java.util.Optional;
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
	public TicketProgressController(TicketService ticketService,EmployeeService employeeService) {
		this.ticketService = ticketService;
		this.employeeService = employeeService; 
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
		prepareView(model, ticketService.getAllNotAssignedTickets());
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
		System.out.println("===== ASSIGN API CALLED =====");
	    try {
	        // 1. Fetch ticket
	        TicketMaster ticket = ticketService.getTicketByid(ticketId);
	        if (ticket == null) {
	            redirectAttributes.addFlashAttribute("msg", "Ticket not found!");
	            return "redirect:/progress/notAssigned";
	        }

	        // 2. Fetch employee
	        Optional<EmployeeMaster> emp = employeeService.findeByEmployeeId(employeeId);
	        if (emp.isEmpty()) {
	            redirectAttributes.addFlashAttribute("msg", "Employee not found!");
	            return "redirect:/progress/notAssigned";
	        }

	        // 3. Assign employee and change status
	        ticket.setAssignedTo(emp.get().getFirstName() + " " + emp.get().getLastName());
	        ticket.setStatus(StatusMaster.INPROGRESS);
	        ticketService.saveTicket(ticket);

	        redirectAttributes.addFlashAttribute("msg", "Ticket assigned successfully!");
	        return "redirect:/progress/notAssigned";

	    } catch (Exception e) {
	        // Optional: Log the error for debugging
	        e.printStackTrace(); // or use a logger
	        redirectAttributes.addFlashAttribute("msg", "Something went wrong: " + e.getMessage());
	        return "redirect:/progress/notAssigned";
	    }
	}


	
	
	
}
