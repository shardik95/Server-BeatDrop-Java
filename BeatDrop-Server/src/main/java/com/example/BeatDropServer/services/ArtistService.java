package com.example.BeatDropServer.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Artist;
import com.example.BeatDropServer.model.Follower;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.ArtistRepository;
import com.example.BeatDropServer.repositories.UserRepository;

//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")

@RestController
@CrossOrigin(origins="https://beatdropapp.herokuapp.com",allowCredentials="true",allowedHeaders="*")
public class ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository; 
	
	@Autowired
	private FollowingService followingService =new FollowingService();
	
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/api/artist")
	public Artist createArtist(@RequestBody Artist artist) {
		return artistRepository.save(artist);
	}
	
	@GetMapping("/api/artist/{artistId}")
	public Artist getArtist(@PathVariable("artistId") int artistId) {
		Optional<Artist> artist = artistRepository.findById(artistId);
		if(artist.isPresent()){
			return artist.get();
		}
		return null;
	}
	
	@PostMapping("/api/verify")
	public Artist verifyArtist(@RequestBody User user) {
		Artist artist =new Artist();
		artist.setFirstName(user.getFirstName());
		artist.setLastName(user.getLastName());
		artist.setPhone(user.getPhone());
		artist.setDob(user.getDob());
		artist.setEmail(user.getEmail());
		artist.setPassword(user.getPassword());
		artist.setUserName(user.getUserName());
		artist.setType("Artist");
		artist = artistRepository.save(artist);
		
		
		List<Follower> followers = user.getFollowers();
		Iterator<Follower> itr = followers.iterator();
		while(itr.hasNext()) {
			Follower f = itr.next();
			Optional<User> data = userRepository.findById(f.getMyid());
			followingService.addFollowing(artist.getId(),data.get());
			followingService.DeleteFollowing(f.getId(),f.getId());
		}
		

		artist.setType("Artist");
		
		userRepository.deleteById(user.getId());
		return artistRepository.save(artist);
		
	}
	
	@GetMapping("/api/artist")
	public List<Artist> getAllArtist(){
		return (List<Artist>) artistRepository.findAll();
	}
	
	@DeleteMapping("/api/artist/{artistId}")
	public int deleteArtist(@PathVariable("artistId") int id) {
		artistRepository.deleteById(id);
		return 1;
	}
}
