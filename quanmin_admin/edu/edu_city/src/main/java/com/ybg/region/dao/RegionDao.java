package com.ybg.region.dao;
import java.util.List;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.util.Page;
import com.ybg.region.domain.RegionVO;
import com.ybg.region.qvo.RegionQuery;

/**
 * 行政区域表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-06
 */
/** @author Administrator */
public interface RegionDao {
	
	/**创建
	 *  @param bean
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
