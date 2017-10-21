package com.ybg.oss.service;
import java.util.List;
import java.util.Map;
import com.ybg.base.util.Page;
import com.ybg.oss.domian.SysConfigEntity;

/** 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:49:01 */
public interface SysConfigService {
	
	/** 保存配置信息
	 * 
	 * @param config
	 * @throws Exception
	 */
	public void save(SysConfigEntity config) throws Exception;
	
	/** 更新配置信息
	 * 
	 * @param config
	 */
	public void update(SysConfigEntity config);
	
	/** 根据key，更新value
	 * 
	 * @param key
	 * @param value
	 */
	public void updateValueByKey(String key, String value);
	
	/** 删除配置信息
	 * 
	 * @param ids
	 */
	public void deleteBatch(Long[] ids);
	
	/** 查询单个
	 * 
	 * @param id
	 * @return */
	public SysConfigEntity queryObject(Long id);
	
	/** 根据key，获取配置的value值
	 * 
	 * @param key
	 *            key
	 * @param defaultValue
	 *            缺省值
	 * @return */
	public String getValue(String key, String defaultValue);
	
	/** 根据key，获取value的Object对象
	 * 
	 * @param key
	 *            key
	 * @param clazz
	 *            Object对象
	 * @return */
	public <T> T getConfigObject(String key, Class<T> clazz);
	
	/** 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	Page list(Page page, SysConfigEntity qvo) throws Exception;
}
