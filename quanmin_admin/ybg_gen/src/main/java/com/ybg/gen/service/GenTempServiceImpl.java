package com.ybg.gen.service;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ybg.gen.dao.GenTempDao;
import com.ybg.gen.domain.GenTempDO;
import com.ybg.gen.domain.GenTempVO;
import com.ybg.gen.qvo.GenTempQuery;
import java.util.List;
import java.util.Map;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

@Repository
public class GenTempServiceImpl implements GenTempService {
	
	@Autowired
	private GenTempDao genTempDao;
	
	@Override
	/** 返回主键的创建
	 * 
	 * @throws Exception
	 **/
	public GenTempVO save(GenTempVO bean) throws Exception {
		return genTempDao.save(bean);
	}
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		genTempDao.update(updatemap, wheremap);
	}
	
	/** 分页查询 **/
	@Override
	public Page list(Page page, GenTempQuery qvo) throws Exception {
		return genTempDao.list(page, qvo);
	}
	
	/** 不分页查询 **/
	@Override
	public List<GenTempVO> list(GenTempQuery qvo) throws Exception {
		return genTempDao.list(qvo);
	}
	
	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap) {
		genTempDao.remove(wheremap);
	}
	
	public GenTempVO get(String id) {
		return genTempDao.get(id);
	}
}
