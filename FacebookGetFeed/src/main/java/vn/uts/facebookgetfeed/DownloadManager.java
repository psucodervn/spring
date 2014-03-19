package vn.uts.facebookgetfeed;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class DownloadManager {

	private String configFileName;
	private GenericXmlApplicationContext context;
	private MongoOperations mongoOperation;
	private List<String> listAccessTokens;
	private boolean isReady;

	private int maxThread;

	private final String MONGO_TEMPLATE = "mongoTemplate";
	private final String MAX_THREAD = "maxThread";

	public DownloadManager(String configFileName) {
		super();
		this.configFileName = configFileName;
		isReady = readConfig();
		if (isReady)
			init();
	}

	private void init() {
		listAccessTokens = new ArrayList<String>();
		DatabaseManager.setMongoOperation(mongoOperation);
	}

	private boolean readConfig() {
		try {
			context = new GenericXmlApplicationContext(configFileName);
			mongoOperation = (MongoOperations) context.getBean(MONGO_TEMPLATE);
			maxThread = (Integer) context.getBean(MAX_THREAD);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public int getMaxThreads() {
		return maxThread;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThread = maxThreads;
	}

	public MongoOperations getMongoOperation() {
		return mongoOperation;
	}

	public void addAccessToken(String accessToken) {
		if (!listAccessTokens.contains(accessToken)) {
			listAccessTokens.add(accessToken);
		}
	}

	public void addAccessToken(String[] accessTokens) {
		for (String accessToken : accessTokens) {
			addAccessToken(accessToken);
		}
	}

	public void startAndWait() {
		final CountDownLatch latch = new CountDownLatch(listAccessTokens.size());
		ExecutorService es = Executors.newFixedThreadPool(maxThread);

		for (String accessToken : listAccessTokens) {
			final FacebookManager fm = new FacebookManager(accessToken, latch);
			es.submit(new Runnable() {				
				@Override
				public void run() {
					fm.start();
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
}
