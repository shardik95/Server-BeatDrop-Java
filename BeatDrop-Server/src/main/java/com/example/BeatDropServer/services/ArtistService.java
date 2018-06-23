package com.example.BeatDropServer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Artist;
import com.example.BeatDropServer.repositories.ArtistRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
public class ArtistService {
	
	@Autowired
	private ArtistRepository artistRepository; 

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
	
}
