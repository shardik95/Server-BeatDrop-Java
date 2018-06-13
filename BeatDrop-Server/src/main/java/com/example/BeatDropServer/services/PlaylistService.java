package com.example.BeatDropServer.services;

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

import com.example.BeatDropServer.model.Playlist;
import com.example.BeatDropServer.model.Song;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.PlaylistRepository;
import com.example.BeatDropServer.repositories.SongRepository;
import com.example.BeatDropServer.repositories.UserRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
public class PlaylistService {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private SongService songService = new SongService();
	
	@GetMapping("/api/user/{userId}/playlist")
	public List<Playlist> getPlaylistForUser(@PathVariable("userId") int userId){
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			User user=data.get();
			return user.getPlaylists();
		}
		return null;

	}
	
	@PostMapping("/api/user/{userId}/playlist")
	public Playlist addPlaylist(@PathVariable("userId") int userId,@RequestBody Playlist playlist) {
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			User user=data.get();
			playlist.setUser(user);
			return playlistRepository.save(playlist);
		}
		return null;
	}
	
	@DeleteMapping("/api/playlist/{playlistId}")
	public void deletePlaylist(@PathVariable("playlistId") int playlistId) {
		playlistRepository.deleteById(playlistId);
	}
	
	@PostMapping("/api/playlist/{playlistId}/track/{trackId}")
	public Playlist addSongToPlaylist(@PathVariable("playlistId") int playlistId,@PathVariable("trackId") String trackId
			,@RequestBody Song song) {
		Optional<Playlist> data = playlistRepository.findById(playlistId);
		//System.out.println("--"+data);
		if(data.isPresent()) {
			Playlist playlist=data.get();
			//System.out.println("--"+playlist.getPlaylistName());
			Song s=songRepository.save(song);
			//System.out.println("--"+s.getSongName());
			List<Song> songlist= playlist.getSongs();
			//System.out.println("--"+songlist);
			songlist.add(s);
			playlist.setSongs(songlist);
			//System.out.println("--here");
			List<Playlist> play_list=s.getPlaylists();
			play_list.add(playlist);
			s.setPlaylists(play_list);
			songRepository.save(s);
			return playlistRepository.save(playlist);
		}
		return null;
	}
}
