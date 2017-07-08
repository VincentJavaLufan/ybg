package com.ybg.oss.dao;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.oss.domian.SysConfigEntity;

@Repository
public class SysConfigDaoImpl extends BaseDao implements SysConfigDao {
	
	@Override
	public String queryByKey(String paramKey) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int updateValueByKey(String key, String value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(SysConfigEntity config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBatch(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SysConfigEntity> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysConfigEntity queryObject(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(SysConfigEntity config) {
		// TODO Auto-generated method stub
		
	}
}
