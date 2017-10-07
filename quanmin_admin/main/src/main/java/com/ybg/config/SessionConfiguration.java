package com.ybg.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import com.ybg.config.sission.ImoocExpiredSessionStrategy;
import com.ybg.config.sission.ImoocInvalidSessionStrategy;
//Spring Session 配置
@Configuration
public class SessionConfiguration {
	
	
	@Bean
	//@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new ImoocInvalidSessionStrategy("/SessionInvalidUrl");
	}
	
	@Bean
	//@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new ImoocExpiredSessionStrategy("/SessionInvalidUrl");
	}
}
