package com.example.BeatDropServer.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.example.BeatDropServer.model.Playlist;;


@Entity
public class Song {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String songName;
	private String duration;
	private String imgUrl;
	private String spotifySongId;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "song_playlist", joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "playlist_id", referencedColumnName = "id"))
	private List<Playlist> playlists;
	
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
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
	
}
