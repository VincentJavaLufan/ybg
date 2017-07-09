package com.ybg.oss.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ybg.base.util.Page;
import com.ybg.oss.dao.SysOssDao;
import com.ybg.oss.domian.SysOssEntity;

@Repository
public class SysOssServiceImpl implements SysOssService {
	
	@Autowired
	private SysOssDao sysOssDao;
	
	@Override
	public SysOssEntity queryObject(Long id) {
		return sysOssDao.queryObject(id);
	}
	
	@Override
	public void save(SysOssEntity sysOss) throws Exception {
		sysOssDao.save(sysOss);
	}
	
	@Override
	public void update(SysOssEntity sysOss) {
		sysOssDao.update(sysOss);
	}
	
	@Override
	public void delete(Long id) {
		sysOssDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids) {
		sysOssDao.deleteBatch(ids);
	}
	
	@Override
	public Page list(Page page, SysOssEntity qvo) {
		return sysOssDao.list(page, qvo);
	}
}
