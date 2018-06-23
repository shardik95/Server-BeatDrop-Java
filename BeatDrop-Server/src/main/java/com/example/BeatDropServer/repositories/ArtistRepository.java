package com.example.BeatDropServer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.BeatDropServer.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {

	@Query("SELECT u FROM Artist u WHERE u.userName=:username")
	public Iterable<Artist> findArtistByUserName(@Param("username") String u);
}
