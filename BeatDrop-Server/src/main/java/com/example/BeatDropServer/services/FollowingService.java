package com.example.BeatDropServer.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Follower;
import com.example.BeatDropServer.model.Following;
import com.example.BeatDropServer.model.Likes;
import com.example.BeatDropServer.model.Review;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.FollowerRepository;
import com.example.BeatDropServer.repositories.FollowingRepository;
import com.example.BeatDropServer.repositories.LikeRepository;
import com.example.BeatDropServer.repositories.ReviewRepository;
import com.example.BeatDropServer.repositories.UserRepository;

//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
@RestController
@CrossOrigin(origins="https://beatdropapp.herokuapp.com",allowCredentials="true",allowedHeaders="*")
public class FollowingService {
	
	@Autowired
	private UserRepository userRepository;
	
	UserService userService = new UserService();
	
	@Autowired
	private FollowerRepository followerRepository;
	
	@Autowired
	private FollowingRepository followingRepository;
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@DeleteMapping("/api/following/{followingId}/follower/{followerId}")
	public int DeleteFollowing(@PathVariable("followingId") int followingId,@PathVariable("followerId") int followerId) {
			followingRepository.deleteById(followingId);
			followerRepository.deleteById(followerId);
			return 1;
	}
	
	@GetMapping("/api/getFollowing/{userName}/{followingName}")
	public Following getFollowingRecord(@PathVariable("userName") String userName,@PathVariable("followingName") String followingName) {
		return followingRepository.findFollowingRecord(userName, followingName).get(0);
	}
	
	@PostMapping("/api/user/following/{userId}")
	public List<Following> addFollowing(@PathVariable("userId") int toFollow,@RequestBody User me){

		Optional<User> data = (Optional<User>)userRepository.findById(toFollow);
		if(data.isPresent()) {
			User follow = data.get();
			
			Following following = new Following();
			following.setFirstName(follow.getFirstName());
			following.setLastName(follow.getLastName());
			following.setUserName(follow.getUserName());
			following.setMyId(follow.getId());
			following.setType(follow.getType());
			following.setUser(me);
			following.setFollowingName(me.getUserName());
			
			followingRepository.save(following);
			
			List<Following> followingList = me.getFollowing();
			followingList.add(followingRepository.findFollowingByUserName(follow.getUserName()).get(0));
			
			
			me.setFollowing(followingList);
			me.setLikes(me.getLikes());
			me.setReviews(me.getReviews());
			User x = userRepository.save(me);
			
			List<Likes> likes=me.getLikes();
			Iterator<Likes> itr = likes.iterator();
			while(itr.hasNext()) {
				Likes like = itr.next();
				like.setUser(x);
				likeRepository.save(like);
			}
			
			List<Review> reviews = me.getReviews();
			Iterator<Review> itr1 = reviews.iterator();
			while(itr1.hasNext()) {
				Review review = itr1.next();
				review.setUser(x);
				reviewRepository.save(review);
			}
			
			
			Follower follower = new Follower();
			follower.setFirstName(me.getFirstName());
			follower.setLastName(me.getLastName());
			follower.setUserName(me.getUserName());
			follower.setMyid(me.getId());
			follower.setUser(follow);
			follower.setType(me.getType());
			follower.setFollowingName(follow.getUserName());
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
