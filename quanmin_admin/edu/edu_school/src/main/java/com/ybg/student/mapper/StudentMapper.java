package com.ybg.student.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.ybg.student.domain.StudentVO;

public class StudentMapper implements RowMapper<StudentVO> {
	

	public StudentVO mapRow(ResultSet rs, int index) throws SQLException {
		StudentVO bean = new StudentVO();
						bean.setId(rs.getString("Id"));
								bean.setStudentname(rs.getString("Studentname"));
								bean.setStudentno(rs.getString("Studentno"));
								bean.setClassid(rs.getString("Classid"));
								bean.setGradeid(rs.getInt("Gradeid"));
								bean.setSchoolid(rs.getString("Schoolid"));
								bean.setGradename(rs.getString("Gradename"));
								bean.setClassname(rs.getString("Classname"));
								bean.setSchoolname(rs.getString("Schoolname"));
								bean.setIdentitycard(rs.getString("Identitycard"));
								bean.setHeadurl(rs.getString("Headurl"));
								bean.setBirthday(rs.getString("Birthday"));
								bean.setGmtCreate(rs.getString("GmtCreate"));
								bean.setGmtModified(rs.getString("GmtModified"));
								bean.setStudentorign(rs.getString("Studentorign"));
								bean.setStudentaddress(rs.getString("Studentaddress"));
								bean.setParentname(rs.getString("Parentname"));
								bean.setGender(rs.getInt("Gender"));
								bean.setCityid(rs.getInt("Cityid"));
								bean.setRegionid(rs.getInt("Regionid"));
								bean.setDistrictid(rs.getString("Districtid"));
								bean.setProvinceid(rs.getInt("Provinceid"));
						return bean;
	}
}
