package com.yang.young.common.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yang.young.common.sso.security.DBUserDetailsServiceHandler;
import com.yang.young.common.sso.security.RestAuthenticationEntryPoint;
import com.yang.young.common.sso.security.RestUsernamePasswordAuthenticationFilter;

@Configuration
@Profile("localDBAuthentication")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/rest/logon","/rest/register");
    }
	*/
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        //logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");
        http.csrf().disable()
        	.formLogin().disable()
        	.addFilterBefore(getUsernamePasswordAuthenticationFilter(), SecurityContextHolderAwareRequestFilter.class)
        	.exceptionHandling().authenticationEntryPoint(getRestAuthenticationEntryPoint())
        	.and()
        	.authorizeRequests() 
        	.antMatchers("/rest/logon","/rest/register").anonymous()
        	.antMatchers("/rest/account/**").authenticated()
        	;
            
    }
	

	
	private AuthenticationEntryPoint getRestAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}
	
	@Bean
	public UsernamePasswordAuthenticationFilter getUsernamePasswordAuthenticationFilter() {
		UsernamePasswordAuthenticationFilter filter = new RestUsernamePasswordAuthenticationFilter();
		filter.setAuthenticationManager(getAuthenticationManager());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/rest/logon"));;
		return filter ;
	}
	
	@Autowired
	private AuthenticationManagerBuilder auth;
	public AuthenticationManager getAuthenticationManager() {
		
		auth.authenticationProvider(getDBAuthenticationProvide());
		AuthenticationManager authenticationManager = auth.getOrBuild();
		
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
