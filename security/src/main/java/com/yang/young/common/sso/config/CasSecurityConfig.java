package com.yang.young.common.sso.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("CasAuthentication")
@EnableWebSecurity
public class CasSecurityConfig {
	
	@Configuration
	@Profile("CASAuthentication")
	@EnableWebSecurity(debug=true)
	@Order(Ordered.LOWEST_PRECEDENCE - 100)
	protected static class SigninAuthenticationConfig extends WebSecurityConfigurerAdapter {
		
		@Resource
		ProviderManager authenticationManager;
		
		
		/*
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/rest/logon","/rest/register");
	    }
		*/
		
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
			
			http.csrf().disable()
			;
			setAuthenticationProvider();
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
		
		public void setAuthenticationProvider() {
			
			List<AuthenticationProvider> providers = authenticationManager.getProviders();
			providers.add(getAuthenticationProvider());
		}
		
		public CasAuthenticationProvider getAuthenticationProvider() {
			return null;
		}
		
	}

}
