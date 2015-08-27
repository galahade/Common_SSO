package com.yang.young.common.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import com.yang.young.common.sso.security.DBUserDetailsServiceHandler;
import com.yang.young.common.sso.security.RestAuthenticationEntryPoint;

@Configuration
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

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getDBAuthenticationProvide());
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
