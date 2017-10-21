package com.ybg.component.email.receivemail;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import com.ybg.component.email.EmailConstant;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
public class Receive163mailImpl implements ReceiveMailInter {
	
	@Override
	public Message[] receiveMail() throws Exception {
		// 定义连接POP3服务器的属性信息
		String pop3Server = EmailConstant.POP163SERVER;
		String protocol = EmailConstant.PROTOCOL;
		String username = EmailConstant.getEmailaccount();
		String password = EmailConstant.getEmailpwd();
		// QQ邮箱的SMTP的授权码，什么是授权码，它又是如何设置？
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", protocol);
		// 使用的协议（JavaMail规范要求）
		props.setProperty("mail.pop.host", pop3Server);
		// 发件人的邮箱的 SMTP服务器地址
		// 获取连接
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);
		// 获取Store对象
		Store store = session.getStore(protocol);
		store.connect(pop3Server, username, password);
		// POP3服务器的登陆认证
		// 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
		Folder folder = store.getFolder("INBOX");
		// 获得用户的邮件帐户
		folder.open(Folder.READ_WRITE);
		// 设置对邮件帐户的访问权限
		Message[] messages = folder.getMessages();
		// 得到邮箱帐户中的所有邮件
		folder.close(false);
		// 关闭邮件夹对象
		store.close();
		// 关闭连接对象
		return messages;
	}
}
