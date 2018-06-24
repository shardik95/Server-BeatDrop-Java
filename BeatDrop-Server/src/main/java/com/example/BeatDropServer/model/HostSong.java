package com.example.BeatDropServer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class HostSong {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String songName;
	private String duration;
	private String imgUrl;
	private String spotifySongId;
	
	@ManyToOne
	@JsonIgnore
	private HostPlaylist playlist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSpotifySongId() {
		return spotifySongId;
	}

	public void setSpotifySongId(String spotifySongId) {
		this.spotifySongId = spotifySongId;
	}

	public HostPlaylist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(HostPlaylist playlist) {
		this.playlist = playlist;
	}
	
	
}
