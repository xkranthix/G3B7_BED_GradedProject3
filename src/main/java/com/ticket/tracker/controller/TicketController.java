package com.ticket.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ticket.tracker.entity.Ticket;
import com.ticket.tracker.service.TicketService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/tickets")
public class TicketController {
	@Autowired
	TicketService ticketService;

	@GetMapping
	@ApiOperation(value = "Get all tickets", notes = "This API is used to get all ticket and display them on thymleaf UI page")

	public String Home(Model model) {

		model.addAttribute("allticketlist", ticketService.getAllTickets());
		return "index";
	}

	@PostMapping
	@ApiOperation(value = "Register and Save a New Ticket", notes = "This API is used to save the details of new ticket")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
		ModelAndView modelAndView = new ModelAndView("view");
		modelAndView.addObject("ticket", ticket);
		ticketService.save(ticketService.setDate(ticket));
		return "redirect:/tickets";
	}

	@GetMapping("/delete/{id}")
	@ApiOperation(value = "Delete an ticket with ID", notes = "This API is used to delete the Ticket whose ID is passed as parameter")
	public String deleteThroughID(@PathVariable(value = "id") Long id) {
		ticketService.deleteByID(id);
		return "redirect:/tickets";

	}

	@GetMapping("/view/{id}")
	public String view(@PathVariable(value = "id") Long id, Model theModel) {
		Ticket tickets = ticketService.getById(id);
		theModel.addAttribute("tickets", tickets);
		return "view";
	}

	@GetMapping("/search")
	public String searchForm(Ticket tickets, Model model, String keyword) {
		if (keyword != null) {
			List<Ticket> list = ticketService.getByKeyword(keyword);
			model.addAttribute("list", list);
		} else {
			List<Ticket> list = ticketService.getAllTickets();
			model.addAttribute("list", list);
		}
		return "search";
	}
}
