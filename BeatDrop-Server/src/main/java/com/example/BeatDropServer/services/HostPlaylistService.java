package com.example.BeatDropServer.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.HostPlaylist;
import com.example.BeatDropServer.model.HostSong;
import com.example.BeatDropServer.model.Party;
import com.example.BeatDropServer.model.Playlist;
import com.example.BeatDropServer.model.Song;
import com.example.BeatDropServer.repositories.HostPlaylistRepository;
import com.example.BeatDropServer.repositories.HostSongRepository;
import com.example.BeatDropServer.repositories.PartyRepository;
import com.example.BeatDropServer.repositories.PlaylistRepository;

//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
@RestController
@CrossOrigin(origins="https://beatdropapp.herokuapp.com",allowCredentials="true",allowedHeaders="*")
public class HostPlaylistService {
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private HostPlaylistRepository hostPlaylistRepository;
	
	@Autowired
	private HostSongRepository hostSongRepository;
	
	@Autowired
	private PartyRepository partyRepository;
		
	@PostMapping("/api/party/{partyId}/playlist/{playlistId}")
	public HostPlaylist addPlaylistToParty(@PathVariable("partyId") int partyId,@PathVariable("playlistId") int playlistId) {
		Optional<Playlist> data = playlistRepository.findById(playlistId);
		if(data.isPresent()) {
			Playlist p = data.get();
			
			HostPlaylist hostplaylist = new HostPlaylist();
			hostplaylist.setPlaylistName(p.getPlaylistName());
			HostPlaylist hp = hostPlaylistRepository.save(hostplaylist);
			
			List<Song> playlistSong = p.getSongs();
			Iterator<Song> itr = playlistSong.iterator();
			while(itr.hasNext()) {
				Song old = itr.next();
				HostSong song = new HostSong();
				song.setSongName(old.getSongName());
				song.setSpotifySongId(old.getSpotifySongId());
				song.setImgUrl(old.getImgUrl());
				song.setDuration(old.getDuration());
				song.setPlaylist(hp);
				HostSong savedSong = hostSongRepository.save(song);
				
				List<HostSong> hs = hp.getSongs();
				hs.add(savedSong);
				hp.setSongs(hs);
			}
			
			Optional<Party> pdata = partyRepository.findById(partyId);
			if(pdata.isPresent()) {
				Party party = pdata.get();
				hp.setParty(party);
				return hostPlaylistRepository.save(hp);
			}
			return null;
			
		}
		return null;
	}
	
	@DeleteMapping("/api/hostplaylist/{playlistId}")
	public int deletePlaylist(@PathVariable("playlistId") int id) {
		hostPlaylistRepository.deleteById(id);
		return 1;
	}
	
}
