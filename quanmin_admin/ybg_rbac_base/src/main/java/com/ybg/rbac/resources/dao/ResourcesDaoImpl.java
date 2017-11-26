package com.ybg.rbac.resources.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.rbac.resources.domain.SysResourcesVO;
import com.ybg.rbac.resources.qvo.ResourcesQuery;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * 
 * @date 2016/10/1 */
@Repository
public class ResourcesDaoImpl extends BaseDao implements ResourcesDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "sys_resources res";
	private static String	QUERY_TABLE_COLUMN	= " res.id,res.name,res.parentid,res.reskey,res.type,res.resurl,res.level,res.ishide,res.description,res.colorid ";
	
	@Override
	public SysResourcesVO save(final SysResourcesVO bean) {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("name", bean.getName());
		createmap.put("parentid", bean.getParentid());
		createmap.put("reskey", bean.getReskey());
		createmap.put("type", bean.getType());
		createmap.put("resurl", bean.getResurl());
		createmap.put("level", bean.getLevel());
		createmap.put("icon", bean.getIcon());
		createmap.put("ishide", bean.getIshide());
		createmap.put("description", bean.getDescription());
		createmap.put("colorid", bean.getColorid());
		try {
			id = baseCreate(createmap, "sys_resources", "id");
		} catch (Exception e) {
			e.printStackTrace();
		}
		bean.setId(id);
		return bean;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> whereMap) {
		this.baseupdate(updatemap, whereMap, "sys_resources");
	}
	
	@Override
	public Page list(Page page, ResourcesQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		// 原来是有顺序的 前后分离后没有必要了 private static String QUERY_TABLE_COLUMN = " res.id,res.name,res.parentid,res.reskey,res.type,res.resurl,res.level,res.icon,res.ishide,res.description,res.colorid ";
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(",color.colorclass").append(FROM).append(QUERY_TABLE_NAME).append(",sys_color color");
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<SysResourcesVO>(SysResourcesVO.class)));
		}
		else {
			page.setResult(new ArrayList<SysResourcesVO>());
		}
		return page;
	}
	
	private String getcondition(ResourcesQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
			sql.append(AND).append("res.isdelete=").append(qvo.getIsdelete());
		}
		else {
			// 默认
			sql.append(AND).append("res.isdelete=0");
		}
		sql.append(AND).append("res.colorid=color.id");
		sqlappen(sql, "res.colorid", qvo.getColorid());
		sqlappen(sql, "res.id", qvo.getId());
		sqlappen(sql, "res.level", qvo.getLevel());
		sqlappen(sql, "res.description", qvo.getDescription(), qvo);
		sqlappen(sql, "res.icon", qvo.getIcon(), qvo);
		sqlappen(sql, "res.ishide", qvo.getIshide(), qvo);
		sqlappen(sql, "res.name", qvo.getName(), qvo);
		sqlappen(sql, "res.reskey", qvo.getReskey(), qvo);
		sqlappen(sql, "res.resurl", qvo.getResurl(), qvo);
		sqlappen(sql, "res.type", qvo.getType());
		return sql.toString();
	}
	
	@Override
	public List<SysResourcesVO> list(ResourcesQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(",color.colorclass").append(FROM).append(QUERY_TABLE_NAME).append(",sys_color color");
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SysResourcesVO>(SysResourcesVO.class));
	}
	
	@Override
	public List<SysResourcesVO> getRolesByRoleId(String roleid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(",sc.colorclass").append(FROM).append("sys_resources res,sys_res_role rr,sys_color sc").append(WHERE).append("res.id=rr.resId");
		sqlappen(sql, "rr.roleid", roleid);
		sql.append(AND).append("rr.state=0");
		sql.append(AND).append("res.isdelete=0");
		sql.append(AND).append("sc.id=res.colorid");
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SysResourcesVO>(SysResourcesVO.class));
	}
	
	@Override
	public List<SysResourcesVO> getOperatorButton(String roleid, String parentid) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(",color.colorclass").append(FROM).append(QUERY_TABLE_NAME).append(", sys_res_role rr  ").append(",sys_color color");
		sql.append(WHERE).append("res.id=rr.resId").append(AND).append("res.colorid=color.id").append(AND).append("rr.roleid=").append(roleid);
		sqlappen(sql, "rr.roleid", roleid);
		sqlappen(sql, "parentid", parentid);
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SysResourcesVO>(SysResourcesVO.class));
	}
}
