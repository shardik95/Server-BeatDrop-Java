package com.example.BeatDropServer.services;

import java.util.List;

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
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.HostRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
public class HostService {
	
	@Autowired
	private HostRepository hostRepository;
	
	@PostMapping("/api/host")
	public Host createHost(@RequestBody Host user,HttpSession session) {
		List<Host> users=(List<Host>) hostRepository.findHostByUserName(user.getUserName());
		if(users.size()>0) {
			return null;
		}
		else {
			session.setAttribute("currentUser", user);
			return createUser(user);
		}
	}
	
	public Host createUser(@RequestBody Host user) {
		return hostRepository.save(user);
	}
	
	@GetMapping("/api/host")
	public List<Host> getAllHosts(){
		return (List<Host>) hostRepository.findAll();
	}
	
	@DeleteMapping("/api/host/{hostId}")
	public int deleteHost(@PathVariable("hostId") int hostId) {
		hostRepository.deleteById(hostId);
		return 1;
	}
	
	@PutMapping("/api/host")
	public User updateUser(@RequestBody Host newUser) {
		List<Host> users=(List<Host>) hostRepository.findHostByUserName(newUser.getUserName());
		if(users.size()>0) {
			Host user=users.get(0);
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setPhone(newUser.getPhone());
			user.setDob(newUser.getDob());
			user.setPassword(newUser.getPassword());
			hostRepository.save(user);
			return user;
		}
		return null;
	}
	
}
