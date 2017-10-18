package com.ybg.urule;
import java.util.Properties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import com.bstek.urule.URulePropertyPlaceholderConfigurer;

/** @author Jacky.gao 相当于 configure.properties 
 * @since 2016年10月12日 */
 @Component 
public class PropertiesConfiguration extends URulePropertyPlaceholderConfigurer implements InitializingBean {
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Properties props = new Properties();
		props.setProperty("urule.resporityServerUrl", "http://127.0.0.1:8081/urule-server");
		setProperties(props);
	}
}