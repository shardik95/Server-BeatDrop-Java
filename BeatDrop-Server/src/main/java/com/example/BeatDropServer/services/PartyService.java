package com.example.BeatDropServer.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Host;
import com.example.BeatDropServer.model.HostPlaylist;
import com.example.BeatDropServer.model.Party;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.HostRepository;
import com.example.BeatDropServer.repositories.PartyRepository;

//https://beatdropapp.herokuapp.com
//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
@RestController
@CrossOrigin(origins="https://beatdropapp.herokuapp.com",allowCredentials="true",allowedHeaders="*")
public class PartyService {
		
	@Autowired
	private PartyRepository partyRepository; 
	
	@Autowired
	private HostRepository hostRepository;
	
	@PostMapping("/api/party")
	public Party createParty(@RequestBody Party party,HttpSession session) {
		User host =  (User)session.getAttribute("currentUser");
		List<Host> users=(List<Host>) hostRepository.findHostByUserName(host.getUserName());
		if(users.size()>0) {
			Host currenthost = users.get(0);
			party.setHost(currenthost);
			return partyRepository.save(party);
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}/party")
	public List<Party> getPlaylistForHost(@PathVariable("userId") int userId){
		Optional<Host> data = hostRepository.findById(userId);
		if(data.isPresent()) {
			Host user=data.get();
			return user.getParties();
		}
		return null;
	}
	
	@DeleteMapping("/api/party/{partyId}")
	public int deleteParty(@PathVariable("partyId") int partyId) {
		partyRepository.deleteById(partyId);
		return 1;
	}
	
	@GetMapping("/api/party/{partyId}/playlist")
	public List<HostPlaylist> getPlaylistForParty(@PathVariable("partyId") int partyId){
		Optional<Party> data=partyRepository.findById(partyId);
		if(data.isPresent()) {
			Party p = data.get();
			return p.getPlaylists();
		}
		return null;
	}
}
