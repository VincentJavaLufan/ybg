package cn.sina.qvo;
import com.ybg.base.jdbc.BaseQvoInter;
import cn.sina.domain.WeiboUserI;

public class WeiboUserQvo extends WeiboUserI implements BaseQvoInter {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4775262689650626833L;
	private boolean				blurred;
	
	public boolean isBlurred() {
		return blurred;
	}
	
	public void setBlurred(boolean blurred) {
		this.blurred = blurred;
	}
	
	@Override
	public String toString() {
		return "WeiboUserQvo [blurred=" + blurred + ", toString()=" + super.toString() + "]";
	}
}
