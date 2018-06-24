package com.example.BeatDropServer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Host")
public class Host extends User{
	
	@OneToMany(mappedBy="host",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<Party> parties;
	
	@OneToMany(mappedBy="host",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<Ticket> tickets;
	
	private int popularity;
	private int pastParties;
	public List<Party> getParties() {
		return parties;
	}
	public void setParties(List<Party> parties) {
		this.parties = parties;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	public int getPastParties() {
		return pastParties;
	}
	public void setPastParties(int pastParties) {
		this.pastParties = pastParties;
	}
	
	
	
}
