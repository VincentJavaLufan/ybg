package com.ybg.rbac.resources.proxy;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import com.ybg.base.jdbc.BaseMap;
import com.ybg.base.jdbc.util.QvoConditionUtil;
import com.ybg.component.org.inter.Organization;
import com.ybg.rbac.resources.domain.AbstractResources;
import com.ybg.rbac.resources.domain.Button;
import com.ybg.rbac.resources.domain.Directories;
import com.ybg.rbac.resources.domain.Menu;
import com.ybg.rbac.resources.domain.SysResources;
import com.ybg.rbac.resources.qvo.ResourcesQvo;
import com.ybg.rbac.resources.service.ResourcesService;

/** 数据库转换成实体显示 ,实体转换成数据库存储 **/
public class ResourcesUtil {
	
	ResourcesService resourcesService;
	
	public ResourcesUtil(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}
	
	/** 获取整个菜单 数据库转换成逻辑结构 **/
	public List<Directories> getAllResources() {
		ResourcesQvo qvo = new ResourcesQvo();
		qvo.setIsdelete(0);
		List<SysResources> alldb = resourcesService.query(qvo);
		List<Directories> orgs = new ArrayList<Directories>();
		List<Menu> menus = new ArrayList<Menu>();
		for (SysResources r : alldb) {
			if (r.getParentid() == "0") {
				Directories org = new Directories();
				BeanUtils.copyProperties(r, org);
				orgs.add(org);
			}
		}
		for (SysResources r : alldb) {
			for (Directories o : orgs) {
				if (o.getId().equals(r.getParentid())) {
					Menu org = new Menu();
					BeanUtils.copyProperties(r, org);
					o.add(org);
					menus.add(org);
				}
			}
		}
		for (SysResources r : alldb) {
			for (Menu o : menus) {
				if (o.getId().equals(r.getParentid())) {
					Button org = new Button();
					BeanUtils.copyProperties(r, org);
					o.add(org);
				}
			}
		}
		return orgs;
	}
	
	/** 获取某个节点 数据库转换成逻辑结构 **/
	public Organization get(String id) {
		if (!QvoConditionUtil.checkString(id)) {
			return null;
		}
		SysResources iddbs = resourcesService.get(id);
		if (iddbs == null) {
			return null;
		}
		ResourcesQvo qvo = new ResourcesQvo();
		qvo.setIsdelete(0);
		List<Directories> orgs = getAllResources();
		if (iddbs.getType().equals("1")) {
			for (Directories o : orgs) {
				if (o.getId().equals(id)) {
					return o;
				}
			}
		}
		if (iddbs.getType().equals("2")) {
			for (Directories o : orgs) {
				if (QvoConditionUtil.checkList(o.list)) {
					for (AbstractResources menu : o.list) {
						Menu m = (Menu) menu;
						if (m.getId().equals(id)) {
							return m;
						}
					}
				}
			}
		}
		if (iddbs.getType().equals("3")) {
			Button org = new Button();
			BeanUtils.copyProperties(iddbs, org);
			return org;
		}
		return null;
	}
	
	/** 删除操作时，删除整个下级 **/
	public void removebyid(String id) {
		SysResources bean = resourcesService.get(id);
		if (bean.getType().equals("1")) {
			Directories dir = (Directories) get(id);
			resourcesService.removebyid(id);
			if (QvoConditionUtil.checkList(dir.list)) {
				for (AbstractResources menu : dir.list) {
					resourcesService.removebyid(menu.getId());
					if (QvoConditionUtil.checkList(menu.list)) {
						for (AbstractResources button : menu.list) {
							resourcesService.removebyid(button.getId());
						}
					}
				}
			}
		}
		if (bean.getType().equals("2")) {
			resourcesService.removebyid(id);
			BaseMap<String, Object> updatemap = new BaseMap<String, Object>();
			BaseMap<String, Object> wheremap = new BaseMap<String, Object>();
			updatemap.put("isdelete", 1);
			wheremap.put("parentid", id);
			resourcesService.update(updatemap, wheremap);
		}
		if (bean.getType().equals("3")) {
			resourcesService.removebyid(id);
		}
	}
	
	/*** 快速对菜单添加一个增删查改 操作 **/
	public void createMenuWithButton(SysResources resources, boolean add, boolean remove, boolean update, boolean get, boolean toadd, boolean toupdate, boolean list, boolean index) {
		SysResources bean = resourcesService.create(resources);
		String parentid=bean.getId();
		SysResources addbutton= new SysResources();
		addbutton.setParentid(parentid);
		addbutton.setResurl("");
		addbutton.setType("3");
		
	}
}
