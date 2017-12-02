package com.ybg.config;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;
import com.ybg.config.security.LoginSuccessHandler;
import com.ybg.config.security.YbgAccessDecisionManager;
import com.ybg.config.security.YbgSecurityMetadataSource;
import com.ybg.config.security.YbgAuthencationProder;
import com.ybg.rbac.user.service.LoginServiceImpl;

/** @author Deament
 * 
 * @since @date 2016/9/31 ***/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginServiceImpl					userDetailsService;
	@Autowired
	private YbgAuthencationProder				provider;
	/** 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问 **/
	@Autowired
	private YbgSecurityMetadataSource			mySecurityMetadataSource;
	@Autowired
	private SpringSocialConfigurer				imoocSocialSecurityConfig;
	@Autowired
	private SessionInformationExpiredStrategy	sessionInformationExpiredStrategy;
	@Autowired
	private InvalidSessionStrategy				invalidSessionStrategy;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// // 允许访问静态资源
		http.authorizeRequests().antMatchers("/", "/upload/**", "/css/**", "/js/**", "/images/**", "/resources/**", "/lib/**", "/skin/**", "/template/**", "/common/**", "/404/**", "/admin_files/**", "/echarts/**", "/fonts/**", "/notebook/**").permitAll();
		// // 所有的访问都需要权限验证
		// http.authorizeRequests().anyRequest().authenticated();
		//
		// // 允许iframe 嵌套
		http.headers().frameOptions().disable();
		// // 关闭csrf 防止循环定向
		// http.csrf().disable();
		// / ************分隔***/
		// http.addFilterAfter(MyUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		/** 社交登录 **/
		http.apply(imoocSocialSecurityConfig);
		// http.apply(socialConfigurer).and().apply(socialConfigurer);
		http.sessionManagement().invalidSessionStrategy(invalidSessionStrategy).maximumSessions(1).maxSessionsPreventsLogin(true).expiredSessionStrategy(sessionInformationExpiredStrategy);
		// 开启默认登录页面
		// 一个自定义的filter，必须包含 authenticationManager,accessDecisionManager,securityMetadataSource三个属性，
		// 我们的所有控制将在这三个类中实现，解释详见具体配置
		http.authorizeRequests().anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			
			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
				fsi.setSecurityMetadataSource(mySecurityMetadataSource);
				fsi.setAccessDecisionManager(accessDecisionManager());
				fsi.setAuthenticationManager(authenticationManagerBean());
				return fsi;
			}
		});
		// 自定义accessDecisionManager访问控制器,并开启表达式语言
		// http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler());
		// 自定义登录页面
		http.csrf().disable();
		// session管理
		http.sessionManagement().maximumSessions(1);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/webjars/**");
		web.ignoring().antMatchers("/img/**");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 采用自定义验证
		auth.authenticationProvider(provider);
		// 需要采用加密
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	/** 密码加密 **/
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	
	/** 登录成功后跳转的页面 用户或者管理员登录日志 */
	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}
	
	@Bean
	public LoggerListener loggerListener() {
		return new LoggerListener();
	}
	
	@Bean
	public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
		return new org.springframework.security.access.event.LoggerListener();
	}
	
	/*
	 * 
	 * 这里可以增加自定义的投票器
	 */
	@Bean(name = "accessDecisionManager")
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		// 启用表达式投票器
		decisionVoters.add(webExpressionVoter());
		YbgAccessDecisionManager accessDecisionManager = new YbgAccessDecisionManager(decisionVoters);
		return accessDecisionManager;
	}
	
	/** 类可以有许多provider（提供者）提供用户验证信息，这里笔者自己写了一个类LoinServiceImpl来获取用户信息。 **/
	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() {
		// 这个是处理验证的，这里需要特别说明的是：这个类不单只这个拦截器用到，
		// 还有验证拦截器AuthenticationProcessingFilter也用到 了
		// ，而且实际上的登陆验证也是AuthenticationProcessingFilter拦截器调用
		// authenticationManager来处理的，我们这个拦截器只是为了拿到验证用户信息而已（这里不太清楚，因为
		// authenticationManager笔者设了断点，用户登陆后再也没调用这个类了，而且调用这个类时不是笔者自己写的那个拦截
		// 器调用的，看了spring技术内幕这本书才知道是AuthenticationProcessingFilter拦截器调用的）
		AuthenticationManager authenticationManager = null;
		try {
			authenticationManager = super.authenticationManagerBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authenticationManager;
	}
	
	@Bean(name = "failureHandler")
	public SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/common/login_do/unauthorizedUrl.do");
	}
	
	/*
	 * 表达式控制器
	 */
	@Bean(name = "expressionHandler")
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		return webSecurityExpressionHandler;
	}
	
	/*
	 * 表达式投票器
	 */
	@Bean(name = "expressionVoter")
	public WebExpressionVoter webExpressionVoter() {
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
		return webExpressionVoter;
	}
}
