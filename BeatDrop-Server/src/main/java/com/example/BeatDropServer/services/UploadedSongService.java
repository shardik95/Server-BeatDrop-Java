package com.example.BeatDropServer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Artist;
import com.example.BeatDropServer.model.UploadedSong;
import com.example.BeatDropServer.repositories.ArtistRepository;
import com.example.BeatDropServer.repositories.UploadedSongRepository;

//https://beatdropapp.herokuapp.com
//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
@RestController
@CrossOrigin(origins="https://beatdropapp.herokuapp.com",allowCredentials="true",allowedHeaders="*")
public class UploadedSongService {

	@Autowired
	private UploadedSongRepository uploadedRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	
	public UploadedSong createUploadedSong(@RequestBody String songname,@PathVariable("artistId") int artistId) {
		Optional<Artist> data = artistRepository.findById(artistId);
		if(data.isPresent()) {
			UploadedSong song=new UploadedSong();
			System.out.println(songname+"-"+artistId);
			song.setSongName(songname);
			song.setArtist(data.get());
			return uploadedRepository.save(song);
		}
		return null;
	}
	
	@DeleteMapping("/api/uploadedSong/{songId}")
	public int deleteSong(@PathVariable("songId") int id) {
		uploadedRepository.deleteById(id);
		return 1;
	}
	
}
