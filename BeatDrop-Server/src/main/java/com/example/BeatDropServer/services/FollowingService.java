package com.example.BeatDropServer.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Follower;
import com.example.BeatDropServer.model.Following;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.FollowerRepository;
import com.example.BeatDropServer.repositories.FollowingRepository;
import com.example.BeatDropServer.repositories.UserRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
public class FollowingService {
	
	@Autowired
	private UserRepository userRepository;
	
	UserService userService = new UserService();
	
	@Autowired
	private FollowerRepository followerRepository;
	
	@Autowired
	private FollowingRepository followingRepository;
	
	@DeleteMapping("/api/following/{followingId}/follower/{followerId}")
	public int DeleteFollowing(@PathVariable("followingId") int followingId,@PathVariable("followerId") int followerId) {
			followingRepository.deleteById(followingId);
			followerRepository.deleteById(followerId);
			return 1;
	}
	
	@PostMapping("/api/user/following/{userId}")
	public List<Following> addFollowing(@PathVariable("userId") int toFollow,@RequestBody User me){
		Optional<User> data = userRepository.findById(toFollow);
		if(data.isPresent()) {
			User follow = data.get();
			
			Following following = new Following();
			following.setFirstName(follow.getFirstName());
			following.setLastName(follow.getLastName());
			following.setUserName(follow.getUserName());
			following.setMyId(follow.getId());
			following.setUser(me);
			
			followingRepository.save(following);
			
			List<Following> followingList = me.getFollowing();
			followingList.add(followingRepository.findFollowingByUserName(follow.getUserName()).get(0));
			
			me.setFollowing(followingList);
			userRepository.save(me);
			
			Follower follower = new Follower();
			follower.setFirstName(me.getFirstName());
			follower.setLastName(me.getLastName());
			follower.setUserName(me.getUserName());
			follower.setMyid(me.getId());
			follower.setUser(follow);
			followerRepository.save(follower);
			
			List<Follower> followerList = follow.getFollowers();
			followerList.add(followerRepository.findFollowerByUserName(me.getUserName()).get(0));
			
			follow.setFollowers(followerList);
			userRepository.save(follow);
			
			return followingList;
		}
		return null;
	}
}
