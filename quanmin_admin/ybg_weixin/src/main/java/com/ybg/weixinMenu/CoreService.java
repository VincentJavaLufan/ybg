package com.ybg.weixinMenu;
import java.util.Date;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ybg.weixinMenu.resp.TextMessage;


/** 核心服务类
 * 
 * @author liufeng
 * @date 2013-05-20 */
public class CoreService {
	
	/** 处理微信发来的请求
	 * 
	 * @param request
	 * @return */
	public static String processRequest(HttpServletRequest request, HttpServletResponse response) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			request.setAttribute(MessageUtil.WX_DATA_SIGN, requestMap);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "感谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					respContent = "该功能正在努力研发中，谢谢关注！";
					if (1 != 1) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/wx.do?action=subscribe");
						try {
							dispatcher.forward(request, response);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");
					// 关注
					if (eventKey.equals("21")) {
						respContent = "该功能正在研发中，谢谢关注！";
					} // 取消关注
					else if (eventKey.equals("22")) {
						respContent = "该功能正在研发中，谢谢关注！";
					}
				}
			}
			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}