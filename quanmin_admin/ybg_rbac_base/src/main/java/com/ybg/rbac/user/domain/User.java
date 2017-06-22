package com.ybg.rbac.user.domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ybg.rbac.user.UserStateConstant;

@ApiModel(value = "用户类", parent = UserI.class)
public class User extends UserI implements UserDetails {
	
	/**
	 * 
	 */
	private static final long		serialVersionUID	= 6514868907104830464L;
	@ApiModelProperty(name = "salt", dataType = "java.lang.String", value = "可解析加密盐", hidden = true)
	private String					salt;
	List<SimpleGrantedAuthority>	auths;										// 用户自身权限
	@ApiModelProperty(name = "rolename", dataType = "java.lang.String", value = "角色名称", hidden = true)
	private String					rolename;
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// List<SimpleGrantedAuthority> auths = new ArrayList<>();
		// auths.add(new SimpleGrantedAuthority(getRoleid()));
		return auths;
	}
	
	public void setAuthorities(List<SimpleGrantedAuthority> auths) {
		this.auths = (List<SimpleGrantedAuthority>) auths;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		if (UserStateConstant.DIE.equals(getState())) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		if (UserStateConstant.LOCK.equals(getState())) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
