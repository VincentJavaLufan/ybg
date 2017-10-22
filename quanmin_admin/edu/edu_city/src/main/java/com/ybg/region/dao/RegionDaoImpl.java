package com.ybg.region.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.DataBaseConstant;
import com.ybg.base.util.Page;
import com.ybg.region.domain.RegionVO;
import com.ybg.region.qvo.RegionQuery;

/** @author Deament
 * @since 2017-10-22 **/
@Repository
public class RegionDaoImpl extends BaseDao implements RegionDao {
	
	@Autowired
	@Qualifier(DataBaseConstant.JD_EDU)
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "  	 region.PkId, 	 	  region.RegionName, 	 	 region.RegionName, 	 	  region.Level, 	 	 region.Level, 	 	  region.ParentId, 	 	 region.ParentId, 	 	  region.CityCode, 	 	 region.CityCode, 	 	  region.ADCode, 	 	 region.ADCode, 	 	  region.CenterLng, 	 	 region.CenterLng, 	 	  region.CenterLat, 	 	 region.CenterLat, 	 	  region.ProvinceId, 	 	 region.ProvinceId, 	 	  region.ProvinceName, 	 	 region.ProvinceName, 	 	  region.CityId, 	 	 region.CityId, 	 	  region.CityName, 	 	 region.CityName, 	 	  region.DistrictId, 	 	 region.DistrictId, 	 	  region.DistrictName, 	 	 region.DistrictName, 	 	  region.IsActive, 	 	 region.IsActive, 	 	  region.CreateBy, 	 	 region.CreateBy, 	 	  region.CreateTime, 	 	 region.CreateTime, 	 	  region.ModifyBy, 	 	 region.ModifyBy, 	 	  region.ModifyTime, 	 	 region.ModifyTime, 	 PkId";
	private static String	QUERY_TABLE_COLUMN	= "edu_region  region";
	
	@Override
	public RegionVO save(RegionVO region) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		Object id = null;
		createmap.put("RegionName", region.getRegionname());
		createmap.put("Level", region.getLevel());
		createmap.put("ParentId", region.getParentid());
		createmap.put("CityCode", region.getCitycode());
		createmap.put("ADCode", region.getAdcode());
		createmap.put("CenterLng", region.getCenterlng());
		createmap.put("CenterLat", region.getCenterlat());
		createmap.put("ProvinceId", region.getProvinceid());
		createmap.put("ProvinceName", region.getProvincename());
		createmap.put("CityId", region.getCityid());
		createmap.put("CityName", region.getCityname());
		createmap.put("DistrictId", region.getDistrictid());
		createmap.put("DistrictName", region.getDistrictname());
		createmap.put("IsActive", region.getIsactive());
		createmap.put("CreateBy", region.getCreateby());
		createmap.put("CreateTime", region.getCreatetime());
		createmap.put("ModifyBy", region.getModifyby());
		createmap.put("ModifyTime", region.getModifytime());
		id = basecreate(createmap, "edu_region", true, new Integer(0));
		region.setPkid((Integer) id);
		return region;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> whereMap) {
		this.baseupdate(updatemap, whereMap, "edu_region");
	}
	
	@Override
	public Page list(Page page, RegionQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<RegionVO>(RegionVO.class)));
		}
		else {
			page.setResult(new ArrayList<RegionVO>());
		}
		return page;
	}
	
	private String getcondition(RegionQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("region.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("region.isdelete=0");// 默认
		// }
		sqlappen(sql, "region.PkId", qvo.getPkid());
		sqlappen(sql, "region.RegionName", qvo.getRegionname());
		sqlappen(sql, "region.Level", qvo.getLevel());
		sqlappen(sql, "region.ParentId", qvo.getParentid());
		sqlappen(sql, "region.CityCode", qvo.getCitycode());
		sqlappen(sql, "region.ADCode", qvo.getAdcode());
		sqlappen(sql, "region.CenterLng", qvo.getCenterlng());
		sqlappen(sql, "region.CenterLat", qvo.getCenterlat());
		sqlappen(sql, "region.ProvinceId", qvo.getProvinceid());
		sqlappen(sql, "region.ProvinceName", qvo.getProvincename());
		sqlappen(sql, "region.CityId", qvo.getCityid());
		sqlappen(sql, "region.CityName", qvo.getCityname());
		sqlappen(sql, "region.DistrictId", qvo.getDistrictid());
		sqlappen(sql, "region.DistrictName", qvo.getDistrictname());
		sqlappen(sql, "region.IsActive", qvo.getIsactive());
		sqlappen(sql, "region.CreateBy", qvo.getCreateby());
		sqlappen(sql, "region.CreateTime", qvo.getCreatetime());
		sqlappen(sql, "region.ModifyBy", qvo.getModifyby());
		sqlappen(sql, "region.ModifyTime", qvo.getModifytime());
		return sql.toString();
	}
	
	@Override
	public List<RegionVO> list(RegionQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<RegionVO>(RegionVO.class));
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "edu_region");
	}
	
	@Override
	public RegionVO get(Integer pkid) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND + "region.PkId=" + pkid);
		List<RegionVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<RegionVO>(RegionVO.class));
		return list.get(0);
	}
}
