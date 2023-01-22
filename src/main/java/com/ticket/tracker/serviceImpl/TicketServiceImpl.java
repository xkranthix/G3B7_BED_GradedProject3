package com.ticket.tracker.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.tracker.entity.Ticket;
import com.ticket.tracker.repository.TicketRepository;
import com.ticket.tracker.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	@Override
	public Ticket setDate(Ticket ticket) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime dateCreated = LocalDateTime.now();
		ticket.setCreatedOn(dateFormatter.format(dateCreated));
		return ticket;
	}

	@Override
	public Ticket getById(Long id) {
		Ticket ticket = null;
		Optional<Ticket> optional = ticketRepository.findById(id);
		if (optional.isPresent()) {
			ticket = optional.get();
		} else {
			throw new RuntimeException("Ticket not found for id: " + id);
		}
		return ticket;
	}

	@Override
	public String deleteByID(Long id) {
		ticketRepository.deleteById(id);
		return "redirect:/";
	}

	@Override
	public Ticket view(Long id) {
		Ticket ticket = null;
		Optional<Ticket> optional = ticketRepository.findById(id);
		if (optional.isPresent()) {
			ticket = optional.get();
		} else {
			throw new RuntimeException("Ticket not found for id: " + id);
		}
		return ticket;
	}

	@Override
	public List<Ticket> getByKeyword(String keyword) {
		List<Ticket> list = ticketRepository.findByKeyword(keyword);
		return list;

	}
}
