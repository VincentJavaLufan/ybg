package com.ybg.component.email.receivemail;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import com.ybg.component.email.EmailConstant;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class ReceiveQqMailImpl extends AbstractReceiveMail {
	
	@Override
	public Store getStore(Session session) throws Exception {
		Store store = session.getStore(EmailConstant.PROTOCOL);
		store.connect(EmailConstant.POP_QQ_SERVER, EmailConstant.getEmailaccount(), EmailConstant.getEmailpwd());
		return store;
	}
	
	@Override
	public Properties getProperties() {
		String host = EmailConstant.POP_QQ_SERVER;
		String port = EmailConstant.SSLPOP3PORT;
		String sslFactory = "javax.net.ssl.SSLSocketFactory";
		Properties props = new Properties();
		props.setProperty("mail.pop3.socketFactory.class", sslFactory);
		props.setProperty("mail.pop3.socketFactory.port", port);
		props.setProperty("mail.store.protocol", EmailConstant.PROTOCOL);
		props.setProperty("mail.pop3.host", host);
		props.setProperty("mail.pop3.port", port);
		props.setProperty("mail.pop3.auth.login.disable", "true");
		return props;
	}
	
	@Override
	public Folder getFolder(Store store) throws Exception {
		Folder inbox = null;
		inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);
		return inbox;
	}
	
	@Override
	public Session getSession(Properties pros) {
		Session session = Session.getDefaultInstance(pros, null);
		session.setDebug(false);
		return session;
	}
}
