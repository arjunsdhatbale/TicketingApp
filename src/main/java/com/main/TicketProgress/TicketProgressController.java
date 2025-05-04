package com.main.TicketProgress;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.Enum.PriorityMaster;
import com.main.Enum.ProjectMaster;
import com.main.Enum.StatusMaster;
import com.main.Ticket.TicketMaster;
import com.main.Ticket.TicketService;

@Controller
@RequestMapping("/progress")
public class TicketProgressController {

	private final static Logger logger = LoggerFactory.getLogger(TicketProgressController.class);

	private TicketService ticketService;

	public TicketProgressController(TicketService ticketService) {
		this.ticketService = ticketService;
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
}
