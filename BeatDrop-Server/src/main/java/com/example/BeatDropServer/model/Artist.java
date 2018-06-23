package com.example.BeatDropServer.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Artist")
public class Artist extends User{
	
	private Date memberSince;
	
	@OneToMany(mappedBy="artist",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<UploadedSong> songs;

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public List<UploadedSong> getSongs() {
		return songs;
	}

	public void setSongs(List<UploadedSong> songs) {
		this.songs = songs;
	}
	
	
}
