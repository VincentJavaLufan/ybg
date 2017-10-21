package com.ybg.oss.domian;
import java.io.Serializable;

/** 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26 */
public class SysOssEntity implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	private Long				id;
	private String				url;
	private String				createdate;
	
	/** 设置： */
	public void setId(Long id) {
		this.id = id;
	}
	
	/** 获取： */
	public Long getId() {
		return id;
	}
	
	/** 设置：URL地址 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/** 获取：URL地址 */
	public String getUrl() {
		return url;
	}
	
	/** 获取：创建时间 */
	public String getCreatedate() {
		return createdate;
	}
	
	/** 设置：创建时间 */
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
}
