package com.ybg.oss.service;



import java.util.List;
import java.util.Map;
import com.ybg.base.util.Page;
import com.ybg.oss.domian.SysOssEntity;

/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService {
	
	SysOssEntity queryObject(Long id);
	
	Page list(Page page,SysOssEntity qvo);
	
	void save(SysOssEntity sysOss) throws Exception;
	
	void update(SysOssEntity sysOss);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
