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

	@GetMapping
	public String viewAllTickets(Model model) {
		logger.info("Request receive to view all tickets...");
		model.addAttribute("tickets", ticketService.getAllTickets());
		model.addAttribute("priority", PriorityMaster.values());
		model.addAttribute("project", ProjectMaster.values());
		model.addAttribute("status", StatusMaster.values());
		return "progress/viewAllTickets";
	}

	@GetMapping("/notAssigned")
	public String viewNotAssignedAllTickets(Model model) {
		logger.info("Request receive to view all not assigned tickets...");

		List<TicketMaster> listOfNotAssignedTickets = ticketService.getAllNotAssignedTickets();

		model.addAttribute("tickets", listOfNotAssignedTickets);
		model.addAttribute("priority", PriorityMaster.values());
		model.addAttribute("project", ProjectMaster.values());
		model.addAttribute("status", StatusMaster.values());
		return "progress/notAssigned";

	}

}
