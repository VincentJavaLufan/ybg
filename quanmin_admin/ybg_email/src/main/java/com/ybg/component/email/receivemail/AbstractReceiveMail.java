package com.ybg.component.email.receivemail;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public abstract class AbstractReceiveMail implements ReceiveMailInter {
	
	@Override
	public Message[] receiveMail() throws Exception {
		Properties props = getProperties();
		Session session = getSession(props);
		Store store = getStore(session);
		Folder folder = getFolder(store);
		return folder.getMessages();
	}
	
	public abstract Store getStore(Session session) throws Exception;
	
	public abstract Properties getProperties();
	
	public abstract Folder getFolder(Store store) throws Exception;
	
	public abstract Session getSession(Properties pros);
}
