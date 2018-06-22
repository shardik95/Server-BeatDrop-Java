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

import com.example.BeatDropServer.model.Likes;
import com.example.BeatDropServer.model.User;
import com.example.BeatDropServer.repositories.LikeRepository;
import com.example.BeatDropServer.repositories.UserRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000",allowCredentials="true",allowedHeaders="*")
public class LikesService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LikeRepository likesRepository;
	
	@PostMapping("/api/profile/like")
	public Likes addLike(@RequestBody Likes like,HttpSession session) {
		User data = (User) session.getAttribute("currentUser");
		Iterable<User> itr = userRepository.findUserByUserName(data.getUserName());
		Iterator<User> it = itr.iterator();
		User user = it.next();
		Likes likeObj = new Likes();
		likeObj.setDate(like.getDate());
		likeObj.setTitle(like.getTitle());
		likeObj.setType(like.getType());
		likeObj.setTypeId(like.getTypeId());
		likeObj.setImgUrl(like.getImgUrl());
		likeObj.setName(like.getName());
		likeObj.setUser(user);
		return likesRepository.save(likeObj);
	}
	
	@DeleteMapping("/api/profile/like/{likeId}")
	public int unLikeSong(@PathVariable("likeId")int likeId) {
		likesRepository.deleteById(likeId);
		return likeId;
	}
}
