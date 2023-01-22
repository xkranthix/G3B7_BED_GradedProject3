package com.ticket.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticket.tracker.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	@Query(value = "select * from ticket where title like  %:keyword% or description like %:keyword%", nativeQuery = true)
	List<Ticket> findByKeyword(@Param("keyword") String keyword);
}
