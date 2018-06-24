package com.example.BeatDropServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JsonIgnore
	private Host host;
	
	private String eventName;
	private String maxTickets;
	private String availTickets;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Host getHost() {
		return host;
	}
	public void setHost(Host host) {
		this.host = host;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getMaxTickets() {
		return maxTickets;
	}
	public void setMaxTickets(String maxTickets) {
		this.maxTickets = maxTickets;
	}
	public String getAvailTickets() {
		return availTickets;
	}
	public void setAvailTickets(String availTickets) {
		this.availTickets = availTickets;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
