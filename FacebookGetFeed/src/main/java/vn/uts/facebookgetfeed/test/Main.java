package vn.uts.facebookgetfeed.test;

import org.springframework.context.support.GenericXmlApplicationContext;

import vn.uts.facebookgetfeed.DownloadManager;

public class Main {

	static final String CONFIG_FILE = "config.xml";
	static final String[] ACCESS_TOKEN_ARRAY = {
			"CAACEdEose0cBACjX2ZBQSqGED74RBzLowGqANsNGvjncnYKC2gTZCb7qtFRbQSAPmeJJm2Vnz46F1nDr9I0ibHJwgQFPQ4MVx2rEI4ZB8vrJYgmfK5a9K4n5vphcZBWkpIii3m7NdYLvPp0vVmt42hXI4kT3sSkpF9JU3brNkoWVTsbz7IHhdljaXigJExZAlhSSR9BDWfAZDZD",
			"CAACEdEose0cBACHPmejQdnF5EQIJujcpcLSJUylvbfg6GUXJmPTtMhMTasa2PPW84Du4SZCp4xJzHv1jUwSTu4P2R4aZBTW3amiE4dVhBOZBp8X90yJ0TFHwW2wP2j2QX3BgUuKmA1yBbC3WhK01rnx0EsHl18YipuTA9I612w71BZB706XCiHgFgTWYhQm8XmJ5ptnoKQZDZD" };
	private static GenericXmlApplicationContext context;

	public static void main(String[] args) {

		context = new GenericXmlApplicationContext(CONFIG_FILE);

		DownloadManager downloadManager = (DownloadManager) context
				.getBean("downloadManager");
		if (!downloadManager.isReady())
			return;
		downloadManager.addAccessToken(ACCESS_TOKEN_ARRAY);
		downloadManager.startAndWait();

	}
}
