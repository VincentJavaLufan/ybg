package cn.sina.domain;
import java.io.Serializable;

@SuppressWarnings("serial")
public class WeiboUserDO implements Serializable {
	
	private String	id;
	private String	uid;
	private String	userid;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return "WeiboUserI [id=" + id + ", uid=" + uid + ", userid=" + userid + "]";
	}
}
