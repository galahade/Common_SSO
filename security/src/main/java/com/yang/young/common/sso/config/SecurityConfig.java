package com.yang.young.common.sso.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yang.young.common.sso.security.DBUserDetailsServiceHandler;
import com.yang.young.common.sso.security.RestAuthenticationEntryPoint;
import com.yang.young.common.sso.security.RestAuthenticationSuccessHandler;
import com.yang.young.common.sso.security.RestUsernamePasswordAuthenticationFilter;

@Configuration
@Profile("localDBAuthentication")
@EnableJpaRepositories(basePackages="com.yang.young.common.sso.persistance.jpa.repository")
@EntityScan(basePackages="com.yang.young.common.sso.persistance.jpa.entity")
@ComponentScan(basePackages={"com.yang.young.common.sso.persistance.jpa.service","com.yang.young.common.sso.rest","com.yang.young.common.sso.config"})
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
	
	@Configuration
	@EnableWebSecurity(debug=true)
	@Order(Ordered.LOWEST_PRECEDENCE - 100)
	protected static class SigninAuthenticationConfig extends WebSecurityConfigurerAdapter {
		
		/*
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/rest/logon","/rest/register");
	    }
		*/
		
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
			
			http.antMatcher("/rest/logon")
			.addFilterBefore(getUsernamePasswordAuthenticationFilter(), AnonymousAuthenticationFilter.class)
			.anonymous().and().csrf().disable()
			//make request not be redirect just return 401
			.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
			/*
	        //logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
	        http.csrf().disable()
	        	.formLogin().disable()
	        	.addFilterBefore(, SecurityContextHolderAwareRequestFilter.class)
	        	.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
	        	.and()
	        	.authorizeRequests() 
	        	.antMatchers("/rest/logon","/rest/register").anonymous()
	        	.antMatchers("/rest/account/**").authenticated()
	        	//.antMatchers("/rest/admin/**").hasAnyRole("admin")
	        	;
	            */
	    }
		
		@Bean
		public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() {
			UsernamePasswordAuthenticationFilter filter = new RestUsernamePasswordAuthenticationFilter();
			filter.setAuthenticationManager(getAuthenticationManager());
			filter.setAuthenticationSuccessHandler(new RestAuthenticationSuccessHandler());
			filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/rest/logon"));;
			return filter ;
		}
		
		public AuthenticationManager getAuthenticationManager() {
			
			List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
			providers.add(getDBAuthenticationProvide());
			ProviderManager authenticationManager = new ProviderManager(providers);
			
			return authenticationManager;
		}
		
		public AuthenticationProvider getDBAuthenticationProvide() {
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(getDBUserDetailsService());
			provider.setPasswordEncoder(getEncoder());
			return provider;
		}
		@Bean 
		public UserDetailsService getDBUserDetailsService() {
			return new DBUserDetailsServiceHandler();
		}
		@Bean 
		public PasswordEncoder getEncoder() {
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			return encoder;
		}
	}
	
	@Configuration
	@EnableWebSecurity(debug=true)
	@Order(Ordered.LOWEST_PRECEDENCE - 90)
	protected static class AdminAuthenticationConfig extends WebSecurityConfigurerAdapter {
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
			
	        //logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
	        http.csrf().disable()
	        	.formLogin().disable()
	        	.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
	        	.and()
	        	.authorizeRequests() 
	        	.antMatchers("/rest/register").anonymous()
	        	.antMatchers("/rest/account/**").authenticated()
	        	.antMatchers("/rest/admin/**").hasAnyRole("admin")
	        	;
	            
	    }
	}
	
}
