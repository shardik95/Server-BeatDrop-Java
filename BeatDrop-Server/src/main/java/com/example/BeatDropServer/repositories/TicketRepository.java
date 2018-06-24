package com.example.BeatDropServer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.BeatDropServer.model.Host;
import com.example.BeatDropServer.model.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer>{
	
	@Query("SELECT u FROM Host u WHERE u.userName=:username")
	public Iterable<Host> findHostByUserName(@Param("username") String u);
}
