package com.main.Ticket;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.main.Enum.StatusMaster;

@Repository
public interface TicketRepo extends JpaRepository<TicketMaster, Long>, JpaSpecificationExecutor<TicketMaster>{

	List<TicketMaster> findByStatus(StatusMaster status);
}
