package com.main.Ticket;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.main.Enum.PriorityMaster;
import com.main.Enum.ProjectMaster;
import com.main.Enum.StatusMaster;

public class TicketSpecification {
	
	public static Specification<TicketMaster> hasTicketId(Long ticketId){
		return (root,query,cb) -> 
			ticketId != null ? null : cb.equal(root.get("ticketId"), ticketId);
	}

    public static Specification<TicketMaster> hasTicketNameLike(String ticketName) {
        return (root, query, cb) ->
                StringUtils.hasText(ticketName) ? cb.like(cb.lower(root.get("ticketName")), "%" + ticketName.toLowerCase() + "%") : null;
    }

    public static Specification<TicketMaster> hasAppIdLike(String appId) {
        return (root, query, cb) ->
                StringUtils.hasText(appId) ? cb.like(cb.lower(root.get("appId")), "%" + appId.toLowerCase() + "%") : null;
    }

    public static Specification<TicketMaster> hasProject(ProjectMaster project) {
        return (root, query, cb) -> project != null ? cb.equal(root.get("project"), project) : null;
    }

    public static Specification<TicketMaster> hasPriority(PriorityMaster priority) {
        return (root, query, cb) -> priority != null ? cb.equal(root.get("priority"), priority) : null;
    }

    public static Specification<TicketMaster> hasStatus(StatusMaster status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }


    public static Specification<TicketMaster> hasActive(Boolean active) {
        return (root, query, cb) -> active != null ? cb.equal(root.get("active"), active) : null;
    }
}

