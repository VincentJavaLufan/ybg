package com.ybg.rbac.syspro.service;
import java.util.List;
import com.ybg.rbac.syspro.domain.Syspro;
import com.ybg.rbac.syspro.qvo.SysproQvo;

public interface SysproService {
	
	void update(Syspro bean);
	
	List<Syspro> query(SysproQvo qvo);
}
