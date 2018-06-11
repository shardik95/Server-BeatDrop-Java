package com.example.BeatDropServer.services;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RestController
@CrossOrigin(origins="*")
public class SpotifyAccountService {
	
	@GetMapping("/api/accessToken")
	public String getAccessToken() throws IOException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
		Request request = new Request.Builder()
		  .url("https://accounts.spotify.com/api/token")
		  .post(body)
		  .addHeader("Content-Type", "application/x-www-form-urlencoded")
		  .addHeader("Authorization", "Basic MWFkYTcxMmFkODlkNDQ4Mjg2ZjhkODkyODY4NjIxZDU6MTg3NDE0MmVhZTQ5NDhiZWI3YTRjMzVkYmIyMjZkYzU=")
		  .addHeader("Cache-Control", "no-cache")
		  .build();

		Response response = client.newCall(request).execute();
		return response.body().string();
	}
}

