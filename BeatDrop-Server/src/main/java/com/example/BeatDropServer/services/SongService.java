package com.example.BeatDropServer.services;

import java.util.Iterator;

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
		Iterable<Song> data= songRepository.findByTrackId(song.getSpotifySongId());
		Iterator<Song> itr=data.iterator();
		if(itr.hasNext()) {
			return itr.next();
		}
		else {
			return songRepository.save(song);
		}
	}
}
