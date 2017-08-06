package com.ybg.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ybg.base.util.SystemConstant;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**在线API 接口。**/
@Configuration
@EnableSwagger2
/** http://localhost:8080/swagger-ui.html **/
public class Swagger2Configuration {
	
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}
	
	private ApiInfo apiInfo() {
		String comments="以Spring 为中心，模块化开发系统，用户可以随意删减除权限框架外 任意的系统模块。复用，组装性强。2种打包方式，传统可运行的tomcat目录 以及直接jar 方式运行。主要应用技术：Security+Ehcache+quartz+swagger2+Mysql5.6.... Ehcahce 缓存,减少数据库压力quartz 自定义任务执行时间。在线开放API文档 模块，一览系统所有的功能，生成在线API文档,并且可以调试。多维度监控系统，包含sql 监控，内存监控和管理，cpu监控，缓存管理，并发监控，磁盘监控，线程监控和管理，session监控，java 开销监控 等";
		return new ApiInfoBuilder().title("云系统 APIs").description(comments).termsOfServiceUrl(SystemConstant.getSystemdomain()).contact(SystemConstant.getSystemAuth()).version("1.0").build();
	}
}