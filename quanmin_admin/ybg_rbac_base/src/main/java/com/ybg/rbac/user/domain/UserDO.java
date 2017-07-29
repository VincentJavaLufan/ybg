/**
 * 
 */
package com.ybg.rbac.user.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import com.ybg.base.util.excel.ExcelVOAttribute;

@ApiModel(value = "用戶实体类(数据库)", description = "")
/** @author yy 2016-06-21 用戶实体类(数据库) */
public class UserDO implements Serializable {
	
	private static final long	serialVersionUID	= 8884558988237838150L;
	@ApiModelProperty(name = "id", dataType = "java.lang.String", value = "用户编码")
	@ExcelVOAttribute(name = "编码", column = "A")
	private String				id;
	@ApiModelProperty(name = "username", dataType = "java.lang.String", value = "账号")
	@ExcelVOAttribute(name = "账号", column = "B", isExport = true)
	@NotBlank(message = "账号不能为空")
	private String				username;
	@ApiModelProperty(name = "email", dataType = "java.lang.String", value = "电子邮箱")
	@ExcelVOAttribute(name = "电子邮箱", column = "D", isExport = true)
	@NotBlank(message = "电子邮箱不能为空")
	private String				email;
	@ApiModelProperty(name = "phone", dataType = "java.lang.String", value = "手机")
	@ExcelVOAttribute(name = "手机", column = "E", isExport = true)
	private String				phone;
	@ApiModelProperty(name = "password", dataType = "java.lang.String", value = "密码")
	@ExcelVOAttribute(name = "密码", column = "C", prompt = "密码保密哦!", isExport = false)
	@NotBlank(message = "密码不能为空")
	private String				password;
	@ApiModelProperty(name = "state", dataType = "java.lang.String", value = "状态")
	@ExcelVOAttribute(name = "状态", column = "F", isExport = true)
	private String				state;
	@ApiModelProperty(name = "createtime", dataType = "java.lang.String", value = "创建时间")
	@ExcelVOAttribute(name = "创建时间", column = "G", isExport = true)
	private String				createtime;
	@ApiModelProperty(name = "isdelete", dataType = "java.lang.Integer", value = "是否删除")
	private Integer				isdelete;
	@ApiModelProperty(name = "roleid", dataType = "java.lang.String", value = "角色编码")
	@ExcelVOAttribute(name = "角色编码", column = "H", isExport = true)
	@NotBlank(message = "角色编码不能为空")
	private String				roleid;
	@ApiModelProperty(name = "credentialssalt", dataType = "java.lang.String", value = "加密盐", hidden = true)
	private String				credentialssalt;
	
	public String getCredentialssalt() {
		return credentialssalt;
	}
	
	public void setCredentialssalt(String credentialssalt) {
		this.credentialssalt = credentialssalt;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public Integer getIsdelete() {
		return isdelete;
	}
	
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	
	public String getRoleid() {
		return roleid;
	}
	
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	@Override
	public String toString() {
		return "UserDO [id=" + id + ", username=" + username + ", email=" + email + ", phone=" + phone + ", password=" + password + ", state=" + state + ", createtime=" + createtime + ", isdelete=" + isdelete + ", roleid=" + roleid + ", credentialssalt=" + credentialssalt + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createtime == null) ? 0 : createtime.hashCode());
		result = prime * result + ((credentialssalt == null) ? 0 : credentialssalt.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isdelete == null) ? 0 : isdelete.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((roleid == null) ? 0 : roleid.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDO other = (UserDO) obj;
		if (createtime == null) {
			if (other.createtime != null)
				return false;
		}
		else if (!createtime.equals(other.createtime))
			return false;
		if (credentialssalt == null) {
			if (other.credentialssalt != null)
				return false;
		}
		else if (!credentialssalt.equals(other.credentialssalt))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		}
		else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (isdelete == null) {
			if (other.isdelete != null)
				return false;
		}
		else if (!isdelete.equals(other.isdelete))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		}
		else if (!phone.equals(other.phone))
			return false;
		if (roleid == null) {
			if (other.roleid != null)
				return false;
		}
		else if (!roleid.equals(other.roleid))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		}
		else if (!state.equals(other.state))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		}
		else if (!username.equals(other.username))
			return false;
		return true;
	}
}
