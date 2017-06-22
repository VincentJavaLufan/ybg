//package com.ybg.weixin.util.http;
//import java.io.StringReader;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.SortedMap;
//import java.util.TreeMap;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.input.SAXBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.ModelAndView;
//import org.xml.sax.InputSource;
//import com.ybg.weixin.util.RequestHandler;
//
//
///** 微信订单工具类 **/
//public class WeixinOrderUtil {
//	
//	@Autowired
//	static WeixinAppInfo weixinAppInfo;
//	
//	// 私有方法
//	public static WeixinPayQuery getpackage(String Url, WeixinPayQuery w) {
//		String appid = weixinAppInfo.getAppid();// 公众账号ID
//		String mch_id = weixinAppInfo.getMchid();// 商户号
//		// String out_trade_no;// 微信订单号
//		String nonce_str = getNonceStr();// 随机字符串
//		// String sign;
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", appid);
//		packageParams.put("mch_id", mch_id);
//		packageParams.put("out_trade_no", w.getOut_trade_no());
//		packageParams.put("nonce_str", nonce_str);
//		// packageParams.put("sign", value);
//		RequestHandler reqHandler = new RequestHandler(null, null);
//		reqHandler.init(weixinAppInfo.getAppid(), weixinAppInfo.getSecret(), weixinAppInfo.getPartnerkey());
//		String sign = reqHandler.createSign(packageParams);
//		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<out_trade_no>" + w.getOut_trade_no() + "</out_trade_no>" + "<sign>" + sign + "</sign>" + "</xml>";
//		String createOrderURL = Url;
//		Map<?, ?> map = GetWxOrderno.gettrade_state(createOrderURL, xml);
//		if (map.get("return_code") != null && map.get("return_code").equals("SUCCESS") && map.get("result_code") != null && map.get("result_code").equals("SUCCESS")) {
//			w.setAppid(appid);
//			w.setMch_id(mch_id);
//			w.setNonce_str(nonce_str);
//			w.setSign(sign);
//			w.setTrade_state(map.get("trade_state").toString());
//		}
//		return w;
//	}
//	
//	public static Map<String, String> parseXmlToList2(String xml) {
//		Map<String, String> retMap = new HashMap<String, String>();
//		try {
//			StringReader read = new StringReader(xml);
//			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
//			InputSource source = new InputSource(read);
//			// 创建一个新的SAXBuilder
//			SAXBuilder sb = new SAXBuilder();
//			// 通过输入源构造一个Document
//			Document doc = (Document) sb.build(source);
//			Element root = doc.getRootElement();// 指向根节点
//			List<Element> es = root.getChildren();
//			if (es != null && es.size() != 0) {
//				for (Element element : es) {
//					retMap.put(element.getName(), element.getValue());
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return retMap;
//	}
//	
//	/** 微信回调通知 作废 ***/
//	public ModelAndView notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// 把如下代码贴到的你的处理回调的servlet 或者.do 中即可明白回调操作
//		// System.out.print("微信支付回调数据开始");
//		// 示例报文
//		// String xml =
//		// "<xml><appid><![CDATA[wxb4dc385f953b356e]]></appid><bank_type><![CDATA[CCB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1228442802]]></mch_id><nonce_str><![CDATA[1002477130]]></nonce_str><openid><![CDATA[o-HREuJzRr3moMvv990VdfnQ8x4k]]></openid><out_trade_no><![CDATA[1000000000051249]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[1269E03E43F2B8C388A414EDAE185CEE]]></sign><time_end><![CDATA[20150324100405]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1009530574201503240036299496]]></transaction_id></xml>";
//		String inputLine;
//		String notityXml = "";
//		String resXml = "";
//		try {
//			while ((inputLine = request.getReader().readLine()) != null) {
//				notityXml += inputLine;
//			}
//			request.getReader().close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// System.out.println("接收到的报文：" + notityXml);
//		// Map m = parseXmlToList2(notityXml);
//		WxPayResult wpr = new WxPayResult();
//		// wpr.setAppid(m.get("appid").toString());
//		// wpr.setBankType(m.get("bank_type").toString());
//		// wpr.setCashFee(m.get("cash_fee").toString());
//		// wpr.setFeeType(m.get("fee_type").toString());
//		// wpr.setIsSubscribe(m.get("is_subscribe").toString());
//		// wpr.setMchId(m.get("mch_id").toString());
//		// wpr.setNonceStr(m.get("nonce_str").toString());
//		// wpr.setOpenid(m.get("openid").toString());
//		// wpr.setOutTradeNo(m.get("out_trade_no").toString());
//		// wpr.setResultCode(m.get("result_code").toString());
//		// wpr.setReturnCode(m.get("return_code").toString());
//		// wpr.setSign(m.get("sign").toString());
//		// wpr.setTimeEnd(m.get("time_end").toString());
//		// wpr.setTotalFee(m.get("total_fee").toString());
//		// wpr.setTradeType(m.get("trade_type").toString());
//		// wpr.setTransactionId(m.get("transaction_id").toString());
//		wpr.setAppid(ServletUtil.getStringParamDefaultBlank(request, "appid"));
//		wpr.setBankType(ServletUtil.getStringParamDefaultBlank(request, "bank_type"));
//		wpr.setCashFee(ServletUtil.getStringParamDefaultBlank(request, "cash_fee"));
//		wpr.setFeeType(ServletUtil.getStringParamDefaultBlank(request, "fee_type"));
//		wpr.setIsSubscribe(ServletUtil.getStringParamDefaultBlank(request, "is_subscribe"));
//		wpr.setMchId(ServletUtil.getStringParamDefaultBlank(request, "mch_id"));
//		wpr.setNonceStr(ServletUtil.getStringParamDefaultBlank(request, "nonce_str"));
//		wpr.setOpenid(ServletUtil.getStringParamDefaultBlank(request, "openid"));
//		wpr.setOutTradeNo(ServletUtil.getStringParamDefaultBlank(request, "out_trade_no"));
//		wpr.setResultCode(ServletUtil.getStringParamDefaultBlank(request, "result_code"));
//		wpr.setReturnCode(ServletUtil.getStringParamDefaultBlank(request, "return_code"));
//		wpr.setSign(ServletUtil.getStringParamDefaultBlank(request, "sign"));
//		wpr.setTimeEnd(ServletUtil.getStringParamDefaultBlank(request, "time_end"));
//		wpr.setTotalFee(ServletUtil.getStringParamDefaultBlank(request, "total_fee"));
//		wpr.setTradeType(ServletUtil.getStringParamDefaultBlank(request, "trade_type"));
//		wpr.setTransactionId(ServletUtil.getStringParamDefaultBlank(request, "transaction_id"));
//		if ("SUCCESS".equals(wpr.getResultCode())) {
//			// 支付成功
//			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//		}
//		else {
//			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
//		}
//		// System.out.println("微信支付回调数据结束");
//		// System.out.println(resXml);
//		// BufferedOutputStream out = new BufferedOutputStream(
//		// response.getOutputStream());
//		// out.write(resXml.getBytes());
//		// out.flush();
//		// out.close();
//		Map<String, Object> map = new HashMap<String, Object>();
//		return new ModelAndView("/weixinpay/weixinpay", map);
//	}
//	
//	/** 获取随机字符串
//	 * 
//	 * @return */
//	private static String getNonceStr() {
//		// 随机数
//		String currTime = TenpayUtil.getCurrTime();
//		// 8位日期
//		String strTime = currTime.substring(8, currTime.length());
//		// 四位随机数
//		String strRandom = TenpayUtil.buildRandom(4) + "";
//		// 10位序列号,可以自行调整。
//		return strTime + strRandom;
//	}
//}
