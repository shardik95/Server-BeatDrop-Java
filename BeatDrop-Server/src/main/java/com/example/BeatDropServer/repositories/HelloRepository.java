package com.example.BeatDropServer.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.BeatDropServer.model.Hello;

public interface HelloRepository
extends CrudRepository<Hello, Integer> {}
