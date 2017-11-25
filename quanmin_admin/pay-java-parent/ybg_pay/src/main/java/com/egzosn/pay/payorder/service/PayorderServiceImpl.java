package com.egzosn.pay.payorder.service;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
import com.egzosn.pay.payorder.dao.PayorderDao;
import com.egzosn.pay.payorder.domain.PayorderVO;
import com.egzosn.pay.payorder.qvo.PayorderQuery;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/** @author https://gitee.com/YYDeament/88ybg
 *
 * @date
 * @version v1.0 */
@Repository
public class PayorderServiceImpl implements PayorderService {
	
	@Autowired
	private PayorderDao payorderDao;
	
	@Override
	/** 返回主键的创建
	 * 
	 * @throws Exception
	 **/
	public PayorderVO save(PayorderVO bean) throws Exception {
		return payorderDao.save(bean);
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
		payorderDao.update(updatemap, wheremap);
	}
	
	/** 分页查询 **/
	@Override
	public Page list(Page page, PayorderQuery qvo) throws Exception {
		return payorderDao.list(page, qvo);
	}
	
	/** 不分页查询 **/
	@Override
	public List<PayorderVO> list(PayorderQuery qvo) throws Exception {
		return payorderDao.list(qvo);
	}
	
	/** 根据条件删除 **/
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		payorderDao.remove(wheremap);
	}
	
	@Override
	public PayorderVO get(String id) {
		return payorderDao.get(id);
	}
}
