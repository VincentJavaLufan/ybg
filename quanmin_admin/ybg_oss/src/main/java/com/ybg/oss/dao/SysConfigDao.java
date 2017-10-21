package com.ybg.oss.dao;
import org.apache.ibatis.annotations.Param;
import com.ybg.base.util.Page;
import com.ybg.oss.domian.SysConfigEntity;

/** 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:46:16 */
public interface SysConfigDao {
	
	/** 根据key，查询value
	 * 
	 * @param paramKey
	 * @return */
	String queryByKey(String paramKey);
	
	/** 根据key，更新value
	 * 
	 * @param key
	 * @param value
	 * @return */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
	/** 更新配置
	 * 
	 * @param config
	 */
	void update(SysConfigEntity config);
	
	/** 批量删除
	 * 
	 * @param ids
	 */
	void deleteBatch(Long[] ids);
	
	/** 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	Page list(Page page, SysConfigEntity qvo) throws Exception;
	
	/** 查询单个
	 * 
	 * @param id
	 * @return */
	SysConfigEntity queryObject(Long id);
	
	/** 保存
	 * 
	 * @param config
	 * @throws Exception
	 */
	void save(SysConfigEntity config) throws Exception;
}
