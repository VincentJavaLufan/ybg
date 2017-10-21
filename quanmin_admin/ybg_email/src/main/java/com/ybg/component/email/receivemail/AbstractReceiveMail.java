package com.ybg.component.email.receivemail;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public abstract class AbstractReceiveMail implements ReceiveMailInter {
	
	@Override
	public Message[] receiveMail() throws Exception {
		Properties props = getProperties();
		Session session = getSession(props);
		Store store = getStore(session);
		Folder folder = getFolder(store);
		return folder.getMessages();
	}
	
	/** 交给子类去实现
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public abstract Store getStore(Session session) throws Exception;
	
	/** 交给子类去实现
	 * 
	 * @return */
	public abstract Properties getProperties();
	
	/** 交给子类去实现
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 */
	public abstract Folder getFolder(Store store) throws Exception;
	
	/** 交给子类去实现
	 * 
	 * @param pros
	 * @return */
	public abstract Session getSession(Properties pros);
}
