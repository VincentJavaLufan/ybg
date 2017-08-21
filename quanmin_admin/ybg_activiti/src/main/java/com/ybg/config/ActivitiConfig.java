package com.ybg.config;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.ybg.activiti.modeler.JsonpCallbackFilter;
import com.ybg.base.jdbc.DataBaseConstant;
import javax.sql.DataSource;
import java.io.IOException;

/** activiti配置文件 AbstractProcessEngineAutoConfiguration在activiti-spring-boot-starter-basic下
 *
 * @author 尹冬飞
 * @create 2017-04-10 10:30 */
@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {
	
	// 注入数据源和事务管理器
	@Bean
	public SpringProcessEngineConfiguration springProcessEngineConfiguration(@Qualifier(DataBaseConstant.DB_OA) DataSource dataSource, @Qualifier(DataBaseConstant.TM_OA) PlatformTransactionManager transactionManager, SpringAsyncExecutor springAsyncExecutor) throws IOException {
		return this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
	}
	@Bean
	public JsonpCallbackFilter filter(){
		return new JsonpCallbackFilter();
	}
}
