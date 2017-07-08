package com.ybg.oa.company.service;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ybg.oa.company.dao.CompanyDao;
import com.ybg.oa.company.domain.CompanyVO;
import com.ybg.oa.company.qvo.CompanyQuery;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

@Repository
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	public CompanyVO save(CompanyVO bean) throws Exception {
		return companyDao.save(bean);
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
		companyDao.update(updatemap, wheremap);
	}
	
	/** 分页查询 **/
	@Override
	public Page list(Page page, CompanyQuery qvo) throws Exception {
		return companyDao.list(page, qvo);
	}
	
	/** 不分页查询 **/
	@Override
	public List<CompanyVO> list(CompanyQuery qvo) throws Exception {
		return companyDao.list(qvo);
	}
	
	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap) {
		companyDao.remove(wheremap);
	}
	
	public CompanyVO get(String id) {
		return companyDao.get(id);
	}
}
