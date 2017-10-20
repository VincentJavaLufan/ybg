package com.ybg.base.util.webexception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19 */
@RestControllerAdvice
public class YbgExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** 自定义异常 */
	@ExceptionHandler(ResultException.class)
	public RepostResult handleRRException(ResultException e) {
		RepostResult r = new RepostResult();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public RepostResult handleDuplicateKeyException(DuplicateKeyException e) {
		logger.error(e.getMessage(), e);
		return RepostResult.error("数据库中已存在该记录");
	}
	
	@ExceptionHandler(Exception.class)
	public RepostResult handleException(Exception e) {
		logger.error(e.getMessage(), e);
		return RepostResult.error();
	}
}
