package com.ticket.tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticket.tracker.entity.Ticket;
import com.ticket.tracker.service.TicketService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/tickets")
public class FormController {
	@Autowired
	TicketService ticketService;

	@GetMapping("/updateInfo/{id}")
	@ApiOperation(value = "Fetches the Update form", notes = "This API is used fetch and display the Update form")
	public String updateTicket(@PathVariable(value = "id") Long id, Model theModel) {
		Ticket tickets = ticketService.getById(id);
		theModel.addAttribute("tickets", tickets);
		return "update";
	}

	@GetMapping("/register")
	@ApiOperation(value = "Fetches ticket creation form", notes = "This API is used fetch and display the ticket creation form")
	public String newTicket(Model theModel) {
		Ticket tickets = new Ticket();
		theModel.addAttribute("tickets", tickets);
		return "newTicket";
	}
}
