package vn.uts.facebookgetfeed.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import vn.uts.facebookgetfeed.domain.ProfileLog;
import vn.uts.facebookgetfeed.map.MultivaluedHashMapString;
import vn.uts.facebookgetfeed.object.FacebookPost;
import vn.uts.facebookgetfeed.service.PostService;
import vn.uts.facebookgetfeed.service.ProfileLogService;

@Component
public class FacebookManager {

	private ProfileLogService profileLogService;
	private PostService postService;
	private String accessToken;
	private CountDownLatch latch;

	private FacebookProfile me;
	private ProfileLog log;
	private FacebookOperations facebookOperations;
	private MultiValueMap<String, String> queryParams;

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
		facebookOperations = new FacebookOperations(accessToken);
		me = facebookOperations.getMe();
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
		queryParams = new MultivaluedHashMapString();
		queryParams.add("limit", Integer.toString(LIMIT));
		queryParams.add("fields", FacebookPost.FIELDS);
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
			List<FacebookPost> posts = getFeedBetween(since, until);
			for (FacebookPost p : posts) {
				processPost(p);
				since = Math.max(since,
						Converter.dateToTimeStamp(p.getCreated_time()));
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
			List<FacebookPost> posts = getFeedBetween(0, until);
			if (posts.isEmpty())
				break;
			for (FacebookPost p : posts) {
				processPost(p);
				until = Math.min(until,
						Converter.dateToTimeStamp(p.getCreated_time()));
			}
			log.setLastSince(until);
			profileLogService.save(log);
		} while (true);
	}

	private List<FacebookPost> getFeedBetween(long since, long until) {
		PagingParameters pp = new PagingParameters(LIMIT, 0, since, until);
		try {
			List<FacebookPost> posts = facebookOperations.getHomeFeed(pp,
					queryParams);
			return posts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<FacebookPost>();
		}
	}

	private void processPost(FacebookPost p) {
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

	private void processLink(FacebookPost p) {
		try {
			vn.uts.facebookgetfeed.domain.Post post = new vn.uts.facebookgetfeed.domain.Post();
			post.setPostId(p.getId());
			post.setProfileId(me.getId());
//			post.setFromId(p.getFrom().getId());
//			post.setFromName(p.getFrom().getName());
			post.setMessage(p.getMessage());
			post.setCreatedTime(p.getCreated_time());
//			post.setUpdatedTime(p.getUpdatedTime());
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
