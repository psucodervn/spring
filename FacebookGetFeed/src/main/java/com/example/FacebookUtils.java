package com.example;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class FacebookUtils {

	private String accessToken = "CAACEdEose0cBADR4uaKfI5UpehwX6bd7w8HYGhOglEw2WDgAPtb02ElV3Ltqnsw1Ss4qmzXFih6g5JuYeQ0tZAsKEEZCzsGqihO0eHc9O5HJ1dD02KL2ZCU68P5xhmhTXtoH7K8T32HVSzmoNuwdqviQzjw5KYQdIVZCg9kJbQtZCgUS1DMnFtY9GjmE5hEsZACfje7NZBhsAZDZD";
	private Facebook facebook;

	public void run() {

		facebook = new FacebookTemplate(accessToken);
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"SpringConfig.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx
				.getBean("mongoTemplate");

		FacebookProfile me = facebook.userOperations().getUserProfile();

		System.out.println(me.getAbout());
		System.out.println(me.getBio());
		System.out.println(me.getEmail());

		PagingParameters pp = new PagingParameters(50, 0, 0L, Long.MAX_VALUE);
		List<Post> posts = facebook.feedOperations().getHomeFeed(pp);
		System.out.println(posts.size());
		for (Post p : posts) {
			System.out.println(p.getMessage());
			mongoOperation.save(p);
		}

	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Facebook getFacebook() {
		return facebook;
	}

	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}
}
