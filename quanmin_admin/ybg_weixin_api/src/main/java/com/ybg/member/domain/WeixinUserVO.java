package com.ybg.member.domain;

import java.util.Arrays;

/** @author Deament
 * @date 2017/1/1 */
public class WeixinUserVO extends WeixinUserDO {

	@Override
	public String toString() {
		return "WeixinUserVO [subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex + ", city=" + city + ", country=" + country + ", province=" + province + ", language=" + language + ", headimgurl=" + headimgurl + ", subscribe_time=" + subscribe_time + ", unionid=" + unionid + ", remark=" + remark + ", groupid=" + groupid + ", tagid_list=" + Arrays.toString(tagid_list) + "]";
	}
	
}
