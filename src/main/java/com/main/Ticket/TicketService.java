package com.main.Ticket;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.main.Enum.StatusMaster;

import jakarta.validation.Valid;

@Service
public class TicketService {

	private final static Logger logger = LoggerFactory.getLogger(TicketService.class);
	private TicketRepo ticketRepo; 
	
	public TicketService(TicketRepo ticketRepo) {
		this.ticketRepo = ticketRepo;
	}

	public TicketMaster getTicketByid(Long ticketId) {
		logger.info("Request receive to get ticket by ticket id ...");
		
		TicketMaster ticket = ticketRepo.findById(ticketId)
				.orElseThrow(() -> new NullPointerException("Ticket is not found by ticket id: " + ticketId));
		
		return ticket;
	}

	public void saveTicket(@Valid TicketMaster ticketMaster) {
		ticketRepo.save(ticketMaster);
	}

	public List<TicketMaster> getAllTickets(){
		List<TicketMaster> tickets = ticketRepo.findAll();
		return tickets;
	}

	public List<TicketMaster> getAllNotAssignedTickets() {
		logger.info("Request receive to find all not Assigned tickets...");
		return ticketRepo.findByStatus(StatusMaster.NOT_ASSIGNED);
	}
	
	public List<TicketMaster> getAllInprogressTickets() {
		logger.info("Request receive to find all not Assigned tickets...");
		return ticketRepo.findByStatus(StatusMaster.INPROGRESS);
	}
	public List<TicketMaster> getAllSolvedTickets() {
		logger.info("Request receive to find all not Assigned tickets...");
		return ticketRepo.findByStatus(StatusMaster.SOLVED);
	}
	
	
}
















