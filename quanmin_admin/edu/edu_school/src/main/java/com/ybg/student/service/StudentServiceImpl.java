package com.ybg.student.service;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import com.ybg.student.dao.StudentDao;

import com.ybg.student.domain.StudentVO;
import com.ybg.student.qvo.StudentQuery;
import java.util.List;

import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

@Repository
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	/** 返回主键的创建
	 * 
	 * @throws Exception **/
	public StudentVO save(StudentVO bean) throws Exception {
		return studentDao.save(bean);

	}

	/**
	 * 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称
	 **/

	@Override
	public void update(BaseMap<String, Object> updatemap,
			BaseMap<String, Object> wheremap) {
		studentDao.update(updatemap, wheremap);
	}

	/** 分页查询 **/
	@Override
	public Page list(Page page, StudentQuery qvo) throws Exception {
		return studentDao.list(page, qvo);
	}

	/** 不分页查询 **/
	@Override
	public List<StudentVO> list(StudentQuery qvo) throws Exception {
		return studentDao.list(qvo);
	}

	/** 根据条件删除 **/
	public void remove(BaseMap<String, Object> wheremap) {
		studentDao.remove(wheremap);
	}

	public StudentVO get(String id) {
		return studentDao.get(id);
	}
}
