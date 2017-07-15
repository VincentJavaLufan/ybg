package com.ybg.oa.department.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.oa.department.domain.DepartmentDO;
import com.ybg.oa.department.domain.DepartmentVO;
import com.ybg.oa.department.mapper.DepartmentMapper;
import com.ybg.oa.department.qvo.DepartmentQuery;

@Repository
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {



	private static String QUERY_TABLE_NAME = "  	  department.name, 	  department.companyid, 	  department.gmt_create, 	  department.parentid, 	  department.gmt_modified, 	  department.companyname, id";
	private static String QUERY_TABLE_COLUMN ="oa_department  department";
	@Override
	public DepartmentVO save(DepartmentVO department) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;		
									  	createmap.put("name", department.getName());
							  	createmap.put("companyid", department.getCompanyid());
							  	createmap.put("gmt_create", department.getGmtCreate());
							  	createmap.put("parentid", department.getParentid());
							  	createmap.put("gmt_modified", department.getGmtModified());
							  	createmap.put("companyname", department.getCompanyname());
						id = baseCreate(createmap, "oa_department", "id");
		department.setId((String) id);
		return department;
	}
	@Override
	public void update(BaseMap<String, Object> updatemap,
			BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "oa_department");
	}
@Override
	public Page list(Page page,  DepartmentQuery qvo)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM)
				.append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql),
					new DepartmentMapper()));
		} else {
			page.setResult(new ArrayList< DepartmentVO>());
		}

		return page;
	}

	private String getcondition( DepartmentQuery qvo) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
//		if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
//			sql.append(AND).append("department.isdelete=").append(qvo.getIsdelete());
//		} else {
//			sql.append(AND).append("department.isdelete=0");// 默认
//		}
        		sqlappen(sql, "department.id", qvo.getId());
				sqlappen(sql, "department.name", qvo.getName());
				sqlappen(sql, "department.companyid", qvo.getCompanyid());
				sqlappen(sql, "department.gmt_create", qvo.getGmtCreate());
				sqlappen(sql, "department.parentid", qvo.getParentid());
				sqlappen(sql, "department.gmt_modified", qvo.getGmtModified());
				sqlappen(sql, "department.companyname", qvo.getCompanyname());
				return sql.toString();
	}
@Override
	public List<DepartmentVO> list(DepartmentQuery qvo) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM)
				.append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new DepartmentMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		 baseremove(wheremap, "oa_department");		
	}
	@Override
	public DepartmentVO get(String id){
	
	StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM)
				.append(QUERY_TABLE_NAME);
				sql.append(WHERE).append("1=1");
				sql.append(AND).append("id='"+id+"'");
		List<DepartmentVO> list=getJdbcTemplate().query(sql.toString(), new DepartmentMapper());		
		return QvoConditionUtil.checkList(list)?list.get(0):null;
	}
}
