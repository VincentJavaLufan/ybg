package com.egzosn.pay.payorder.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.util.MatrixToImageWriter;
import com.egzosn.pay.wx.bean.WxTransactionType;

/**
 * @author https://gitee.com/YYDeament/88ybg
 * @date
 * @version v1.0
 */
@Controller
@RequestMapping("/pay/huiyuan/")
public class PayorderController {
	@RequestMapping("index.do")
	public String index() {
		return "/pay/huiyuan/huiyuan";
	}

	/** 微信方式开通会员 **/
	@ResponseBody
	@RequestMapping(value = "toWxQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
	public byte[] toWxQrPay(Integer wxPayId, BigDecimal price, HttpServletRequest request) throws IOException {
		// 获取对应的支付账户操作工具（可根据账户id）
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 这里为需要生成二维码的地址
		StringBuffer url = request.getRequestURL();
		url = new StringBuffer(url.substring(0, url.lastIndexOf(request.getRequestURI())));
		url.append("/toWxPay.html?");
		if (null != wxPayId) {
			url.append("wxPayId=").append(wxPayId).append("&");
		}

		url.append("price=").append(price);

		ImageIO.write(MatrixToImageWriter.writeInfoToJpgBuff(url.toString()), "JPEG", baos);
		return baos.toByteArray();
	}

	/** 支付宝方式开通会员 **/
	@ResponseBody
	@RequestMapping(value = "toAliQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
	public byte[] toAliQrPay( HttpServletRequest request) throws IOException {
		Integer aliPayId=1;int price=10;
		
		// 获取对应的支付账户操作工具（可根据账户id）
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 这里为需要生成二维码的地址
		StringBuffer url = request.getRequestURL();
		url = new StringBuffer(url.substring(0, url.lastIndexOf(request.getRequestURI())));
		url.append("/toAliPay.html?");

		if (null != aliPayId) {
			url.append("aliPayId=").append(aliPayId).append("&");
		}
		url.append("price=").append(price);

		ImageIO.write(MatrixToImageWriter.writeInfoToJpgBuff(url.toString()), "JPEG", baos);
		return baos.toByteArray();
	}

	/**
	 * 支付宝与微信平台的判断 并进行支付的转跳
	 * 
	 * @param wxPayId
	 *            微信账户id
	 * @param aliPayId
	 *            支付宝id
	 * @param price
	 *            金额
	 * @return 支付宝与微信平台的判断
	 */
	@RequestMapping(value = "toWxPay.html", produces = "text/html;charset=UTF-8")
	public String toWxPay(Integer wxPayId, BigDecimal price, HttpServletRequest request) throws IOException {
		StringBuilder html = new StringBuilder();

		// 这里为WAP支付的地址，根据需求自行修改
		StringBuffer url = request.getRequestURL();
		url = new StringBuffer(url.substring(0, url.lastIndexOf(request.getRequestURI())));
		url.append("/toPay.html");

		html.append("<html><head></head><body><script type=\"text/javascript\"> ");
		// html.append("\nalert('111');\n");

		if (null != wxPayId) {
			html.append("if(isWxPay()){\n");
			html.append("window.location='");
			// 这里使用H5支付，公众号支付是否可以？请开发者自行尝试
			html.append(url.toString()).append("?payId=").append(wxPayId).append("&transactionType=").append(WxTransactionType.MWEB.getType()).append("&price=").append(price);
			html.append("';\n }else\n");
		}

		html.append("{\n alert('请使用微信App扫码'+window.navigator.userAgent.toLowerCase());\n }");
		// 判断是否为微信
		html.append("function isWxPay(){ \n" + " var ua = window.navigator.userAgent.toLowerCase();\n" + " if(ua.match(/MicroMessenger/i) == 'micromessenger'){\n" + " return true;\n" + " }\n" + " return false;\n" + "} \n");

		return html.toString();
	}

	/**
	 * 支付宝与微信平台的判断 并进行支付的转跳
	 * 
	 * @param wxPayId
	 *            微信账户id
	 * @param aliPayId
	 *            支付宝id
	 * @param price
	 *            金额
	 * @return 支付宝与微信平台的判断
	 */
	@RequestMapping(value = "toAliPay.html", produces = "text/html;charset=UTF-8")
	public String toAliPay(Integer aliPayId, BigDecimal price, HttpServletRequest request) throws IOException {
		StringBuilder html = new StringBuilder();

		// 这里为WAP支付的地址，根据需求自行修改
		StringBuffer url = request.getRequestURL();
		url = new StringBuffer(url.substring(0, url.lastIndexOf(request.getRequestURI())));
		url.append("/toPay.html");

		html.append("<html><head></head><body><script type=\"text/javascript\"> ");
		// html.append("\nalert('111');\n");

		if (null != aliPayId) {
			html.append("if(isAliPay()){\n");
			html.append("window.location='");
			html.append(url).append("?payId=").append(aliPayId).append("&transactionType=").append(AliTransactionType.WAP.getType()).append("&price=").append(price);
			html.append("';\n } else");
		}
		html.append("{\n alert('请使用支付宝App扫码'+window.navigator.userAgent.toLowerCase());\n }");
		// 判断是否为微信
		html.append("function isWxPay(){ \n" + " var ua = window.navigator.userAgent.toLowerCase();\n" + " if(ua.match(/MicroMessenger/i) == 'micromessenger'){\n" + " return true;\n" + " }\n" + " return false;\n" + "} \n");
		// 判断是否为支付宝
		html.append("function isAliPay(){\n" + " var ua = window.navigator.userAgent.toLowerCase();\n" + " if(ua.match(/AlipayClient/i) =='alipayclient'){\n" + "  return true;\n" + " }\n" + "  return false;\n" + "}</script> <body></html>");
		return html.toString();
	}

}
