package com.ticket.tracker.service;

import java.util.List;

import com.ticket.tracker.entity.Ticket;

public interface TicketService {
	List<Ticket> getAllTickets();

	List<Ticket> getByKeyword(String keyword);

	void save(Ticket ticket);

	Ticket getById(Long id);

	String deleteByID(Long id);

	Ticket view(Long id);

	Ticket setDate(Ticket ticket);

}
