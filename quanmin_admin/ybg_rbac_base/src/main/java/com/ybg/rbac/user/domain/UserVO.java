package com.ybg.rbac.user.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ybg.rbac.role.domain.SysRoleVO;
import com.ybg.rbac.user.UserStateConstant;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2016/10/1 */
@ApiModel(value = "用户类", parent = UserDO.class)
public class UserVO extends UserDO implements UserDetails {
	
	private static final long		serialVersionUID	= 6514868907104830464L;
	@ApiModelProperty(name = "salt", dataType = "java.lang.String", value = "可解析加密盐", hidden = true)
	private String					salt;
	/** 用户自身权限 **/
	List<SimpleGrantedAuthority>	auths;
	List<SysRoleVO>					rolelist;
	/** 角色ID 集合 **/
	List<String>					roleids;
	/** 角色键 集合 **/
	List<String>					rolekeys;
	
	public List<String> getRolekeys() {
		return rolekeys;
	}
	
	public void setRolekeys(List<String> rolekeys) {
		this.rolekeys = rolekeys;
	}
	
	public List<String> getRoleids() {
		return roleids;
	}
	
	public void setRoleids(List<String> roleids) {
		this.roleids = roleids;
	}
	
	public List<SysRoleVO> getRolelist() {
		return rolelist;
	}
	
	public void setRolelist(List<SysRoleVO> rolelist) {
		this.rolelist = rolelist;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	// public String getRolename() {
	// return rolename;
	// }
	//
	// public void setRolename(String rolename) {
	// this.rolename = rolename;
	// }
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
		return auths;
	}
	
	public void setAuthorities(List<SimpleGrantedAuthority> auths) {
		this.auths = (List<SimpleGrantedAuthority>) auths;
	}
	
	/** 帐号是否不过期，false则验证不通过 */
	@Override
	public boolean isAccountNonExpired() {
		if (UserStateConstant.DIE.equals(getState())) {
			return false;
		}
		return true;
	}
	
	/** 帐号是否不锁定，false则验证不通过 */
	@Override
	public boolean isAccountNonLocked() {
		if (UserStateConstant.LOCK.equals(getState())) {
			return false;
		}
		return true;
	}
	
	/** 凭证是否不过期，false则验证不通过 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	/** 该帐号是否启用，false则验证不通过 */
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public String toString() {
		return "UserVO [salt=" + salt + ", auths=" + auths + ", getCredentialssalt()=" + getCredentialssalt() + ", getId()=" + getId() + ", getUsername()=" + getUsername() + ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone() + ", getPassword()=" + getPassword() + ", getState()=" + getState() + ", getCreatetime()=" + getCreatetime() + ", getIsdelete()=" + getIsdelete() + ", toString()=" + super.toString() + "]";
	}
}
