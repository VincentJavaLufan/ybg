package com.ybg.urule;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import com.bstek.urule.KnowledgePackageReceiverServlet;



@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@ImportResource({"classpath:urule-core-context.xml"})
public class URuleConfiguration {
	

	@Bean(name="uRuleServlet")
	public ServletRegistrationBean registerURuleServlet() {
		return new ServletRegistrationBean(new KnowledgePackageReceiverServlet(), "/knowledgepackagereceiver");
	}
}