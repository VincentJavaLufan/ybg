package com.egzosn.pay.seller.domain;
import com.egzosn.pay.ali.api.AliPayConfigStorage;
import com.egzosn.pay.ali.api.AliPayService;
import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.BasePayType;
import com.egzosn.pay.common.bean.TransactionType;
import com.egzosn.pay.seller.service.handler.FuiouPayMessageHandler;
import com.egzosn.pay.fuiou.api.FuiouPayConfigStorage;
import com.egzosn.pay.fuiou.api.FuiouPayService;
import com.egzosn.pay.fuiou.bean.FuiouTransactionType;
import com.egzosn.pay.fuiou.api.FuiouPayConfigStorage;
import com.egzosn.pay.fuiou.api.FuiouPayService;
import com.egzosn.pay.wx.api.WxPayConfigStorage;
import com.egzosn.pay.wx.api.WxPayService;
import com.egzosn.pay.wx.bean.WxTransactionType;
import com.egzosn.pay.wx.youdian.api.WxYouDianPayConfigStorage;
import com.egzosn.pay.wx.youdian.api.WxYouDianPayService;
import com.egzosn.pay.wx.youdian.bean.YoudianTransactionType;
import com.ybg.base.jdbc.util.QvoConditionUtil;

/** 支付类型
 *
 * @author egan email egzosn@gmail.com date 2016/11/20 0:30 */
public enum PayType implements BasePayType {
	/*** 阿里支付 **/
	aliPay {
		
		/** @see com.egzosn.pay.ali.api.AliPayService 17年更新的版本,旧版本请自行切换
		 * @param apyAccount
		 * @return */
		@Override
		public PayService getPayService(SellerDO apyAccount) {
			AliPayConfigStorage aliPayConfigStorage = new AliPayConfigStorage();
			aliPayConfigStorage.setPid(apyAccount.getPartner());
			aliPayConfigStorage.setAppId(apyAccount.getAppid());
			aliPayConfigStorage.setKeyPublic(apyAccount.getPublickey());
			aliPayConfigStorage.setKeyPrivate(apyAccount.getPrivatekey());
			aliPayConfigStorage.setNotifyUrl(apyAccount.getNotifyurl());
			aliPayConfigStorage.setReturnUrl(apyAccount.getReturnurl());
			aliPayConfigStorage.setSignType(apyAccount.getSigntype());
			aliPayConfigStorage.setSeller(apyAccount.getSeller());
			aliPayConfigStorage.setPayType(apyAccount.getPaytype().toString());
			aliPayConfigStorage.setMsgType(apyAccount.getMsgtype());
			aliPayConfigStorage.setInputCharset(apyAccount.getInputcharset());
			aliPayConfigStorage.setTest(QvoConditionUtil.checkInteger(apyAccount.getTest()));
			return new AliPayService(aliPayConfigStorage);
		}
		
		@Override
		public TransactionType getTransactionType(String transactionType) {
			// com.egzosn.pay.ali.before.bean.AliTransactionType 17年更新的版本,旧版本请自行切换
			// AliTransactionType 17年更新的版本,旧版本请自行切换
			return AliTransactionType.valueOf(transactionType);
		}
	},
	/*** 微信支付 **/
	wxPay {
		
		@Override
		public PayService getPayService(SellerDO apyAccount) {
			WxPayConfigStorage wxPayConfigStorage = new WxPayConfigStorage();
			wxPayConfigStorage.setMchId(apyAccount.getPartner());
			wxPayConfigStorage.setKeyPublic(apyAccount.getPublickey());
			wxPayConfigStorage.setAppid(apyAccount.getAppid());
			wxPayConfigStorage.setKeyPrivate(apyAccount.getPrivatekey());
			wxPayConfigStorage.setNotifyUrl(apyAccount.getNotifyurl());
			wxPayConfigStorage.setReturnUrl(apyAccount.getReturnurl());
			wxPayConfigStorage.setSignType(apyAccount.getSigntype());
			wxPayConfigStorage.setPayType(apyAccount.getPaytype().toString());
			wxPayConfigStorage.setMsgType(apyAccount.getMsgtype());
			wxPayConfigStorage.setInputCharset(apyAccount.getInputcharset());
			wxPayConfigStorage.setTest(QvoConditionUtil.checkInteger(apyAccount.getTest()));
			return new WxPayService(wxPayConfigStorage);
		}
		
		/** 根据支付类型获取交易类型
		 * 
		 * @param transactionType
		 *            类型值
		 * @see WxTransactionType
		 * @return */
		@Override
		public TransactionType getTransactionType(String transactionType) {
			return WxTransactionType.valueOf(transactionType);
		}
	},
	/*** 有店支付 **/
	youdianPay {
		
		@Override
		public PayService getPayService(SellerDO apyAccount) {
			// TODO 2017/1/23 14:12 author: egan 集群的话,友店可能会有bug。暂未测试集群环境
			WxYouDianPayConfigStorage wxPayConfigStorage = new WxYouDianPayConfigStorage();
			wxPayConfigStorage.setKeyPrivate(apyAccount.getPrivatekey());
			wxPayConfigStorage.setKeyPublic(apyAccount.getPublickey());
			// wxPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
			// wxPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
			wxPayConfigStorage.setSignType(apyAccount.getSigntype());
			wxPayConfigStorage.setPayType(apyAccount.getPaytype().toString());
			wxPayConfigStorage.setMsgType(apyAccount.getMsgtype());
			wxPayConfigStorage.setSeller(apyAccount.getSeller());
			wxPayConfigStorage.setInputCharset(apyAccount.getInputcharset());
			wxPayConfigStorage.setTest(QvoConditionUtil.checkInteger(apyAccount.getTest()));
			return new WxYouDianPayService(wxPayConfigStorage);
		}
		
		/** 根据支付类型获取交易类型
		 * 
		 * @param transactionType
		 *            类型值
		 * @see YoudianTransactionType
		 * @return */
		@Override
		public TransactionType getTransactionType(String transactionType) {
			return YoudianTransactionType.valueOf(transactionType);
		}
	},
	/*** 富有支付 **/
	fuiou {
		
		@Override
		public PayService getPayService(SellerDO apyAccount) {
			FuiouPayConfigStorage fuiouPayConfigStorage = new FuiouPayConfigStorage();
			fuiouPayConfigStorage.setKeyPublic(apyAccount.getPublickey());
			fuiouPayConfigStorage.setKeyPrivate(apyAccount.getPrivatekey());
			fuiouPayConfigStorage.setNotifyUrl(apyAccount.getNotifyurl());
			fuiouPayConfigStorage.setReturnUrl(apyAccount.getReturnurl());
			fuiouPayConfigStorage.setSignType(apyAccount.getSigntype());
			fuiouPayConfigStorage.setPayType(apyAccount.getPaytype().toString());
			fuiouPayConfigStorage.setMsgType(apyAccount.getMsgtype());
			fuiouPayConfigStorage.setInputCharset(apyAccount.getInputcharset());
			fuiouPayConfigStorage.setTest(QvoConditionUtil.checkInteger(apyAccount.getTest()));
			return new FuiouPayService(fuiouPayConfigStorage);
		}
		
		@Override
		public TransactionType getTransactionType(String transactionType) {
			return FuiouTransactionType.valueOf(transactionType);
		}
	};
	
	public abstract PayService getPayService(SellerDO apyAccount);
}
