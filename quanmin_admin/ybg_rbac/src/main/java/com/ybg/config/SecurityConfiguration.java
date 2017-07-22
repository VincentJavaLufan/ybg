package com.ybg.config;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
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
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.ybg.config.security.LoginSuccessHandler;
import com.ybg.config.security.MyAccessDecisionManager;
import com.ybg.config.security.MyAccessDeniedHandler;
import com.ybg.config.security.MySecurityMetadataSource;
import com.ybg.config.security.YcAnthencationProder;
import com.ybg.rbac.user.service.LoginService;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginService				userDetailsService;
	@Autowired
	private YcAnthencationProder		provider;
	@Autowired
	DataSource							datasource;
	@Autowired
	private MySecurityMetadataSource	mySecurityMetadataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// // 允许访问静态资源
		http.authorizeRequests().antMatchers("/", "/upload/**", "/css/**", "/js/**", "/images/**", "/resources/**", "/lib/**", "/skin/**", "/template/**", "/common/**", "/404/**", "/admin_files/**", "/echarts/**", "/fonts/**", "/notebook/**").permitAll();
		// // 所有的访问都需要权限验证
		// http.authorizeRequests().anyRequest().authenticated();
		http.formLogin()
				// 默认访问页
				.loginPage("/common/login_do/login.do").permitAll().and().logout().
				//
				logoutRequestMatcher(new AntPathRequestMatcher("/common/login_do/loginout.do"))
				// // 注销失败跳转到登录页面
				// .logoutSuccessUrl("/common/login_do/tologin.do")
				.permitAll();
		//
		// // 允许iframe 嵌套
		http.headers().frameOptions().disable();
		// // 关闭csrf 防止循环定向
		// http.csrf().disable();
		// / ************分隔***/
		http.addFilterAfter(MyUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		// 开启默认登录页面
		http.authorizeRequests().anyRequest().authenticated().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
			
			public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
				fsi.setSecurityMetadataSource(mySecurityMetadataSource);
				fsi.setAccessDecisionManager(accessDecisionManager());
				fsi.setAuthenticationManager(authenticationManagerBean());
				return fsi;
			}
		});
		// 自定义accessDecisionManager访问控制器,并开启表达式语言
		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()).and().authorizeRequests().anyRequest().authenticated().expressionHandler(webSecurityExpressionHandler());
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
	
	/** 帐号密码 校验
	 * 
	 * 传进的参数帐号必须是username 区分大小写 传进的密码参数名必须是password 区分大小写 **/
	@Bean
	UsernamePasswordAuthenticationFilter MyUsernamePasswordAuthenticationFilter() {
		UsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
		myUsernamePasswordAuthenticationFilter.setPostOnly(true);
		myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		myUsernamePasswordAuthenticationFilter.setUsernameParameter("username");
		myUsernamePasswordAuthenticationFilter.setPasswordParameter("password");
		myUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/common/login_do/unauthorizedUrl.do", "POST"));
		myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());
		return myUsernamePasswordAuthenticationFilter;
	}
	
	/** 没有权限处理 **/
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		MyAccessDeniedHandler accessDeniedHandler = new MyAccessDeniedHandler("/common/login_do/unauthorizedUrl.do");
		return accessDeniedHandler;
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
		decisionVoters.add(webExpressionVoter());// 启用表达式投票器
		MyAccessDecisionManager accessDecisionManager = new MyAccessDecisionManager(decisionVoters);
		return accessDecisionManager;
	}
	
	@Bean(name = "authenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() {
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
