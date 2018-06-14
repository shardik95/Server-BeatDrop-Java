package com.example.BeatDropServer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.BeatDropServer.model.Song;

public interface SongRepository extends CrudRepository<Song, Integer>{
	
	@Query("SELECT s FROM Song s WHERE s.spotifySongId=:spotifySongId")
	Song findByTrackId(@Param("spotifySongId") String s);
	
}
