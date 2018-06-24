package com.example.BeatDropServer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Party {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JsonIgnore
	private Host host;
	
	private String partyname;
	
	@OneToMany(mappedBy="party",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<HostPlaylist> playlists;

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public String getPartyname() {
		return partyname;
	}

	public void setPartyname(String partyname) {
		this.partyname = partyname;
	}

	public List<HostPlaylist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<HostPlaylist> playlists) {
		this.playlists = playlists;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
