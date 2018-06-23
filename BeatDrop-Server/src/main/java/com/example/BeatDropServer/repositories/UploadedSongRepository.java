package com.example.BeatDropServer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.BeatDropServer.model.UploadedSong;

public interface UploadedSongRepository extends CrudRepository<UploadedSong, Integer> {

}
