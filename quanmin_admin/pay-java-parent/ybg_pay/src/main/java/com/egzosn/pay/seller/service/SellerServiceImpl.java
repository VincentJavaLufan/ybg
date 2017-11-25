package com.egzosn.pay.seller.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Repository;
import com.egzosn.pay.seller.dao.SellerDao;
import com.egzosn.pay.seller.domain.SellerDO;
import com.egzosn.pay.seller.domain.SellerVO;
import com.egzosn.pay.seller.qvo.SellerQuery;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/** @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0 */
@Repository
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	SellerDao							accountDao;
	@Resource
	private AutowireCapableBeanFactory	spring;
	
	@Override
	public SellerVO get(String id) {
		return accountDao.get(id);
	}
	
	@Override
	public Page list(Page page, SellerQuery qvo) throws Exception {
		return accountDao.list(page, qvo);
	}
	
	@Override
	public List<SellerVO> list(SellerQuery qvo) throws Exception {
		return accountDao.list(qvo);
	}
	
	@Override
	public void update(BaseMap<String, Object> updateMap, BaseMap<String, Object> whereMap) {
		accountDao.update(updateMap, whereMap);
	}
	
	@Override
	public void remove(BaseMap<String, Object> conditionmap) {
		accountDao.remove(conditionmap);
	}
	
	@Override
	public SellerVO create(SellerVO bean) throws Exception {
		return accountDao.create(bean);
	}
	
	// @PostConstruct
	// public void init() {
	// System.out.println("初始化 商户账户信息..");
	// try {
	// List<ApyAccountVO> apylist = list(new ApyAccountQuery());
	// for(Stri)
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	// }
	//
	// }
	/** 缓存 */
	private final static Map<String, PayResponse> PAYRESPONSES = new HashMap<String, PayResponse>();
	
	/** 获取支付响应
	 * 
	 * @param id
	 *            账户id
	 * @return */
	@Override
	public PayResponse getPayResponse(String id) {
		PayResponse payResponse = PAYRESPONSES.get(id);
		if (payResponse == null) {
			SellerDO apyAccount = accountDao.get(id);
			if (apyAccount == null) {
				throw new IllegalArgumentException("无法查询");
			}
			payResponse = new PayResponse();
			spring.autowireBean(payResponse);
			payResponse.init(apyAccount);
			PAYRESPONSES.put(id, payResponse);
			// 查询
		}
		return payResponse;
	}
}
