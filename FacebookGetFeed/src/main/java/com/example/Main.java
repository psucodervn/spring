package com.example;

import org.springframework.beans.factory.annotation.Autowired;

import vn.uts.facebookgetfeed.DownloadManager;
import vn.uts.facebookgetfeed.dao.TestMongodbRepository;

public class Main {

	@Autowired
	private static TestMongodbRepository testMongodbRepository;
	static final String CONFIG_FILE = "config.xml";
	static final String[] ACCESS_TOKEN_ARRAY = {
	// "CAACEdEose0cBAOXmCoe8TDQIrfwI8OnZAEdbZCHZB76VsPEmjqrnJHROTfDcKfJNXZAcSgyTuFMkZCPunNT1LDoQGTZBHSbzQO9wuprG4PjfQle4iXmmtZCIhlQoQxL1pljZB6rGhVihcJ5GBAxClFOdJzwPcOIUvoiwGP7ZAOP7AwLB27v2TZCxwZBMuoO1oZCZBAAYZD",
	"CAACEdEose0cBAAkCPeZALP55qqUJgRQFUWxJRDWvwu7fzd1oZBD3RZCgB0ZAkUlbt7pjhcKVM81xWWKMIu6ZBwL1AYSgDnbqydWyTJpTBZBhZCembWyklsHr0gZChGMpCukDLHyqZBkZCFDklWk1zZB2EEgGBt4HYmpRpmHXaFJgflRgP1Rcv46lxV8hlTLJ0t0sytf7ZBva57So2QZDZD" };

	public static void main(String[] args) {

		DownloadManager dm = new DownloadManager(CONFIG_FILE);
		if (!dm.isReady())
			return;
		dm.addAccessToken(ACCESS_TOKEN_ARRAY);
		dm.startAndWait();
		
	}
}
