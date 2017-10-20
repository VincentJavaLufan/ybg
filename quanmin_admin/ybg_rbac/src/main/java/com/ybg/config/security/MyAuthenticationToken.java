package com.ybg.config.security;
import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/** @author Deament
 * 
 * @date 2016/9/31 ***/
public class MyAuthenticationToken extends AbstractAuthenticationToken implements Serializable {
	
	private static final long	serialVersionUID	= SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	private final Object		principal;
	
	public MyAuthenticationToken(Object principal) {
		super(null);
		this.principal = principal;
		setAuthenticated(true);
	}
	
	/** This constructor should only be used by <code>AuthenticationManager</code> or <code>AuthenticationProvider</code> implementations that are satisfied with producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>) authentication token.
	 *
	 * @param principal
	 * @param credentials
	 * @param authorities
	 */
	public MyAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		// must use super, as we override
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getPrincipal() {
		return this.principal;
	}
	
	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
}
