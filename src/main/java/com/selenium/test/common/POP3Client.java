package com.selenium.test.common;

import javax.mail.*;
import java.util.Properties;

public class POP3Client {
	
	private Store store;

	public POP3Client(String POP_AUTH_USER, String POP_AUTH_PWD) throws NoSuchProviderException, MessagingException {
		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties pop3Props = new Properties();
		pop3Props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
		pop3Props.setProperty("mail.pop3.socketFactory.fallback", "false");
		pop3Props.setProperty("mail.pop3.port", "995");
		pop3Props.setProperty("mail.pop3.socketFactory.port", "995");
		URLName url = new URLName("pop3", "pop.gmail.com", 955, "", POP_AUTH_USER, POP_AUTH_PWD);
		Session session = Session.getInstance(pop3Props, null);
		store = session.getStore(url);
		store.connect();
	}
	
	public Folder openFolder(String folder_name, int access_mode) throws MessagingException{
		Folder folder = store.getFolder(folder_name);
		folder.open(access_mode);
		return folder;
	}
	
	public void closeStore(){
		try {
			store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new AssertionError("Закрытие соединения");
		}
	}
}
