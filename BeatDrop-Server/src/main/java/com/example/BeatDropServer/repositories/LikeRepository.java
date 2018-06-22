package com.example.BeatDropServer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.BeatDropServer.model.Likes;

public interface LikeRepository extends CrudRepository<Likes, Integer>{

}
