package com.ybg.oa.constant;
/** 企业常量累
 * 
 * @author https://gitee.com/YYDeament/88ybg
 *
 * @date 2018年1月27日 23:43:05
 * @version v1.0 */
import com.ybg.oa.company.domain.CompanyVO;
import com.ybg.oa.department.domain.DepartmentVO;

public class CompanyConstant {
	
	private CompanyConstant() {
	}
	
	public static final String	DEFAULT_COMPANY			= "默认企业";
	public static final String	DEFAULT_COMPANY_ID		= "0";
	public static final String	DEFAULT_DEPARTMENT		= "老板";
	public static final String	DEFAULT_DEPARTMENT_ID	= "0";
	
	/** 得到默认企业 **/
	public static CompanyVO getDefaultCompany() {
		CompanyVO bean = new CompanyVO();
		bean.setId("0");
		bean.setName(DEFAULT_COMPANY);
		bean.setFullname(DEFAULT_COMPANY);
		return bean;
	}
	
	/** 默认的最上层级别部门（相当于老板一人） **/
	public static DepartmentVO getDefaultDepartment() {
		DepartmentVO bean = new DepartmentVO();
		bean.setParentid(null);
		bean.setId(DEFAULT_DEPARTMENT_ID);
		bean.setName(DEFAULT_DEPARTMENT);
		bean.setCompanyid(DEFAULT_COMPANY_ID);
		bean.setCompanyname(DEFAULT_DEPARTMENT);
		return bean;
	}
}
