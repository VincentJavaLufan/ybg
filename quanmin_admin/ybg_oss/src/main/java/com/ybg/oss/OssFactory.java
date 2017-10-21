package com.ybg.oss;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.oss.service.SysConfigService;

/** 文件上传Factory
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 10:18 */
public final class OssFactory {
	
	private static SysConfigService sysConfigService;
	static {
		OssFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean(SysConfigService.class);
	}
	
	public static AbstractCloudStorageService build() {
		// 获取云存储配置信息
		CloudStorageConfig config = sysConfigService.getConfigObject(ConfigConstant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);
		if (config.getType() == OssConstant.CloudService.QINIU.getValue()) {
			return new QiniuCloudStorageService(config);
		}
		else if (config.getType() == OssConstant.CloudService.ALIYUN.getValue()) {
			return new AliyunCloudStorageService(config);
		}
		else if (config.getType() == OssConstant.CloudService.QCLOUD.getValue()) {
			return new QcloudCloudStorageService(config);
		}
		return null;
	}
}
