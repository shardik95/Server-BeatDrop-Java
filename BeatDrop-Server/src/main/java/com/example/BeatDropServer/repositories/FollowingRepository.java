package com.example.BeatDropServer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.BeatDropServer.model.Following;

public interface FollowingRepository extends CrudRepository <Following,Integer> {

	@Query("SELECT f FROM Following f WHERE f.userName=:username")
	public List<Following> findFollowingByUserName(@Param("username") String u);
	
	@Query("SELECT f FROM Following f WHERE f.userName=:username AND f.followingName=:followingName")
	public List<Following> findFollowingRecord(@Param("username") String u,@Param("followingName") String fn);
	
//	@Query("DELETE FROM Following u WHERE u.userId=:userId AND u.myId=:myid")
//	public void DeleteFollowing(@Param("userId") int userId,@Param("myid") int myid);
	
	
	
}
