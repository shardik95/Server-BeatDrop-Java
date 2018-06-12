package com.example.BeatDropServer.model;

import java.util.Date;
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
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	private Date dob;
	private String phone;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<Playlist> playlists;
	
	@ManyToOne
	@JsonIgnore
	private User userFollower=this;
	
	@ManyToOne
	@JsonIgnore
	private User userFollowing=this;
	
	@OneToMany(mappedBy="userFollower",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<User> followers;
	
	@OneToMany(mappedBy="userFollowing",cascade=CascadeType.REMOVE,orphanRemoval=true)
	private List<User> following;
	
	
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	public User getUserFollower() {
		return userFollower;
	}
	public void setUserFollower(User userFollower) {
		this.userFollower = userFollower;
	}
	public User getUserFollowing() {
		return userFollowing;
	}
	public void setUserFollowing(User userFollowing) {
		this.userFollowing = userFollowing;
	}
	public List<User> getFollowers() {
		return followers;
	}
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	public List<User> getFollowing() {
		return following;
	}
	public void setFollowing(List<User> following) {
		this.following = following;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
