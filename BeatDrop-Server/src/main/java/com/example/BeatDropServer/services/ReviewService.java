package com.example.BeatDropServer.services;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BeatDropServer.model.Review;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.ReviewRepository;
import com.example.BeatDropServer.repositories.UserRepository;

//https://beatdropapp.herokuapp.com
//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
@RestController
@CrossOrigin(origins="https://beatdropapp.herokuapp.com",allowCredentials="true",allowedHeaders="*")
public class ReviewService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@PostMapping("/api/profile/review")
	public Review addReview(@RequestBody Review review,HttpSession session) {
		User data = (User) session.getAttribute("currentUser");
		Iterable<User> itr = userRepository.findUserByUserName(data.getUserName());
		Iterator<User> it = itr.iterator();
		User user = it.next();
		Review rev = new Review();
		rev.setDate(review.getDate());
		rev.setTitle(review.getTitle());
		rev.setType(review.getType());
		rev.setTypeId(review.getTypeId());
		rev.setStars(review.getStars());
		rev.setName(review.getName());
		rev.setImgUrl(review.getImgUrl());
		rev.setUser(user);
		return reviewRepository.save(rev);
	}
	
	@DeleteMapping("/api/profile/review/{reviewId}")
	public int unLikeReview(@PathVariable("reviewId")int reviewId) {
		reviewRepository.deleteById(reviewId);
		return reviewId;
	}
	
}
