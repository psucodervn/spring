package vn.uts.facebookgetfeed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

import vn.uts.facebookgetfeed.domain.ProfileLog;

public class FacebookManager {

	private String accessToken;
	private Facebook facebook;
	private FacebookProfile me;
	private DatabaseManager dbm;
	private CountDownLatch latch;
	private ProfileLog log;

	private final int LIMIT = 50;
	private final long NOW = System.currentTimeMillis() / 1000;

	public FacebookManager(String accessToken, CountDownLatch latch) {
		super();
		setAccessToken(accessToken);
		this.latch = latch;
		dbm = new DatabaseManager();
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

	public void start() {
		if (me != null) {
			doWork();
			done();
		}
		latch.countDown();
	}

	private void doWork() {
		System.out.println("Started [profileId=" + me.getId() + "]");
		log = dbm.findProfileLogByProfileId(me.getId());
		if (log == null) {
			log = new ProfileLog();
			log.setProfileId(me.getId());
			log.setLastSince(NOW);
			log.setLastUntil(NOW);
			getFeedBefore();
		} else {
			getFeedAfter();
			getFeedBefore();
		}
	}

	private void getFeedAfter() {
		long since, until;
		do {
			since = log.getLastUntil();
			until = since + 3600;
			List<Post> posts = getFeedBetween(since, until);
			for (Post p : posts) {
				processPost(p);
				since = Math.max(since,
						Converter.dateToTimeStamp(p.getCreatedTime()));
			}
			log.setLastUntil(since);
			dbm.saveProfileLog(log);
			if (posts.isEmpty() && until >= NOW)
				break;
		} while (true);
	}

	private void getFeedBefore() {
		long until;
		do {
			until = log.getLastSince();
			List<Post> posts = getFeedBetween(0, until);
			if (posts.isEmpty())
				break;
			for (Post p : posts) {
				processPost(p);
				until = Math.min(until,
						Converter.dateToTimeStamp(p.getCreatedTime()));
			}
			log.setLastSince(until);
			dbm.saveProfileLog(log);
		} while (true);
	}

	private List<Post> getFeedBetween(long since, long until) {
		PagingParameters pp = new PagingParameters(LIMIT, 0, since, until);
		try {
			List<Post> posts = facebook.feedOperations().getHomeFeed(pp);
			return posts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<Post>();
		}
	}

	private void processPost(Post p) {
		switch (p.getType()) {
		case LINK:
			processLink(p);
			break;
		default:
			if (p.getLink() != null)
				processLink(p);
			break;
		}
	}

	private void processLink(Post p) {
		vn.uts.facebookgetfeed.domain.Post post = new vn.uts.facebookgetfeed.domain.Post();
		post.setPostId(p.getId());
		post.setFromId(p.getFrom().getId());
		post.setFromName(p.getFrom().getName());
		post.setMessage(p.getMessage());
		post.setCreatedTime(p.getCreatedTime());
		post.setUpdatedTime(p.getUpdatedTime());
		post.setLink(p.getLink());
		if (!dbm.existPostId(post.getPostId()))
			dbm.savePost(post);
	}

	private void done() {
		System.out.println("Done [profileId=" + me.getId() + "]");
	}
}
