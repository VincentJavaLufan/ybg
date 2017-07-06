package com.ybg.region.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.region.domain.RegionVO;

public class RegionMapper implements RowMapper<RegionVO> {
	

	public RegionVO mapRow(ResultSet rs, int index) throws SQLException {
		RegionVO bean = new RegionVO();
						bean.setPkid(rs.getInt("Pkid"));
								bean.setRegionname(rs.getString("Regionname"));
								bean.setLevel(rs.getInt("Level"));
								bean.setParentid(rs.getInt("Parentid"));
								bean.setCitycode(rs.getString("Citycode"));
								bean.setAdcode(rs.getString("Adcode"));
								bean.setCenterlng(rs.getDouble("Centerlng"));
								bean.setCenterlat(rs.getDouble("Centerlat"));
								bean.setProvinceid(rs.getInt("Provinceid"));
								bean.setProvincename(rs.getString("Provincename"));
								bean.setCityid(rs.getInt("Cityid"));
								bean.setCityname(rs.getString("Cityname"));
								bean.setDistrictid(rs.getInt("Districtid"));
								bean.setDistrictname(rs.getString("Districtname"));
								bean.setIsactive(rs.getInt("Isactive"));
								bean.setCreateby(rs.getString("Createby"));
								bean.setCreatetime(rs.getString("Createtime"));
								bean.setModifyby(rs.getString("Modifyby"));
								bean.setModifytime(rs.getString("Modifytime"));
						return bean;
	}
}
