package com.ybg.member.service;
import java.util.List;
import com.ybg.member.domain.WeixinUserVO;

/** @author Deament
 * @date 2017/1/1 */
public interface WeixinMemberService {
	
	/** 开发者可通过OpenID来获取用户基本信息。特别需要注意的是，<br>
	 * 如果开发者拥有多个移动应用、网站应用和公众帐号，可通过获取用户基本信息中的unionid来区分用户的唯一性，<br>
	 * 因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号，用户的unionid是唯一的。换句话说，同一用户，<br>
	 * 对同一个微信开放平台下的不同应用，unionid是相同的。 获取用户基本信息（包括UnionID机制）
	 * 
	 * 
	 * @param openid
	 * @return */
	WeixinUserVO get(String openid);
	
	/** 批量获取用户基本信息<br>
	 * 开发者可通过该接口来批量获取用户基本信息。最多支持一次拉取100条。<br>
	 * 返回说明 正常情况下，微信会返回下述JSON数据包给公众号（示例中为一次性拉取了2个openid的用户基本信息，第一个是已关注的，第二个是未关注的）：
	 * 
	 * 
	 * @param openid
	 * @return */
	List<WeixinUserVO> batchget(String openid);
}
