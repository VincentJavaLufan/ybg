package com.ybg.config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

//标注启动了缓存
/** 缓存配置，使用Ehcache <br>
 * * @author Deament
 * 
 * @date 2017/1/1 ehcache.xml 在 main模块 的src/ resources 文件夹中 <br>
 * 
 * @Configuration
 * @EnableCaching 这两个注释取消则 缓存配置失效 ，缓存的方法 将无作用 **/
//@Configuration
//@EnableCaching
public class CacheConfiguration {
	
	/** ehcache主要的管理器
	 * 
	 * @param bean
	 * 
	 * @return */
	@Bean(name = "cache")
	public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
		return new EhCacheCacheManager(bean.getObject());
	}
	
	/** 据shared与否的设置,
	 * 
	 * Spring分别通过CacheManager.create()
	 * 
	 * 或new CacheManager()方式来创建一个ehcache基地.
	 * 
	 * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享) */
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cacheManagerFactoryBean.setShared(true);
		return cacheManagerFactoryBean;
	}
}
