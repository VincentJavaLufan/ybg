package com.ybg.oss;
/** 常量
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月15日 下午1:23:52 */
public class Constant {
	
	/** 云服务商 */
	public enum CloudService {
		/** 七牛云 */
		QINIU(1),
		/** 阿里云 */
		ALIYUN(2),
		/** 腾讯云 */
		QCLOUD(3);
		
		private int value;
		
		private CloudService(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
}
