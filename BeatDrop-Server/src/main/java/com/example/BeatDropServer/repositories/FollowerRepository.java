package com.example.BeatDropServer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.BeatDropServer.model.Follower;

public interface FollowerRepository extends CrudRepository<Follower,Integer>{

	@Query("SELECT u FROM Follower u WHERE u.userName=:username")
	public List<Follower> findFollowerByUserName(@Param("username") String u);
	
//	@Query("DELETE FROM Follower u WHERE u.userId=:userId AND u.myid=:myid")
//	public void DeleteFollower(@Param("userId") int userId,@Param("myid") int myid);
}
