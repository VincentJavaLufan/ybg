package com.egzosn.pay.seller.domain;

import com.ybg.base.jdbc.util.QvoConditionUtil;

/**
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
public class SellerVO extends SellerDO {

	public boolean getTest2() {
		return QvoConditionUtil.checkInteger(getTest());
	}

}
