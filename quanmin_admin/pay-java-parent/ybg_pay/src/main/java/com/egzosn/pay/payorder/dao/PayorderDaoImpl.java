package com.egzosn.pay.payorder.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.egzosn.pay.payorder.domain.PayorderVO;
import com.egzosn.pay.payorder.qvo.PayorderQuery;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import com.ybg.base.jdbc.DataBaseConstant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Repository
public class PayorderDaoImpl extends BaseDao implements PayorderDao {
	@Autowired
	@Qualifier(DataBaseConstant.JD_EDU)
	JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	private static String QUERY_TABLE_COLUMN = "  	  payorder.userid, 	  payorder.gmt_create, 	  payorder.gmt-modified, 	  payorder.subject, 	  payorder.body, 	  payorder.price, 	  payorder.outTradeno, 	  payorder.banktype, 	  payorder.deviceinfo, 	  payorder.spbillcreateip, 	  payorder.authcode, 	  payorder.wapurl, 	  payorder.wapname, 	  payorder.openid, id";
	private static String QUERY_TABLE_NAME = "payorder  payorder";

	@Override
	public PayorderVO save(PayorderVO payorder) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("userid", payorder.getUserid());

		createmap.put("subject", payorder.getSubject());
		createmap.put("body", payorder.getBody());
		createmap.put("price", payorder.getPrice());
		createmap.put("outTradeno", payorder.getOutTradeNo());
		createmap.put("banktype", payorder.getBankType());
		createmap.put("deviceinfo", payorder.getDeviceInfo());
		createmap.put("spbillcreateip", payorder.getSpbillCreateIp());
		createmap.put("authcode", payorder.getAuthCode());
		createmap.put("wapurl", payorder.getWapUrl());
		createmap.put("wapname", payorder.getWapName());
		createmap.put("openid", payorder.getOpenid());
		id = baseCreate(createmap, "payorder", "id");
		payorder.setId((String) id);
		return payorder;
	}

	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> WHEREmap) {
		this.baseupdate(updatemap, WHEREmap, "payorder");
	}

	@Override
	public Page list(Page page, PayorderQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<PayorderVO>(PayorderVO.class)));
		} else {
			page.setResult(new ArrayList<PayorderVO>());
		}

		return page;
	}

	private String getcondition(PayorderQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("payorder.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("payorder.isdelete=0");// 默认
		// }
		sqlappen(sql, "payorder.id", qvo.getId());
		sqlappen(sql, "payorder.userid", qvo.getUserid());

		sqlappen(sql, "payorder.subject", qvo.getSubject());
		sqlappen(sql, "payorder.body", qvo.getBody());
		// sqlappen(sql, "payorder.price", qvo.getPrice());
		sqlappen(sql, "payorder.outTradeno", qvo.getOutTradeNo());
		sqlappen(sql, "payorder.banktype", qvo.getBankType());
		sqlappen(sql, "payorder.deviceinfo", qvo.getDeviceInfo());
		sqlappen(sql, "payorder.spbillcreateip", qvo.getSpbillCreateIp());
		sqlappen(sql, "payorder.authcode", qvo.getAuthCode());
		sqlappen(sql, "payorder.wapurl", qvo.getWapUrl());
		sqlappen(sql, "payorder.wapname", qvo.getWapName());
		sqlappen(sql, "payorder.openid", qvo.getOpenid());
		return sql.toString();
	}

	@Override
	public List<PayorderVO> list(PayorderQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<PayorderVO>(PayorderVO.class));
	}

	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "payorder");
	}

	@Override
	public PayorderVO get(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<PayorderVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<PayorderVO>(PayorderVO.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
