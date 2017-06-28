package cn.sina.qvo;
import com.ybg.base.jdbc.BaseQueryAble;
import cn.sina.domain.WeiboUserDO;

public class WeiboUserQuery extends WeiboUserDO implements BaseQueryAble {
	
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
