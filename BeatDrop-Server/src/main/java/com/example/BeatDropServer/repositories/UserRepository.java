package com.example.BeatDropServer.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.BeatDropServer.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName=:username")
	public Iterable<User> findUserByUserName(@Param("username") String u);
	
	@Query("SELECT u FROM User u WHERE (u.userName=:userName AND u.password=:password) OR (u.email=:userName AND u.password=:password)" )
	public Iterable<User> findByCredentials(@Param("userName") String userName,@Param("password") String password);
	
}
