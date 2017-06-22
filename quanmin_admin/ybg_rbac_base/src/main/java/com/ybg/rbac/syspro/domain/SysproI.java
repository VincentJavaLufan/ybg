package com.ybg.rbac.syspro.domain;
import java.io.Serializable;

public class SysproI implements Serializable {
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8884558988237838150L;
	private Integer				id;
	private String				proname;
	private String				provalue;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getProname() {
		return proname;
	}
	
	public void setProname(String proname) {
		this.proname = proname;
	}
	
	public String getProvalue() {
		return provalue;
	}
	
	public void setProvalue(String provalue) {
		this.provalue = provalue;
	}
	
	@Override
	public String toString() {
		return "SysproI [id=" + id + ", proname=" + proname + ", provalue=" + provalue + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((proname == null) ? 0 : proname.hashCode());
		result = prime * result + ((provalue == null) ? 0 : provalue.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SysproI other = (SysproI) obj;
		if (proname == null) {
			if (other.proname != null)
				return false;
		}
		else if (!proname.equals(other.proname))
			return false;
		if (provalue == null) {
			if (other.provalue != null)
				return false;
		}
		else if (!provalue.equals(other.provalue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}
}
