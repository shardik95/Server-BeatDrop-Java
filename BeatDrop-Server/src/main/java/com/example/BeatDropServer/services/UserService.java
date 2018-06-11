package com.example.BeatDropServer.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.UserRepository;

@RestController
@CrossOrigin(origins="*")
public class UserService {
		
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/api/user")
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PostMapping("/api/signup")
	public User signUpUser(@RequestBody User user,HttpSession session) {
		List<User> users=(List<User>) userRepository.findUserByUserName(user.getUserName());
		if(users.size()>0) {
			return null;
		}
		else {
			session.setAttribute("currentUser", user);
			return createUser(user);
		}
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");	
		System.out.println(currentUser);
		return currentUser;
	}

	
	@PostMapping("/api/login")
	public User loginUser(@RequestBody User user,HttpSession session) {
		//System.out.println(user.getUserName()+"-"+user.getPassword());
		List<User> users=(List<User>) userRepository.findByCredentials(user.getUserName(), user.getPassword());
		if(users.isEmpty())
		{
			User falseUser =new User();
			falseUser.setUserName("CANNOT FIND");
			return falseUser;
		}
		else {
			session.setAttribute("currentUser", user);
			System.out.println(session.getAttribute("currentUser"));
			return users.get(0);
		}
	}
	
	@PutMapping("/api/user")
	public User updateUser(@RequestBody User newUser) {
		List<User> users=(List<User>) userRepository.findUserByUserName(newUser.getUserName());
		if(users.size()>0) {
			User user=users.get(0);
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setPhone(newUser.getPhone());
			user.setDob(newUser.getDob());
			user.setPassword(newUser.getPassword());
			userRepository.save(user);
			return user;
		}
		return null;
	}
	
	
	
}