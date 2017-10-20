package com.ybg.gen.service;
import java.util.List;
import java.util.Map;
import com.ybg.base.util.Page;
import com.ybg.gen.qvo.GeneratorQuery;

/** 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:33:38 */
public interface SysGeneratorService {
	
	/** 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	Page list(Page page, GeneratorQuery qvo) throws Exception;
	
	/** 查询表
	 * 
	 * @param tableName
	 * @return */
	Map<String, String> queryTable(String tableName);
	
	/** 查询列
	 * 
	 * @param tableName
	 * @return */
	List<Map<String, String>> queryColumns(String tableName);
	
	/** 生成代码 */
	byte[] generatorCode(String[] tableNames);
}
