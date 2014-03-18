package vn.uts.facebookgetfeed;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class DownloadManager {

	private String configFileName;
	private GenericXmlApplicationContext context;
	private MongoOperations mongoOperation;
	private List<String> listAccessTokens;
	private boolean isReady;

	private int maxThreads;

	private final String MONGO_TEMPLATE = "mongoTemplate";
	private final String MAX_THREADS = "maxThreads";

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
		System.out.println(DatabaseManager.foo());
	}

	private boolean readConfig() {
		try {
			context = new GenericXmlApplicationContext(configFileName);
			mongoOperation = (MongoOperations) context.getBean(MONGO_TEMPLATE);
			maxThreads = (Integer) context.getBean(MAX_THREADS);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public int getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}

	public void addAccessToken(String accessToken) {
		if (!listAccessTokens.contains(accessToken)) {
			listAccessTokens.add(accessToken);
		}
	}

	public void start() {

	}
}
