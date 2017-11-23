package com.ybg;

import com.egzosn.pay.common.util.sign.encrypt.Base64;
import com.egzosn.pay.common.util.sign.encrypt.RSA;
import com.egzosn.pay.common.util.sign.encrypt.RSA2;

public class Test {
	public static void main(String[] args) {
		String sign = "oq2fXWaOlCtEct3iO3NAvMqsw%2FdpUdtYoouo4ZAy8sPil6JB5lHPGAZ%2FoflmKbWcKVJ6EOWBoHKHdgNG5n%2Fh%2FEMS5w9lqWXQR6Gs0ruYRtNFG5rXuI2N%2FPtnjN9PhF8TRwtyGxUe0nowRdaTHnv6UUax%2FR%2FQ7Z%2BI%2BZ4%2BT4q6fx2oMeN8T4%2BEwzXa5%2FKcliBWc%2Fi82JHa%2FQ0J1iUrBS3n6C3nVP77lBFBGPk46JRzfKQJ16ZzqqcmHlLpU4D4DSemp9uXZx7OZCgAjSwedItlAGvTm6a4gEZN4ZqljKZnlFZCuE%2BVTJa5gQC4UkKMT3R9k%2B5XSPxxJX6miVbDlXem9g%3D%3D";
		String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwvr0XzCHq34lfQTWpoyou6b29Mtv6cY07caUKdUqm1SY5kzhhhA0bMuaFepGxSIlETPGwsp9fuqHAaSzqOiyB4YHfPUODY2xsHqxDOhZerWm0ahbK+JYP7dsh73YLEOi1bANbfm5Zc7hmqyghCzgD4e1IIJVp+9nLuyW6+jkHRbgZZpxJ/Tb5mmOJd2zoQf/2uvAMRP3Hz4cZGRST4+aVGKd99ISHw4wTzbND4M4Zw3NRK8GqOZasR0ggsLxoUvcZ/eiHQtHPLtv0nBNvni/erKcW+YTgmrtA6L8IF7vB6EmFoXH3RyGoKuTRoslCozuP4xoHx5LAAnnhfEDn/2XJwIDAQAB";
		//RSA.verify 实际得到的参数
		String content2 = "app_id=2016080500169957&auth_app_id=2016080500169957&charset=UTF-8&method=alipay.trade.wap.pay.return&out_trade_no=c5258442d59a4b58ba49a331d0590b82&seller_id=2088102170002870&timestamp=2017-11-21 23:15:39&total_amount=10.00&trade_no=2017112121001004810200312390&version=1.0";
		//问号后面的参数
		String content = "total_amount=10.00&timestamp=2017-11-21+23%3A15%3A39&sign=oq2fXWaOlCtEct3iO3NAvMqsw%2FdpUdtYoouo4ZAy8sPil6JB5lHPGAZ%2FoflmKbWcKVJ6EOWBoHKHdgNG5n%2Fh%2FEMS5w9lqWXQR6Gs0ruYRtNFG5rXuI2N%2FPtnjN9PhF8TRwtyGxUe0nowRdaTHnv6UUax%2FR%2FQ7Z%2BI%2BZ4%2BT4q6fx2oMeN8T4%2BEwzXa5%2FKcliBWc%2Fi82JHa%2FQ0J1iUrBS3n6C3nVP77lBFBGPk46JRzfKQJ16ZzqqcmHlLpU4D4DSemp9uXZx7OZCgAjSwedItlAGvTm6a4gEZN4ZqljKZnlFZCuE%2BVTJa5gQC4UkKMT3R9k%2B5XSPxxJX6miVbDlXem9g%3D%3D&trade_no=2017112121001004810200312390&sign_type=RSA2&auth_app_id=2016080500169957&charset=UTF-8&seller_id=2088102170002870&method=alipay.trade.wap.pay.return&app_id=2016080500169957&out_trade_no=c5258442d59a4b58ba49a331d0590b82&version=1.0";
	//	String sign2=Base64.decode(sign);
		RSA.verify(content2, sign, publicKey, "SHA256WithRSA", "gbk");
//		RSA.signature.verify( Base64.decode(sign) );
	}
}
