package com.ybg.api.network;
import java.util.List;
import javax.print.attribute.standard.Media;
import com.google.gson.JsonObject;
import com.ybg.api.domain.WeixinJson;
import net.sf.json.JSONObject;

/** 都是些根据 token 访问的接口 并不是最全 **/
public interface WeixinNW {
	
	/** 获取微信服务器IP地址 **/
	WeixinJson getWeixinIPaddress();
	
	////////////
	/** 自定义菜单创建接口 **/
	WeixinJson menu_create(JSONObject menu, String token);
	
	/** 自定义菜单查询接口 **/
	WeixinJson menu_get(String token);
	
	/** 自定义菜单删除接口 **/
	WeixinJson menu_delete(String token);
	
	/////////////////////
	/** 用户标签管理 创建标签 **/
	WeixinJson tags_create(String token, String name);
	
	/** 用户标签管理 获取公众号已创建的标签 **/
	WeixinJson tags_get(String token);
	
	/** 用户标签管理 编辑标签 **/
	WeixinJson tags_update(String token, int id, String name);
	
	/** 用户标签管理 删除标签 <br>
	 * 请注意，当某个标签下的粉丝超过10w时，后台不可直接删除标签。此时，开发者可以对该标签下的openid列表，先进行取消标签的操作，直到粉丝数不超过10w后，才可直接删除该标签。 **/
	WeixinJson tags_delete(String token, int id);
	
	/** 用户标签管理 获取标签下粉丝列表 **/
	WeixinJson tag_user_get(String token, int id);
	
	/** 用户管理 批量为用户打标签<br>
	 * 标签功能目前支持公众号为用户打上最多20个标签。 **/
	WeixinJson tag_members_batchtagging(String token, int tagid, List<String> openid_list);
	
	/** 用户管理 批量为用户取消标签 **/
	WeixinJson tag_members_batchuntagging(String token, int tagid, List<String> openid_list);
	
	/** 用户管理 获取用户身上的标签列表 **/
	WeixinJson tag_getidlist(String token, String openid);
	
	/** 设置用户备注名<br>
	 * 发者可以通过该接口对指定用户设置备注名，该接口暂时开放给微信认证的服务号。 接口调用请求说明 **/
	WeixinJson user_info_updateremark(String token, String openid, String remark);
	
	/** 获取用户基本信息 **/
	WeixinJson user_info(String token, String openid);
	
	/*** 批量获取用户基本信息 **/
	WeixinJson user_infobatchget(String token, List<String> openids);
	
	/** 获取用户列表
	 * 
	 * @param token
	 * @param next_openid
	 *            第一个拉取的OPENID，不填默认从头开始拉取
	 * @return */
	WeixinJson user_get(String token, String next_openid);
	
	/** 黑名单管理 获取公众号的黑名单列表<br>
	 * 当公众号黑名单列表数量超过 10000 时，可通过填写 next_openid 的值，从而多次拉取列表的方式来满足需求。 具体而言，就是在调用接口时，将上一次调用得到的返回中的 next_openid 的值，作为下一次调用中的 next_openid 值。 **/
	WeixinJson tags_members_getblacklist(String token, String next_openid);
	
	/** 拉黑用户 众号可通过该接口来拉黑一批用户，黑名单列表由一串 OpenID （加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
	 * 
	 * @param access_token
	 * @param openid_list
	 *            需要拉入黑名单的用户的openid，一次拉黑最多允许20个
	 * @return */
	WeixinJson tags_members_batchblacklist(String access_token, List<String> openid_list);
	
	/** 取消拉黑用户 * @param access_token
	 * 
	 * @param openid_list
	 *            需要拉入黑名单的用户的openid，一次拉黑最多允许20个
	 * @return ***/
	WeixinJson tags_members_batchunblacklist(String access_token, List<String> openid_list);
	
	/** 用户分析数据接口
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getusersummary(String access_token, String begin_date, String end_date);
	
	/** 获取累计用户数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getusercumulate(String access_token, String begin_date, String end_date);
	
	/** 获取图文群发每日数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getarticlesummary(String access_token, String begin_date, String end_date);
	
	/** 获取图文群发总数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getarticletotal(String access_token, String begin_date, String end_date);
	
	/** 获取图文统计数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getuserread(String access_token, String begin_date, String end_date);
	
	/** 获取图文统计分时数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getuserreadhour(String access_token, String begin_date, String end_date);
	
	/** 获取图文分享转发数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getusershare(String access_token, String begin_date, String end_date);
	
	/** @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return 获取图文分享转发分时数据 **/
	WeixinJson datacube_getusersharehour(String access_token, String begin_date, String end_date);
	
	/** 获取消息发送概况数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getupstreammsg(String access_token, String begin_date, String end_date);
	
	/** 获取消息分送分时数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getupstreammsghour(String access_token, String begin_date, String end_date);
	
	/** 获取消息发送周数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getupstreammsgweek(String access_token, String begin_date, String end_date);
	
	/** 获取消息发送月数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getupstreammsgmonth(String access_token, String begin_date, String end_date);
	
	/** 获取消息发送分布数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getupstreammsgdist(String access_token, String begin_date, String end_date);
	
	/** 获取消息发送分布周数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getupstreammsgdistweek(String access_token, String begin_date, String end_date);
	
	/** 获取消息发送分布月数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getupstreammsgdistmonth(String access_token, String begin_date, String end_date);
	
	/** 获取接口分析数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getinterfacesummary(String access_token, String begin_date, String end_date);
	
	/** 获取接口分析分时数据
	 * 
	 * @param begin_date
	 *            获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错
	 * 
	 * @param end_date
	 *            获取数据的结束日期，end_date允许设置的最大值为昨日
	 * 
	 * @return **/
	WeixinJson datacube_getinterfacesummaryhour(String access_token, String begin_date, String end_date);
	
	// https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140547
	/** 客服帐号管理- 添加客服帐号 */
	WeixinJson kfaccount_add(String token, String kf_account, String nickname, String password);
	
	/** 客服帐号管理- 修改客服帐号 */
	WeixinJson kfaccount_update(String token, String kf_account, String nickname, String password);
	
	/** 客服帐号管理- 删除客服帐号 */
	WeixinJson kfaccount_del(String token, String kf_account, String nickname, String password);
	
	/** 客服帐号管理-设置客服帐号的头像 <br>
	 * 开发者可调用本接口来上传图片作为客服人员的头像，头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果。该接口调用请求如下： 调用示例：使用curl命令，用FORM表单方式上传一个多媒体文件，curl命令的具体用法请自行了解 **/
	WeixinJson kfaccount_uploadheadimg(String token, String kf_account, String imgpath);
	
	/** 获取所有客服账号 **/
	WeixinJson kfaccount_getkflist(String token);
	
	/** 客服接口-发消息 **/
	WeixinJson message_custom_send(String token, String touser, String msgtype, JSONObject msgjson);
	
	/** 客服输入状态 **/
	WeixinJson message_custom_typing(String token, String touser, String command);
	
	// https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1481187827_i0l21
	/** 发送消息-群发接口和原创校验<br>
	 * 上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】 <br>
	 * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。 **/
	// XXX 参数是不正确的
	WeixinJson media_uploadimg(String token, Media media);
	
	/** 上传图文消息素材 { "articles": [ { "thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p", "author":"xxx", "title":"Happy Day", "content_source_url":"www.qq.com", "content":"content", "digest":"digest", "show_cover_pic":1 }, { "thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p", "author":"xxx", "title":"Happy Day", "content_source_url":"www.qq.com", "content":"content", "digest":"digest", "show_cover_pic":0 } ] } **/
	// XXX 参数是不正确的
	WeixinJson media_uploadnews(String token, JsonObject articles);
	
	/** 根据标签进行群发 群发接口新增 clientmsgid 参数，开发者调用群发接口时可以主动设置 clientmsgid 参数，避免重复推送。 **/
	// XXX 参数是不正确的
	WeixinJson message_mass_sendall(String token, String clientmsgid);
	
	/** 根据OpenID列表群发群发接口新增 clientmsgid 参数，开发者调用群发接口时可以主动设置 clientmsgid 参数，避免重复推送。 ***/
	// XXX 参数是不正确的
	WeixinJson message_mass_send(String token, List<String> touser, String msgtype, String clientmsgid);
	
	/** 删除群发
	 * 
	 * @param msg_id
	 *            发送出去的消息ID 必须
	 * @param article_idx
	 *            要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章 否 **/
	WeixinJson message_mass_delete(String token, int msg_id, int article_idx);
	
	/** 预览接口 **/
	WeixinJson message_mass_preview(String token, int touser, String msgtype, JSONObject media);
	
	/** 查询群发消息发送状态 */
	WeixinJson message_mass_get(String token, int touser, String msg_id);
	
	// 模板消息接口 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277
	/** 设置所属行业
	 * 
	 * @param industry_id1
	 *            公众号模板消息所属行业编号
	 * @param industry_id2
	 *            公众号模板消息所属行业编号 **/
	WeixinJson template_api_set_industry(String token, String industry_id1, String industry_id2);
	
	/** 获取设置的行业信息 **/
	WeixinJson template_api_get_industry(String token);
	
	/** 获得模板ID
	 * 
	 * @param template_id_short
	 *            模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式 **/
	WeixinJson template_api_add_template(String token, String template_id_short);
	
	/** 获取模板列表 **/
	WeixinJson template_get_all_private_template(String token);
	
	/** 删除模板 **/
	WeixinJson template_del_private_template(String token, String template_id);
	
	/** 发送模板消息 */
	WeixinJson template_send(String token, String template_id, String touser, String url, String miniprogram, String appid, String pagepath, String data, String color);
	// 一次性订阅消息 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1500374289_66bvB
	
	// 获取公众号的自动回复规则 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751299
	/** 获取公众号的自动回复规则 **/
	WeixinJson get_current_autoreply_info(String token);
}
