package com.main.Ticket;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;

import com.main.Employee.EmployeeMaster;
import com.main.Enum.PriorityMaster;
import com.main.Enum.ProjectMaster;
import com.main.Enum.StatusMaster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ticket_master")
public class TicketMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticket_id")
	private Long ticketId;
	
	@Column(name = "ticket_name")
	@NotBlank(message = "Ticket Name is required.")
	private String ticketName;
	
	@Column(name = "app_id")
	@NotBlank(message = "Ticket Id is required.")
	private String appId; 

	@Column(name = "project_name")
	@NotNull(message = "Project Name must be selected")
	@Enumerated(EnumType.STRING)
	private ProjectMaster project;
	
	@NotNull(message = "Priority must be selected...")
	@Column(name = "priority")
	@Enumerated(EnumType.STRING)
	private PriorityMaster priority;
	
 
	@Column(name = "assigned_to")
	private String assignedTo; 
	
	@Lob
	@Column(name = "attachment",columnDefinition = "longblob")
	private byte[] attachment;
	
	@Column(name = "attachment_name")
	private String attachmentName;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusMaster status ;
	
	private Boolean active;
	
	public TicketMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param ticketId
	 * @param ticketName
	 * @param appId
	 * @param project
	 * @param priority
	 * @param assignedTo
	 * @param attachment
	 * @param attachmentName
	 * @param status
	 * @param active
	 */
	public TicketMaster(Long ticketId, @NotBlank(message = "Ticket Name is required.") String ticketName,
			@NotBlank(message = "Ticket Id is required.") String appId,
			@NotNull(message = "Project Name must be selected") ProjectMaster project,
			@NotNull(message = "Priority must be selected...") PriorityMaster priority, String assignedTo,
			byte[] attachment, String attachmentName, StatusMaster status, Boolean active) {
		super();
		this.ticketId = ticketId;
		this.ticketName = ticketName;
		this.appId = appId;
		this.project = project;
		this.priority = priority;
		this.assignedTo = assignedTo;
		this.attachment = attachment;
		this.attachmentName = attachmentName;
		this.status = status;
		this.active = active;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public ProjectMaster getProject() {
		return project;
	}

	public void setProject(ProjectMaster project) {
		this.project = project;
	}

	public PriorityMaster getPriority() {
		return priority;
	}

	public void setPriority(PriorityMaster priority) {
		this.priority = priority;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public StatusMaster getStatus() {
		return status;
	}

	public void setStatus(StatusMaster status) {
		this.status = status;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "TicketMaster [ticketId=" + ticketId + ", ticketName=" + ticketName + ", appId=" + appId + ", project="
				+ project + ", priority=" + priority + ", assignedTo=" + assignedTo + ", attachment="
				+ Arrays.toString(attachment) + ", attachmentName=" + attachmentName + ", status=" + status
				+ ", active=" + active + "]";
	}
 
	 
}












