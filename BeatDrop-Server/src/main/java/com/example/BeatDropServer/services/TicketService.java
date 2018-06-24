package com.example.BeatDropServer.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Host;
import com.example.BeatDropServer.model.Ticket;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.HostRepository;
import com.example.BeatDropServer.repositories.TicketRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private HostRepository hostRepository;
	
	@PostMapping("/api/ticket")
	public Ticket createTicket(@RequestBody Ticket ticket,HttpSession session) {
		User h = (User) session.getAttribute("currentUser");
		Iterable<Host> host = hostRepository.findHostByUserName(h.getUserName());
		Iterator<Host> itr = host.iterator();
			Host data = itr.next();
			ticket.setHost(data);
			return ticketRepository.save(ticket);
	}
	
	@GetMapping("/api/ticket/{hostId}")
	public List<Ticket> getTickets(@PathVariable("hostId") int hostId){
		Optional<Host> data = hostRepository.findById(hostId);
		if(data.isPresent()) {
			Host host = data.get();
			return host.getTickets();
		}
		return null;
	}
	
	@PutMapping("/api/ticket/{id}")
	public Ticket updateTicket(@RequestBody Ticket ticket,@PathVariable("id")int id) {
		Optional<Ticket> data =ticketRepository.findById(id);
		if(data.isPresent()) {
			Ticket ticket1 =data.get();
			ticket1.setEventName(ticket.getEventName());
			ticket1.setAvailTickets(ticket.getAvailTickets());
			//ticket1.setMaxTickets(""+(Integer.parseInt(ticket.getMaxTickets())-(Integer.parseInt(ticket1.getMaxTickets())-Integer.parseInt(ticket1.getAvailTickets()))));
			ticket1.setPrice(ticket.getPrice());
			return ticketRepository.save(ticket1);
		}
		return null;
	}
	
	@DeleteMapping("/api/ticket/{ticketId}")
	public int deleteTicket(@PathVariable("ticketId") int id) {
		ticketRepository.deleteById(id);
		return 1;
	}
}
