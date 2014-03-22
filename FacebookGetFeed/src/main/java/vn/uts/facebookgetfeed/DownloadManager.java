package vn.uts.facebookgetfeed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.uts.facebookgetfeed.facebook.FacebookManager;
import vn.uts.facebookgetfeed.service.PostService;
import vn.uts.facebookgetfeed.service.ProfileLogService;

@Component
public class DownloadManager {

	@Autowired
	private ProfileLogService profileLogService;
	@Autowired
	private PostService postService;

	private List<String> listAccessTokens;
	private boolean isReady;

	private int maxThread;

	public DownloadManager() {
		super();
		isReady = readConfig();
		if (isReady)
			init();
	}

	private void init() {
		listAccessTokens = new ArrayList<String>();
	}

	private boolean readConfig() {
		try {
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public int getMaxThread() {
		return maxThread;
	}

	public void setMaxThread(int maxThread) {
		this.maxThread = maxThread;
	}

	public void addAccessToken(String accessToken) {
		if (!listAccessTokens.contains(accessToken)) {
			listAccessTokens.add(accessToken);
		}
	}

	public void addAccessToken(String[] accessTokens) {
		if (!isReady)
			return;
		for (String accessToken : accessTokens) {
			addAccessToken(accessToken);
		}
	}

	public void startAndWait() {
		if (!isReady)
			return;

		final CountDownLatch latch = new CountDownLatch(listAccessTokens.size());
		ExecutorService es = Executors.newFixedThreadPool(maxThread);

		for (String accessToken : listAccessTokens) {
			final FacebookManager facebookManager = new FacebookManager(
					accessToken, latch, postService, profileLogService);
			es.submit(new Runnable() {
				@Override
				public void run() {
					facebookManager.start();
				}
			});
		}

		try {
			latch.await();
			System.out.println("Wait done!");
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
		}
		es.shutdown();
	}

	public boolean isReady() {
		return isReady;
	}
}
