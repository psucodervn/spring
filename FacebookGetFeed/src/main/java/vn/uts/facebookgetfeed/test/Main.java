package vn.uts.facebookgetfeed.test;

import org.springframework.context.support.GenericXmlApplicationContext;

import vn.uts.facebookgetfeed.DownloadManager;

public class Main {

	static final String CONFIG_FILE = "config.xml";
	static final String[] ACCESS_TOKEN_ARRAY = {
			"CAACEdEose0cBAPxt7eAZAtHjm67iUvjMCiVxOFGJ5qleBqsZAnzv8j0P7hl8ZACwS6I1ZAVZA0BAPzqjxoAsLP1nZAG7dJkUaPxq1XevdnyIExzrwEXDoW6Fk4gLmZBVmNtHmF8kg87hQVLZAkgd7bJ76Dm7U82rc1mJ6bFMFfTJzAyYbEpsqOpdZCz9xmBVBgRtxBERWlK41JgZDZD",
			"CAACEdEose0cBAOMXhZCnJu44CLi0pFYxeIU511POvl5gXkpPh11lvSYiQZA2zHc9Xp116EN3iEO2EpnPsjTR0ycwTLfeZAKZCMAZAvTup7hTndHaaSorC10lAmtaXZAB977YPgeaRx4ZBUXZB07IKqF7krK9ubhlnkkraLZAPBWhYcDI1qGGRq2LogNuJHdPySXLoO1pVZAJzBBAZDZD" };
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
