package vn.uts.sendgmail;

public class Main {

	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 587;
	private static final String USERNAME = "username@gmail.com";
	private static final String PASSWORD = "password";

	public static void main(String[] args) {

		GmailSender gmail = new GmailSender(HOST, PORT, USERNAME, PASSWORD);
		String[] to = { "hungle.info@gmail.com", "lmhung2007@gmail.com" };
		if (gmail.send(USERNAME, to, "Test GmailSender", "Hello Bonjour"))
			System.out.println("Success");
		else
			System.out.println("Fail");
	}
}
