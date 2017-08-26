package com.ybg.api.domain.menu;
/** 文本消息
 * 
 * @author liufeng
 * @date 2013-05-19 */
public class RequestBaseMessage extends BaseMessage {
	
	// 回复的消息内容
	private String Content;
	
	public String getContent() {
		return Content;
	}
	
	public void setContent(String content) {
		Content = content;
	}
	
	/** 文本错误信息构造方法
	 * 
	 * @param FromUserName
	 * @param ToUserName
	 * @return */
	public static RequestBaseMessage genErrorTextMessage(String fromUserName, String toUserName) {
		RequestBaseMessage textMessage = new RequestBaseMessage();
		textMessage.setFromUserName(fromUserName);
		textMessage.setToUserName(toUserName);
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("服务器繁忙中...请稍候再试！谢谢！");
		textMessage.setFuncFlag(0);
		return textMessage;
	}
	
	/** 文本信息构造方法
	 * 
	 * @param FromUserName
	 * @param ToUserName
	 * @return */
	public static RequestBaseMessage genSimpleTextMessage(String fromUserName, String toUserName, String content) {
		RequestBaseMessage textMessage = new RequestBaseMessage();
		textMessage.setFromUserName(fromUserName);
		textMessage.setToUserName(toUserName);
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(content);
		textMessage.setFuncFlag(0);
		return textMessage;
	}
}