package com.ybg.oss.service;
import com.alibaba.fastjson.JSON;
import com.ybg.base.util.Page;
import com.ybg.oss.dao.SysConfigDao;
import com.ybg.oss.domian.SysConfigEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@Repository
public class SysConfigServiceImpl implements SysConfigService {
	
	@Autowired
	private SysConfigDao sysConfigDao;
	
	@Override
	public void save(SysConfigEntity config) throws Exception {
		sysConfigDao.save(config);
	}
	
	@Override
	public void update(SysConfigEntity config) {
		sysConfigDao.update(config);
	}
	
	@Override
	public void updateValueByKey(String key, String value) {
		sysConfigDao.updateValueByKey(key, value);
	}
	
	@Override
	public void deleteBatch(Long[] ids) {
		sysConfigDao.deleteBatch(ids);
	}
	
	@Override
	public SysConfigEntity queryObject(Long id) {
		return sysConfigDao.queryObject(id);
	}
	
	@Override
	public String getValue(String key, String defaultValue) {
		String value = sysConfigDao.queryByKey(key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key, null);
		if (StringUtils.isNotBlank(value)) {
			return JSON.parseObject(value, clazz);
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			// throw new Exception("获取参数失败");
		}
		return null;
	}
	
	@Override
	public Page list(Page page, SysConfigEntity qvo) throws Exception {
		return sysConfigDao.list(page, qvo);
	}
}
