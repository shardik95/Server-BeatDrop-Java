package com.example.BeatDropServer.model;

import java.util.ArrayList;
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
public class HostPlaylist {

	@ManyToOne
	@JsonIgnore
	private Party party;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="playlist",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<HostSong> songs= new ArrayList<>();
	
	private String playlistName;

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}


	public List<HostSong> getSongs() {
		return songs;
	}

	public void setSongs(List<HostSong> songs) {
		this.songs = songs;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
