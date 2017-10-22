package com.ybg.region.service;
import com.ybg.region.domain.RegionVO;
import com.ybg.region.qvo.RegionQuery;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;

/** 行政区域表
 * 
 * @author Deament
 * @email
 * @date 2017-07-06 */
public interface RegionService {
	
	/** 创建
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	RegionVO save(RegionVO bean) throws Exception;
	
	/** 更新数据，条件 和 需要更新的字段都不能为空 不限个数个条件
	 * 
	 * @author Deament
	 * @param updatemap
	 *            需要更新的字段和值
	 * @param wheremap
	 *            更新中的条件字段和值
	 * @param table_name
	 *            表的名称 **/
	void update(BaseMap<String, Object> updatemap, BaseMap<String, Object> wheremap);
	
	/** 分页查询
	 * 
	 * @param page
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	Page list(Page page, RegionQuery qvo) throws Exception;
	
	/** 不分页查询
	 * 
	 * @param qvo
	 * @return
	 * @throws Exception
	 */
	List<RegionVO> list(RegionQuery qvo) throws Exception;
	
	/** 根据条件删除
	 * 
	 * @param wheremap
	 */
	void remove(BaseMap<String, Object> wheremap);
	
	/** 获取地区信息
	 * 
	 * @param pkid
	 *            （主键）
	 * @return */
	RegionVO get(Integer pkid);
}
