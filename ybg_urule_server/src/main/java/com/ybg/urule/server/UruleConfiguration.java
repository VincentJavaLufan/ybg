package com.ybg.urule.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import com.bstek.urule.console.servlet.URuleServlet;

/**
 * @author Deament 相当于 configure.properties
 * @since 2017年10月12日
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
@ImportResource({ "classpath:urule-console-context.xml" })
public class UruleConfiguration {

	@Bean(name = "uRuleServlet")
	public ServletRegistrationBean registerURuleServlet() {
		return new ServletRegistrationBean(new URuleServlet(), "/urule/*");
	}
}