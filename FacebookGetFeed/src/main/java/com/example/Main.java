package com.example;

import vn.uts.facebookgetfeed.DownloadManager;

public class Main {

	static final String ACCESS_TOKEN = "CAACEdEose0cBAFdtSLNT9DoIVOCPiLXg24rsQvB6o1FsNdqToW4ZBO0gzIMSCI4QTiVJjNWFH8UCZBWqS9OQJMDU6FtE1bo6vb6A8zFVteF6Ha94F6OxEOZANnsx7WGFgQyjaSY9OssZAL7Vmho7l4UsSQk7xrH6qdq9ZBLkcBzm3SyQ37xKRSZCZBGIWgbWcYQjoZBzk4qZANgZDZD";
	static final String CONFIG_FILE = "config.xml";

	public static void main(String[] args) throws InterruptedException {

		DownloadManager dm = new DownloadManager(CONFIG_FILE);
		dm.addAccessToken(ACCESS_TOKEN);
		dm.start();
	}

}
