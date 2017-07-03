package com.ybg.gen.dao;
import java.util.List;
import java.util.Map;
import com.ybg.base.util.Page;
import com.ybg.gen.qvo.GeneratorQuery;

/** 代码生成器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午3:32:04 */
public interface SysGeneratorDao {
	
	Page list(Page page, GeneratorQuery qvo) throws Exception;
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
}
