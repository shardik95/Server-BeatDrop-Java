package com.example.BeatDropServer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Playlist;
import com.example.BeatDropServer.model.Song;
import com.example.BeatDropServer.repositories.PlaylistRepository;
import com.example.BeatDropServer.repositories.SongRepository;

//https://beatdropapp.herokuapp.com
//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
@RestController
@CrossOrigin(origins="https://beatdropapp.herokuapp.com",allowCredentials="true",allowedHeaders="*")
public class SongService {

	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@GetMapping("/api/song")
	public List<Song> getAllSongs(){
		return (List<Song>) songRepository.findAll();
	}
	
	@PostMapping("/api/song")
	public Song addSongInPlaylist(@RequestBody Song song) {
		
		//System.out.println(song.getId());
		String spotifyId=song.getSpotifySongId();
		System.out.println(spotifyId);
		Song data= songRepository.findByTrackId(spotifyId);
		if(data!=null) {
			System.out.println(data);
			return data;
		}
		else {
			return songRepository.save(song);
		}
	 /* songRepository.save(song);
	  return song;*/
	}
	
	@GetMapping("/api/playlist/{playlistId}/song")
	public List<Song> getSongsForPlaylist(@PathVariable("playlistId") int playlistId){
		Optional<Playlist> data = playlistRepository.findById(playlistId);
		if(data.isPresent()) {
			Playlist p=data.get();
			return p.getSongs();
		}
		return null;
	}
	
}
