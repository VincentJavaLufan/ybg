package com.ybg.student.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.DataBaseConstant;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import com.ybg.student.domain.StudentVO;
import com.ybg.student.mapper.StudentMapper;
import com.ybg.student.qvo.StudentQuery;

@Repository
public class StudentDaoImpl extends BaseDao implements StudentDao {
	
	@Autowired
	@Qualifier(DataBaseConstant.DB_EDU)
	JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_NAME	= "  	 student.id, 	 	  student.studentname, 	 	 student.studentname, 	 	  student.studentno, 	 	 student.studentno, 	 	  student.classid, 	 	 student.classid, 	 	  student.gradeid, 	 	 student.gradeid, 	 	  student.schoolid, 	 	 student.schoolid, 	 	  student.gradename, 	 	 student.gradename, 	 	  student.classname, 	 	 student.classname, 	 	  student.schoolname, 	 	 student.schoolname, 	 	  student.identitycard, 	 	 student.identitycard, 	 	  student.headurl, 	 	 student.headurl, 	 	  student.birthday, 	 	 student.birthday, 	 	  student.gmt_create, 	 	 student.gmt_create, 	 	  student.gmt_modified, 	 	 student.gmt_modified, 	 	  student.studentorign, 	 	 student.studentorign, 	 	  student.studentaddress, 	 	 student.studentaddress, 	 	  student.parentname, 	 	 student.parentname, 	 	  student.gender, 	 	 student.gender, 	 	  student.cityid, 	 	 student.cityid, 	 	  student.regionid, 	 	 student.regionid, 	 	  student.districtid, 	 	 student.districtid, 	 	  student.provinceid, 	 	 student.provinceid, 	 id";
	private static String	QUERY_TABLE_COLUMN	= "edu_student  student";
	
	@Override
	public StudentVO save(StudentVO student) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("studentname", student.getStudentname());
		createmap.put("studentno", student.getStudentno());
		createmap.put("classid", student.getClassid());
		createmap.put("gradeid", student.getGradeid());
		createmap.put("schoolid", student.getSchoolid());
		createmap.put("gradename", student.getGradename());
		createmap.put("classname", student.getClassname());
		createmap.put("schoolname", student.getSchoolname());
		createmap.put("identitycard", student.getIdentitycard());
		createmap.put("headurl", student.getHeadurl());
		createmap.put("birthday", student.getBirthday());
		createmap.put("gmt_create", student.getGmtCreate());
		createmap.put("gmt_modified", student.getGmtModified());
		createmap.put("studentorign", student.getStudentorign());
		createmap.put("studentaddress", student.getStudentaddress());
		createmap.put("parentname", student.getParentname());
		createmap.put("gender", student.getGender());
		createmap.put("cityid", student.getCityid());
		createmap.put("regionid", student.getRegionid());
		createmap.put("districtid", student.getDistrictid());
		createmap.put("provinceid", student.getProvinceid());
		id = baseCreate(createmap, "edu_student", "id");
		student.setId((String) id);
		return student;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "edu_student");
	}
	
	@Override
	public Page list(Page page, StudentQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new StudentMapper()));
		}
		else {
			page.setResult(new ArrayList<StudentVO>());
		}
		return page;
	}
	
	private String getcondition(StudentQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("student.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("student.isdelete=0");// 默认
		// }
		sqlappen(sql, "student.id", qvo.getId());
		sqlappen(sql, "student.studentname", qvo.getStudentname());
		sqlappen(sql, "student.studentno", qvo.getStudentno());
		sqlappen(sql, "student.classid", qvo.getClassid());
		sqlappen(sql, "student.gradeid", qvo.getGradeid());
		sqlappen(sql, "student.schoolid", qvo.getSchoolid());
		sqlappen(sql, "student.gradename", qvo.getGradename());
		sqlappen(sql, "student.classname", qvo.getClassname());
		sqlappen(sql, "student.schoolname", qvo.getSchoolname());
		sqlappen(sql, "student.identitycard", qvo.getIdentitycard());
		sqlappen(sql, "student.headurl", qvo.getHeadurl());
		sqlappen(sql, "student.birthday", qvo.getBirthday());
		sqlappen(sql, "student.gmt_create", qvo.getGmtCreate());
		sqlappen(sql, "student.gmt_modified", qvo.getGmtModified());
		sqlappen(sql, "student.studentorign", qvo.getStudentorign());
		sqlappen(sql, "student.studentaddress", qvo.getStudentaddress());
		sqlappen(sql, "student.parentname", qvo.getParentname());
		sqlappen(sql, "student.gender", qvo.getGender());
		sqlappen(sql, "student.cityid", qvo.getCityid());
		sqlappen(sql, "student.regionid", qvo.getRegionid());
		sqlappen(sql, "student.districtid", qvo.getDistrictid());
		sqlappen(sql, "student.provinceid", qvo.getProvinceid());
		return sql.toString();
	}
	
	@Override
	public List<StudentVO> list(StudentQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new StudentMapper());
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "edu_student");
	}
	
	@Override
	public StudentVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<StudentVO> list = getJdbcTemplate().query(sql.toString(), new StudentMapper());
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
