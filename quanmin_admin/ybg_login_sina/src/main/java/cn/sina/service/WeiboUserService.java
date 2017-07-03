package cn.sina.service;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import cn.sina.domain.WeiboUserVO;
import cn.sina.qvo.WeiboUserQuery;

public interface WeiboUserService {
	
	void create(WeiboUserVO bean) throws Exception;
	
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	void remove(BaseMap<String, Object> wheremap);
	
	List<WeiboUserVO> query(WeiboUserQuery qvo) throws Exception;
	
	WeiboUserVO get(String uid) throws Exception;
	
	Map<String, String> getSetting();
}
