package com.egzosn.pay.seller.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.egzosn.pay.seller.domain.SellerVO;
import com.egzosn.pay.seller.qvo.SellerQuery;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;

@Repository
public class SellerDaoImpl extends BaseDao implements SellerDao {
	private static String QUERY_TABLE_COLUMN = "payAccount.partner,payAccount.appid,payAccount.publickey,payAccount.privatekey,payAccount.notifyurl,payAccount.returnurl,payAccount.seller,payAccount.signtype,payAccount.inputcharset,payAccount.paytype,payAccount.msgtype,payAccount.test, payid";
	private static String QUERY_TABLE_NAME = "pay_account  payAccount";
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public SellerVO get(String id) {

		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("payid='" + id + "'");
		System.out.println(sql.toString());
		List<SellerVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SellerVO>(SellerVO.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}

	@Override
	public Page list(Page page, SellerQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<SellerVO>(SellerVO.class)));
		} else {
			page.setResult(new ArrayList<SellerVO>());
		}
		return page;
	}

	private String getcondition(SellerQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("payAccount.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("payAccount.isdelete=0");// 默认
		// }
		sqlappen(sql, "payAccount.payid", qvo.getPayid());
		sqlappen(sql, "payAccount.partner", qvo.getPartner());
		sqlappen(sql, "payAccount.appid", qvo.getAppid());
		sqlappen(sql, "payAccount.publickey", qvo.getPublickey());
		sqlappen(sql, "payAccount.privatekey", qvo.getPrivatekey());
		sqlappen(sql, "payAccount.notifyurl", qvo.getNotifyurl());
		sqlappen(sql, "payAccount.returnurl", qvo.getReturnurl());
		sqlappen(sql, "payAccount.seller", qvo.getSeller());
		sqlappen(sql, "payAccount.signtype", qvo.getSigntype());
		sqlappen(sql, "payAccount.inputcharset", qvo.getInputcharset());
		if (qvo.getPaytype() != null) {
			sqlappen(sql, "payAccount.paytype", qvo.getPaytype().toString());
		}
		if (qvo.getMsgtype() != null) {
			sqlappen(sql, "payAccount.msgtype", qvo.getMsgtype().toString());
		}

		return sql.toString();
	}

	@Override
	public List<SellerVO> list(SellerQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<SellerVO>(SellerVO.class));
	}

	@Override
	public void update(BaseMap<String, Object> updateMap, BaseMap<String, Object> whereMap) {
		baseupdate(updateMap, whereMap, "pay_account");

	}

	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		baseremove(conditionmap, "pay_account");

	}

	@Override
	public SellerVO create(SellerVO payAccount) throws Exception {

		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("partner", payAccount.getPartner());
		createmap.put("appid", payAccount.getAppid());
		createmap.put("publickey", payAccount.getPublickey());
		createmap.put("privatekey", payAccount.getPrivatekey());
		createmap.put("notifyurl", payAccount.getNotifyurl());
		createmap.put("returnurl", payAccount.getReturnurl());
		createmap.put("seller", payAccount.getSeller());
		createmap.put("signtype", payAccount.getSigntype());
		createmap.put("inputcharset", payAccount.getInputcharset());
		if (payAccount.getPaytype() != null) {
			createmap.put("paytype", payAccount.getPaytype().toString());
		}
		if (payAccount.getPaytype() != null) {
			createmap.put("msgtype", payAccount.getMsgtype().toString());
		}
		createmap.put("test", payAccount.getTest());

		id = baseCreate(createmap, "pay_account", "payid");
		payAccount.setPayid((String) id);
		return payAccount;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {

		return jdbcTemplate;
	}

}
