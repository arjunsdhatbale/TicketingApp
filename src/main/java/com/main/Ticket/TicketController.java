package com.main.Ticket;

 
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.http.MediaType;
import com.main.Enum.PriorityMaster;
import com.main.Enum.ProjectMaster;
import com.main.Enum.StatusMaster;

import org.springframework.boot.ApplicationArguments;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
	
	private final TicketRepo ticketRepo; 
	private final TicketService ticketService;
	
	public TicketController(TicketRepo ticketRepo,TicketService ticketService) {
		this.ticketRepo = ticketRepo;
		this.ticketService = ticketService;
	}

	@GetMapping("/viewTickets")
	public String viewAllTickets(
			@RequestParam(required = false) Long ticketId,
			@RequestParam(required = false) ProjectMaster project,
			@RequestParam(required = false) StatusMaster status,
			@RequestParam(defaultValue = "ticketId") String sortField,
			@RequestParam(defaultValue = "desc") String sortDir,
			Model model
	) {
		logger.info("Request received to view all tickets...");

		// Apply filters using Specification (or fallback to simple findAll if not using Specification)
		Specification<TicketMaster> specification = Specification
				.where(TicketSpecification.hasTicketId(ticketId))
				.and(TicketSpecification.hasProject(project))
				.and(TicketSpecification.hasStatus(status));

		Sort sort = sortDir.equalsIgnoreCase("asc") ?
				Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		List<TicketMaster> ticketList = ticketRepo.findAll(specification, sort);

		// Send ticket list to JSP
		model.addAttribute("tickets", ticketList);
		model.addAttribute("projectList", ProjectMaster.values());
		model.addAttribute("statusList", StatusMaster.values());
		model.addAttribute("priorityList", PriorityMaster.values());

		// Preserve filter values
		model.addAttribute("ticketId", ticketId);
		model.addAttribute("project", project);
		model.addAttribute("status", status);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);

		return "ticket/viewTickets";
	}



	@GetMapping("/add")
	public String showAddForm(Model model) {
		logger.info("Request receive to show Add Ticket form...");
		model.addAttribute("ticket", new TicketMaster());
		model.addAttribute("priorities", PriorityMaster.values());
		model.addAttribute("projects", ProjectMaster.values());
		model.addAttribute("status", StatusMaster.values());
		return "ticket/ticketForm";
	}
	
	@PostMapping("/save")
	public String saveTicket(@Valid @ModelAttribute("ticket") TicketMaster ticketMaster,
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes,
			BindingResult bindingResult,
			Model model
			) {
		logger.info("Request Receive to add new Ticket..");
		if(bindingResult.hasErrors()) {
			model.addAttribute("priorities", PriorityMaster.values());
			model.addAttribute("project", ProjectMaster.values());
			model.addAttribute("status", StatusMaster.values());
			return "ticket/ticketForm";
		}
		if(!file.isEmpty()) {
			try {
				ticketMaster.setAttachment(file.getBytes());
				ticketMaster.setAttachmentName(file.getOriginalFilename());
			}catch (IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("msg","Failed to upload file.");
				return "redirect:/ticket/viewTickets"; 
			}
		}
		 
		ticketService.saveTicket(ticketMaster);
		redirectAttributes.addAttribute("msg","Ticket saved successfully.");
		return "redirect:/ticket/viewTickets";
	}
	 
	@GetMapping("/edit/{ticketId}")
	public String showEditForm(@PathVariable("ticketId") Long ticketId, Model model) {
	    logger.info("Request receive to update edit form...");
	    TicketMaster ticketMaster = ticketRepo.findById(ticketId)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid Ticket id : " + ticketId));
	    
	    model.addAttribute("ticket", ticketMaster);
	    model.addAttribute("priority", PriorityMaster.values());
	    model.addAttribute("project", ProjectMaster.values());
	    model.addAttribute("status", StatusMaster.values());
	    return "ticket/ticketForm";  
	}
	
	@GetMapping("/delete/{ticketId}")
	public String deleteTicket(@PathVariable("ticketId") Long ticketId, RedirectAttributes redirectAttributes ) {
		logger.info("Request receive to delete Ticket..."); 
		ticketRepo.deleteById(ticketId);
		redirectAttributes.addFlashAttribute("msg", "Ticket Delete Successfully...");
		return "redirect:/ticket/viewTickets";
		
	}

	@GetMapping("/download/{ticketId}")
	public ResponseEntity<byte[]> downloadAttachments(@PathVariable("ticketId") Long ticketId){
		
		logger.info("Request receive to download Attachments...");
		
		TicketMaster ticket = ticketService.getTicketByid(ticketId);
		if(ticket == null || ticket.getAttachment() == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ticket.getAttachmentName()+ "\"")
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(ticket.getAttachment());
		 
	}
	
	@GetMapping("/viewAttachment/{ticketId}")
	public ResponseEntity<byte[]> viewAttachment(@PathVariable("ticketId") Long ticketId){
		logger.info("Request Receive to View Attachments...");
		
		TicketMaster ticket = ticketService.getTicketByid(ticketId);
		if(ticket != null && ticket.getAttachment() != null) {
			MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
			if(ticket.getAttachmentName().endsWith(".pdf")) {
				mediaType = MediaType.APPLICATION_PDF;
			}
			return ResponseEntity.ok()
					.contentType(mediaType)
					.body(ticket.getAttachment());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
}






































