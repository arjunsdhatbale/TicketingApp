package com.main.Ticket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.main.Employee.EmployeeMaster;
import com.main.Employee.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.main.Enum.StatusMaster;

import jakarta.validation.Valid;

@Service
public class TicketService {

	private final static Logger logger = LoggerFactory.getLogger(TicketService.class);
	private final  TicketRepo ticketRepo;
	private final EmployeeService employeeService;
	
	public TicketService(TicketRepo ticketRepo, EmployeeService employeeService) {

		this.ticketRepo = ticketRepo;
		this.employeeService = employeeService;
	}

	public TicketMaster getTicketByid(Long ticketId) {
		logger.info("Request receive to get ticket by ticket id ...");
		
		TicketMaster ticket = ticketRepo.findById(ticketId)
				.orElseThrow(() -> new NullPointerException("Ticket is not found by ticket id: " + ticketId));
		
		return ticket;
	}

	public boolean assignedTicketToEmployee(Long ticketId,Long employeeId) {

		Optional<TicketMaster> ticket = this.ticketRepo.findById(ticketId);
		Optional<EmployeeMaster> emp = employeeService.findeByEmployeeId(employeeId);
		if(ticket.isPresent() && emp.isPresent()){
			TicketMaster optionalTicket = ticket.get();
			EmployeeMaster optionalEmployee = emp.get();
			optionalTicket.setAssignedTo(optionalEmployee.getFirstName() + " " + optionalEmployee.getLastName());
			optionalTicket.setStatus(StatusMaster.INPROGRESS);
			this.ticketRepo.save(optionalTicket);
			return true;
		}
		return false;
	}

	public List<TicketMaster> getAllTickets(){

		List<TicketMaster> listOfAllTickets = this.ticketRepo.findAll();

		List<TicketMaster> updatedTickts = listOfAllTickets.stream()
				.filter(ticket -> (ticket.getAssignedTo() == null) || (ticket.getAssignedTo().trim().isEmpty())
						&& ticket.getStatus() != StatusMaster.NOT_ASSIGNED)
				.peek(ticket -> ticket.setStatus(StatusMaster.NOT_ASSIGNED))
				.toList();
		if(!updatedTickts.isEmpty()){
			this.ticketRepo.saveAll(updatedTickts);
		}
		return listOfAllTickets;
//		List<TicketMaster> tickets = ticketRepo.findAll();
//		List<TicketMaster> updateTickets = new ArrayList<>();
//		for(TicketMaster ticket : tickets){
//			if(ticket.getAssignedTo() == null || ticket.getAssignedTo().trim().isEmpty()){
//				if(ticket.getStatus() != StatusMaster.NOT_ASSIGNED){
//					ticket.setStatus(StatusMaster.NOT_ASSIGNED);
//					updateTickets.add(ticket);
//				}
//			}
//		}
//		if(!updateTickets.isEmpty()){
//			this.ticketRepo.saveAll(updateTickets);
//		}
//		return tickets;
	}

	public List<TicketMaster> getAllNotAssignedTickets() {
		logger.info("Request receive to find all not Assigned tickets...");
		List<TicketMaster> listOfTickets = this.ticketRepo.findByStatus(StatusMaster.NOT_ASSIGNED);
		return listOfTickets;
	}
	
	public List<TicketMaster> getAllInprogressTickets() {
		logger.info("Request receive to find all not Assigned tickets...");
		return ticketRepo.findByStatus(StatusMaster.INPROGRESS);
	}
	public List<TicketMaster> getAllSolvedTickets() {
		logger.info("Request receive to find all not Assigned tickets...");
		return ticketRepo.findByStatus(StatusMaster.SOLVED);
	}


	public void saveTicket(TicketMaster ticketMaster) {
		TicketMaster ticket;
		ticket = ticketMaster;
		ticket.setStatus(StatusMaster.NOT_ASSIGNED);
		this.ticketRepo.save(ticket);
	}
}
















