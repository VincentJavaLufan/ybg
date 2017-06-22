package com.ybg.rbac.syspro.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.rbac.syspro.dao.SysproDao;
import com.ybg.rbac.syspro.domain.Syspro;
import com.ybg.rbac.syspro.qvo.SysproQvo;

@Repository
public class SysproServiceImpl implements SysproService{
	@Autowired
	SysproDao sysproDao;
	
	@CacheEvict(value = "sysproCache", allEntries = true)
	public void update(Syspro bean) {
		BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
		BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
		wheremap.put("proname", bean.getProname());
		updatemap.put("provalue", bean.getProvalue());
		sysproDao.update(updatemap, wheremap);
	}
	
	/** 不分页查询用户信息 **/
	@Cacheable(value = "sysproCache", key = "#root.method.name+#root.args[0]")
	public List<Syspro> query(SysproQvo qvo) {
		return sysproDao.query(qvo);
	}
}
