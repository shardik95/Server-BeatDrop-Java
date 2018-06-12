package com.example.BeatDropServer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.BeatDropServer.model.Playlist;

public interface PlaylistRepository extends CrudRepository<Playlist, Integer> {

}
