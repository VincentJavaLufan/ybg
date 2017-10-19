package com.ybg.config;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.ybg.component.jwt.HTTPBasicAuthorizeAttribute;
import com.ybg.component.jwt.HTTPBearerAuthorizeAttribute;

/** @author Deament
 * 
 * @date 2017/1/1springmvc 配置，虽然没没有怎么生效 **/
@ComponentScan(basePackages = { "com.qq", "com.baidu", "com.ybg", "cn", "weibo4j", "org.mybatis" }) // 扫描注解
@ServletComponentScan({ "com.qq", "com.baidu", "com.ybg", "cn", "weibo4j", "org.mybatis" }) // 扫描servlet
@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {
	
	/** 自定义异常页 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.FORBIDDEN, "/common/login_do/unauthorizedUrl.do");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
			container.addErrorPages(error401Page, error404Page, error500Page);
		};
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	}
	
	@Bean
	public FilterRegistrationBean basicFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/user/getuser");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
	
	@Bean
	public FilterRegistrationBean jwtFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();
		registrationBean.setFilter(httpBearerFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/user/getusers");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
}
