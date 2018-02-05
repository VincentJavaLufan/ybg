package com.ybg.gen.utils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import com.ybg.base.jdbc.util.DateUtil;
import com.ybg.base.util.SpringContextUtils;
import com.ybg.gen.entity.ColumnEntity;
import com.ybg.gen.entity.TableEntity;
import com.ybg.gen.service.SysGeneratorService;
import cn.hutool.extra.template.VelocityUtil;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/** 代码生成器 工具类
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24 */
public class GenUtils {
	
	private static final String	TEMPLATES_DO			= "DO.java.vm";
	private static final String	TEMPLATES_DAO			= "Dao.java.vm";
	private static final String	TEMPLATES_DAOIMPL		= "DaoImpl.java.vm";
	private static final String	TEMPLATES_SERVICE		= "Service.java.vm";
	private static final String	TEMPLATES_SERVICEIMPL	= "ServiceImpl.java.vm";
	private static final String	TEMPLATES_CONTROLLER	= "Controller.java.vm";
	private static final String	TEMPLATES_LIST_HTML		= "list.html.vm";
	private static final String	TEMPLATES_LIST_JS		= "list.js.vm";
	private static final String	TEMPLATES_SQL			= "menu.sql.vm";
	private static final String	TEMPLATES_VO			= "VO.java.vm";
	private static final String	TEMPLATES_QUERY			= "Query.java.vm";
	private static final String	BASE_DIR				= "template";
	
	public static List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add(BASE_DIR + "/" + TEMPLATES_DO);
		templates.add(BASE_DIR + "/" + TEMPLATES_DAO);
		templates.add(BASE_DIR + "/" + TEMPLATES_DAOIMPL);
		templates.add(BASE_DIR + "/" + TEMPLATES_SERVICE);
		templates.add(BASE_DIR + "/" + TEMPLATES_SERVICEIMPL);
		templates.add(BASE_DIR + "/" + TEMPLATES_CONTROLLER);
		templates.add(BASE_DIR + "/" + TEMPLATES_LIST_HTML);
		templates.add(BASE_DIR + "/" + TEMPLATES_LIST_JS);
		templates.add(BASE_DIR + "/" + TEMPLATES_SQL);
		templates.add(BASE_DIR + "/" + TEMPLATES_VO);
		templates.add(BASE_DIR + "/" + TEMPLATES_QUERY);
		return templates;
	}
	
	/** 生成代码
	 * 
	 * @throws Exception
	 */
	public static void generatorCode(Map<String, String> table, List<Map<String, String>> columns, ZipOutputStream zip) throws Exception {
		// 配置信息
		Map<String, String> config = getConfig();
		// 表信息
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setComments(table.get("tableComment"));
		// 表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), config.get("tablePrefix"));
		tableEntity.setClassName(className);
		tableEntity.setClassname(StringUtils.uncapitalize(className));
		// 列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));
			// 列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
			// 列的数据类型，转换成Java类型
			String attrType = config.get(columnEntity.getDataType());
			if (attrType == null) {
				attrType = "unknowType";
			}
			columnEntity.setAttrType(attrType);
			// 是否主键
			if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
				tableEntity.setPk(columnEntity);
			}
			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);
		// 没主键，则第一个字段为主键
		if (tableEntity.getPk() == null) {
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}
		// 设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);
		// 封装模板数据
		Map<String, Object> map = new HashMap<>(10);
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("pathName", config.get("pathName") + tableEntity.getClassname().toLowerCase() + "_do/");
		map.put("columns", tableEntity.getColumns());
		map.put("package", config.get("package"));
		map.put("author", config.get("author"));
		map.put("email", config.get("email"));
		map.put("datetime", DateUtil.getDate());
		VelocityContext context = new VelocityContext(map);
		// 获取模板列表
		List<String> templates = getTemplates();
		for (String template : templates) {
			
			// 渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			try {
				// 添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.get("package"))));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new Exception("渲染模板失败，表名：" + tableEntity.getTableName(), e);
			}
		}
	}
	
	/** 列名转换成Java属性名 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
	}
	
	/** 表名转换成Java类名 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}
	
	/** 获取配置信息
	 * 
	 * @throws Exception
	 */
	public static Map<String, String> getConfig() throws Exception {
		SysGeneratorService service = SpringContextUtils.getBean(SysGeneratorService.class);
		return service.queryGenSetting();
	}
	
	/** 获取文件名 */
	public static String getFileName(String template, String className, String packageName) {
		String packagePath = "main" + File.separator + "java" + File.separator;
		if (StringUtils.isNotBlank(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator;
		}
		if (template.contains(TEMPLATES_DO)) {
			return packagePath + "domain" + File.separator + className + "DO.java";
		}
		if (template.contains(TEMPLATES_VO)) {
			return packagePath + "domain" + File.separator + className + "VO.java";
		}
		if (template.contains(TEMPLATES_QUERY)) {
			return packagePath + "qvo" + File.separator + className + "Query.java";
		}
		if (template.contains(TEMPLATES_DAO)) {
			return packagePath + "dao" + File.separator + className + "Dao.java";
		}
		if (template.contains(TEMPLATES_DAOIMPL)) {
			return packagePath + "dao" + File.separator + className + "DaoImpl.java";
		}
		if (template.contains(TEMPLATES_SERVICE)) {
			return packagePath + "service" + File.separator + className + "Service.java";
		}
		if (template.contains(TEMPLATES_SERVICEIMPL)) {
			return packagePath + "service" + File.separator + className + "ServiceImpl.java";
		}
		if (template.contains(TEMPLATES_CONTROLLER)) {
			return packagePath + "controller" + File.separator + className + "Controller.java";
		}
		if (template.contains(TEMPLATES_LIST_HTML)) {
			return "main" + File.separator + "webapp" + File.separator + "WEB-INF" + File.separator + "page" + File.separator + "generator" + File.separator + className.toLowerCase() + ".html";
		}
		if (template.contains(TEMPLATES_LIST_JS)) {
			return "main" + File.separator + "webapp" + File.separator + "js" + File.separator + "generator" + File.separator + className.toLowerCase() + ".js";
		}
		if (template.contains(TEMPLATES_SQL)) {
			return className.toLowerCase() + "_menu.sql";
		}
		return null;
	}
}
