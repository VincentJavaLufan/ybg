package com.ybg.oss.dao;
import com.ybg.base.util.Page;
import com.ybg.oss.domian.SysOssEntity;

/** 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26 */
public interface SysOssDao {
	
	/** 查询单个
	 * 
	 * @param id
	 * @return */
	SysOssEntity queryObject(Long id);
	
	/** 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return */
	Page list(Page page, SysOssEntity qvo);
	
	/** 保存
	 * 
	 * @param sysOss
	 * @throws Exception
	 */
	void save(SysOssEntity sysOss) throws Exception;
	
	/** 更新
	 * 
	 * @param sysOss
	 */
	void update(SysOssEntity sysOss);
	
	/** 删除
	 * 
	 * @param id
	 */
	void delete(Long id);
	
	/** 批量删除
	 * 
	 * @param ids
	 */
	void deleteBatch(Long[] ids);
}
