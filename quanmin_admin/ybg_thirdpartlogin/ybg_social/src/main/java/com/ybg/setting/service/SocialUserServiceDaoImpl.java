package com.ybg.setting.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.setting.dao.SocialUserDao;
import com.ybg.setting.domain.SocialUserVO;
import com.ybg.setting.qvo.SocialUserQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Repository
public class SocialUserServiceDaoImpl implements SocialUserService {
	
	@Autowired
	SocialUserDao socialUserDao;
	
	@Override
	public List<SocialUserVO> query(SocialUserQuery qvo) throws Exception {
		return socialUserDao.query(qvo);
	}
	
	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		socialUserDao.remove(conditionmap);
	}
	
	@Override
	public void create(String userid, String provideruserid, String prividerid) {
		socialUserDao.create(userid, provideruserid, prividerid);
	}
	
	@Override
	public SocialUserVO get(String provideruserid, String prividerid) {
		return socialUserDao.get(provideruserid, prividerid);
	}
}
