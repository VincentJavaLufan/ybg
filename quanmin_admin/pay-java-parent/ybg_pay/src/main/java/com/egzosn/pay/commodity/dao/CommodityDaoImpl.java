package com.egzosn.pay.commodity.dao;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import com.egzosn.pay.commodity.domain.CommodityVO;
import com.egzosn.pay.commodity.qvo.CommodityQuery;
import com.ybg.base.jdbc.BaseDao;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.base.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/** @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0 */
@Repository
public class CommodityDaoImpl extends BaseDao implements CommodityDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	private static String	QUERY_TABLE_COLUMN	= "commodity.name,commodity.type,commodity.price,commodity.picture,commodity.description,commodity.gmt_modified, id";
	private static String	QUERY_TABLE_NAME	= "pay_commodity  commodity";
	
	@Override
	public CommodityVO save(CommodityVO commodity) throws Exception {
		BaseMap<String, Object> createmap = new BaseMap<String, Object>();
		String id = null;
		createmap.put("name", commodity.getName());
		createmap.put("type", commodity.getType());
		createmap.put("price", commodity.getPrice());
		createmap.put("picture", commodity.getPicture());
		createmap.put("description", commodity.getDescription());
		id = baseCreate(createmap, "pay_commodity", "id");
		commodity.setId((String) id);
		return commodity;
	}
	
	@Override
	public void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap) {
		this.baseupdate(updatemap, wheremap, "pay_commodity");
	}
	
	@Override
	public Page list(Page page, CommodityQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		page.setTotals(queryForInt(sql));
		if (page.getTotals() > 0) {
			page.setResult(getJdbcTemplate().query(page.getPagesql(sql), new BeanPropertyRowMapper<CommodityVO>(CommodityVO.class)));
		}
		else {
			page.setResult(new ArrayList<CommodityVO>());
		}
		return page;
	}
	
	private String getcondition(CommodityQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(WHERE).append("1=1");
		// if (QvoConditionUtil.checkInteger(qvo.getIsdelete())) {
		// sql.append(AND).append("commodity.isdelete=").append(qvo.getIsdelete());
		// } else {
		// sql.append(AND).append("commodity.isdelete=0");// 默认
		// }
		sqlappen(sql, "commodity.id", qvo.getId());
		sqlappen(sql, "commodity.name", qvo.getName());
		sqlappen(sql, "commodity.type", qvo.getType());
		// sqlappen(sql, "commodity.price", qvo.getPrice());
		sqlappen(sql, "commodity.picture", qvo.getPicture());
		sqlappen(sql, "commodity.description", qvo.getDescription());
		return sql.toString();
	}
	
	@Override
	public List<CommodityVO> list(CommodityQuery qvo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(getcondition(qvo));
		return getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<CommodityVO>(CommodityVO.class));
	}
	
	@Override
	public void remove(BaseMap<String, Object> wheremap) {
		baseremove(wheremap, "pay_commodity");
	}
	
	@Override
	public CommodityVO get(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(SELECT).append(QUERY_TABLE_COLUMN).append(FROM).append(QUERY_TABLE_NAME);
		sql.append(WHERE).append("1=1");
		sql.append(AND).append("id='" + id + "'");
		List<CommodityVO> list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper<CommodityVO>(CommodityVO.class));
		return QvoConditionUtil.checkList(list) ? list.get(0) : null;
	}
}
