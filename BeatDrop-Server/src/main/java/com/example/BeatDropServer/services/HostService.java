package com.example.BeatDropServer.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Host;
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
	
}
