package com.example.BeatDropServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Song;
import com.example.BeatDropServer.repositories.SongRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
public class SongService {

	@Autowired
	private SongRepository songRepository;
	
	@PostMapping("/api/song")
	public Song addSongInPlaylist(@RequestBody Song song) {
		
		Song data= songRepository.findByTrackId(song.getSpotifySongId());
		//System.out.println("123");
		if(data!=null) {
			return data;
		}
		else {
			return songRepository.save(song);
		}
	 /* songRepository.save(song);
	  return song;*/
	}
}
