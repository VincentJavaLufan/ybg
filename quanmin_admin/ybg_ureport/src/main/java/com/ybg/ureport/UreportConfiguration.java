package com.ybg.ureport;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import com.bstek.ureport.console.UReportServlet;

/** @author Deament */
@Configuration
@ImportResource("classpath:ureportcontext.xml")
public class UreportConfiguration {
	
	@Bean(name = "uReportServlet")
	public ServletRegistrationBean buildUfloServlet() {
		return new ServletRegistrationBean(new UReportServlet(), "/ureport/*");
	}
}
