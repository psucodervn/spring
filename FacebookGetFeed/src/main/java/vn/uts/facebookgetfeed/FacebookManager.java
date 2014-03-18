package vn.uts.facebookgetfeed;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingWorker;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class FacebookManager {

	private MongoOperations mongoOperation;

	private String accessToken;
	private Facebook facebook;
	private FacebookProfile me;

	public FacebookManager(String accessToken) {
		super();
		setAccessToken(accessToken);
	}

	public FacebookProfile getMe() {
		return me;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		try {
			// attempt to use this accessToken
			Facebook fb = new FacebookTemplate(accessToken);
			FacebookProfile me = fb.userOperations().getUserProfile();
			// if success then assign...
			this.facebook = fb;
			this.me = me;
			this.accessToken = accessToken;
		} catch (Exception e) {
			// else print error
			System.out.println(e.getMessage());
		}
	}

	public void start(CountDownLatch latch) {
		System.out.println("Started!");
	}

	public void stop() {
		System.out.println("Stopped!");
	}
}
