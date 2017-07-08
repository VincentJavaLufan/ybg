package com.ybg.oss.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.ybg.base.util.Page;
import com.ybg.oss.domian.SysConfigEntity;

/** 系统配置信息
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月4日 下午6:46:16 */
public interface SysConfigDao {
	
	/** 根据key，查询value */
	String queryByKey(String paramKey);
	
	/** 根据key，更新value */
	int updateValueByKey(@Param("key") String key, @Param("value") String value);
	
	void update(SysConfigEntity config);
	
	void deleteBatch(Long[] ids);
//	
//	List<SysConfigEntity> queryList(Map<String, Object> map);
//	
//	int queryTotal(Map<String, Object> map);
	
	Page list(Page page,SysConfigEntity qvo) throws Exception;
	
	SysConfigEntity queryObject(Long id);
	
	void save(SysConfigEntity config) throws Exception;
}
