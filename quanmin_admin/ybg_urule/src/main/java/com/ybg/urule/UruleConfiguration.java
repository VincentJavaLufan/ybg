package com.ybg.urule;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import com.bstek.urule.KnowledgePackageReceiverServlet;

/**
 * @author Deament
 * @since 2017年10月19日
 **/

@Configuration
@ImportResource({ "classpath:urule-core-context.xml" })
public class UruleConfiguration {

	@Bean(name = "uRuleServlet")
	public ServletRegistrationBean registerURuleServlet() {
		return new ServletRegistrationBean(new KnowledgePackageReceiverServlet(), "/knowledgepackagereceiver");
	}
}