package com.ybg.quartz.schedule.domain;
import java.io.Serializable;

/** 定时执行日志
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月1日 下午10:26:18 */
public class ScheduleJobLogDO implements Serializable {
	
	private static final long	serialVersionUID	= 1L;
	/** 日志id */
	private Long				log_Id;
	/** 任务id */
	private Long				job_Id;
	/** spring bean名称 */
	private String				bean_Name;
	/** 方法名 */
	private String				method_Name;
	/** 参数 */
	private String				params;
	/** 任务状态 0：成功 1：失败 */
	private Integer				status;
	/** 失败信息 */
	private String				error;
	/** 耗时(单位：毫秒) */
	private Integer				times;
	/** 创建时间 */
	private String				create_Time;
	
	@Override
	public String toString() {
		return "ScheduleJobLogEntity [logId=" + log_Id + ", jobId=" + job_Id + ", beanName=" + bean_Name + ", methodName=" + method_Name + ", params=" + params + ", status=" + status + ", error=" + error + ", times=" + times + ", createTime=" + create_Time + "]";
	}
	
	public Long getLog_Id() {
		return log_Id;
	}
	
	public void setLog_Id(Long logId) {
		this.log_Id = logId;
	}
	
	public Long getJob_Id() {
		return job_Id;
	}
	
	public void setJob_Id(Long jobId) {
		this.job_Id = jobId;
	}
	
	public String getBean_Name() {
		return bean_Name;
	}
	
	public void setBean_Name(String beanName) {
		this.bean_Name = beanName;
	}
	
	public String getMethod_Name() {
		return method_Name;
	}
	
	public void setMethod_Name(String methodName) {
		this.method_Name = methodName;
	}
	
	public String getParams() {
		return params;
	}
	
	public void setParams(String params) {
		this.params = params;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public Integer getTimes() {
		return times;
	}
	
	public void setTimes(Integer times) {
		this.times = times;
	}
	
	public String getCreate_Time() {
		return create_Time;
	}
	
	public void setCreate_Time(String createTime) {
		this.create_Time = createTime;
	}
}
