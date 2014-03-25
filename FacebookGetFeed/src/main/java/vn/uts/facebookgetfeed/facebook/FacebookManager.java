package vn.uts.facebookgetfeed.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagingParameters;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import vn.uts.facebookgetfeed.domain.Action;
import vn.uts.facebookgetfeed.domain.Post;
import vn.uts.facebookgetfeed.domain.UserLog;
import vn.uts.facebookgetfeed.map.MultivaluedHashMapString;
import vn.uts.facebookgetfeed.object.FacebookPost;
import vn.uts.facebookgetfeed.service.ActionService;
import vn.uts.facebookgetfeed.service.PostService;
import vn.uts.facebookgetfeed.service.UserLogService;

@Component
public class FacebookManager {

	private UserLogService userLogService;
	private PostService postService;
	private ActionService actionService;
	private String accessToken;
	private CountDownLatch latch;

	private UserLog log;
	private MultiValueMap<String, String> queryParams;

	private FacebookOperations facebookOperations;
	private FacebookProfile me;

	private final int LIMIT = 50;
	private final long NOW = System.currentTimeMillis() / 1000;

	public FacebookManager(String accessToken, CountDownLatch latch,
			PostService postService, UserLogService profileLogService, ActionService actionService) {
		super();
		this.postService = postService;
		this.userLogService = profileLogService;
		this.actionService = actionService;
		facebookOperations = new FacebookOperations(accessToken);
		me = facebookOperations.getMe();
		setLatch(latch);
	}

	public FacebookManager() {
	}

	public FacebookProfile getMe() {
		return me;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void start() {
		if (me != null) {
			startGetFeed();
			done();
		}
		latch.countDown();
	}

	private void startGetFeed() {
		System.out.println("Started [profileId=" + me.getId() + "]");
		queryParams = new MultivaluedHashMapString();
		queryParams.add("limit", Integer.toString(LIMIT));
		queryParams.add("fields", FacebookPost.FIELDS);
		log = userLogService.findByProfileId(me.getId());
		if (log == null) {
			log = new UserLog();
			log.setUserId(me.getId());
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
			userLogService.save(log);
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
			userLogService.save(log);
		} while (true);
	}

	private List<FacebookPost> getFeedBetween(long since, long until) {
		PagingParameters pp = new PagingParameters(LIMIT, 0, since, until);
		try {
			queryParams.set("since", Long.toString(since));
			queryParams.set("until", Long.toString(until));
			List<FacebookPost> posts = facebookOperations.getHomeFeed(pp,
					queryParams);
			return posts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ArrayList<FacebookPost>();
		}
	}

	private void processPost(FacebookPost p) {
		if (p.getType().equals("link")) {
			processLink(p);
		} else {
			if (p.getLink() != null) {
				processLink(p);
			}
		}
	}

	private void processLink(FacebookPost p) {
		try {
			Post post = postService.findByPostId(p.getId());
			if (post != null)
				return;

			post = new Post();
			post.setPostId(p.getId());
			post.setFromId(p.getFrom().getId());
			post.setMessage(p.getMessage());
			post.setCaption(p.getCaption());
			post.setCreatedTime(p.getCreated_time());
			post.setLink(p.getLink());
			post.setType(p.getType());
			postService.save(post);
			
			Action action = facebookOperations.getAction(post.getPostId());
			action.setPostObjectId(post.getId());
			actionService.save(action);
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
