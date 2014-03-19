package com.example;

import vn.uts.facebookgetfeed.DownloadManager;

public class Main {

	static final String CONFIG_FILE = "config.xml";
	static final String[] ACCESS_TOKEN_ARRAY = {
	// "CAACEdEose0cBAOXmCoe8TDQIrfwI8OnZAEdbZCHZB76VsPEmjqrnJHROTfDcKfJNXZAcSgyTuFMkZCPunNT1LDoQGTZBHSbzQO9wuprG4PjfQle4iXmmtZCIhlQoQxL1pljZB6rGhVihcJ5GBAxClFOdJzwPcOIUvoiwGP7ZAOP7AwLB27v2TZCxwZBMuoO1oZCZBAAYZD",
	"CAACEdEose0cBAG2QV7ii9GthoDIdv9YUvTxp3UQgbArXIv1ZC3KosSxnwwgiZBNVVzz1xZA8nOaZCisC3cKtA1HfFQPzxW1F8RCUBmuBr8p6RZBZCxPFdJM6QepppEc1xFGFJwGme6JiHRQpZBfZB9GsKHySVjXbzkCCY0WNmAdlDTJ8XblveEySaK1pXmLF34XQTdhp7ekgcQZDZD" };

	public static void main(String[] args) {

		DownloadManager dm = new DownloadManager(CONFIG_FILE);
		dm.addAccessToken(ACCESS_TOKEN_ARRAY);
		dm.startAndWait();
	}
}
