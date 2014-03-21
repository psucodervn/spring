package vn.uts.facebookgetfeed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Component;

import vn.uts.facebookgetfeed.domain.ProfileLog;
import vn.uts.facebookgetfeed.service.PostService;
import vn.uts.facebookgetfeed.service.ProfileLogService;

@Component
public class FacebookManager {

	private ProfileLogService profileLogService;
	private PostService postService;
	private String accessToken;
	private CountDownLatch latch;

	private Facebook facebook;
	private FacebookProfile me;
	private ProfileLog log;

	private final int LIMIT = 50;
	private final long NOW = System.currentTimeMillis() / 1000;

	public FacebookManager(String accessToken, CountDownLatch latch,
			PostService postService, ProfileLogService profileLogService) {
		super();
		this.postService = postService;
		this.profileLogService = profileLogService;
		setAccessToken(accessToken);
		setLatch(latch);
	}

	public FacebookManager() {
		super();
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
		log = profileLogService.findByProfileId(me.getId());
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
			profileLogService.save(log);
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
			profileLogService.save(log);
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
		try {
			vn.uts.facebookgetfeed.domain.Post post = new vn.uts.facebookgetfeed.domain.Post();
			post.setPostId(p.getId());
			post.setProfileId(me.getId());
			post.setFromId(p.getFrom().getId());
			post.setFromName(p.getFrom().getName());
			post.setMessage(p.getMessage());
			post.setCreatedTime(p.getCreatedTime());
			post.setUpdatedTime(p.getUpdatedTime());
			post.setLink(p.getLink());
			post.setType(p.getType().name());
			if (postService.findByPostId(post.getPostId()) == null)
				postService.save(post);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void done() {
		System.out.println("Done [profileId=" + me.getId() + "]");
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}
}
