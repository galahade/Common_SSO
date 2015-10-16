package com.yang.young.common.sso.config;

import java.util.ArrayList;
import java.util.List;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yang.young.common.sso.security.RestAuthenticationEntryPoint;

@Configuration
@Profile("CasAuthentication")
@EnableWebSecurity
public class CasSecurityConfig {

	@Configuration
	@Profile("CASAuthentication")
	@EnableWebSecurity(debug = true)
	@Order(Ordered.LOWEST_PRECEDENCE - 100)
	protected static class SigninAuthenticationConfig extends
			WebSecurityConfigurerAdapter {

		@Bean
		public ServiceProperties serviceProperties() {
			ServiceProperties serviceProperties = new ServiceProperties();
			serviceProperties.setService("http://localhost:8080/login/cas");
			serviceProperties.setSendRenew(false);
			return serviceProperties;
		}

		@Bean
		public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
			CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
			casAuthenticationEntryPoint
					.setLoginUrl("https://localhost:8443/cas/login");
			casAuthenticationEntryPoint
					.setServiceProperties(serviceProperties());
			return casAuthenticationEntryPoint;
		}

		@Bean
		public CasAuthenticationFilter casAuthenticationFilter()
				throws Exception {
			CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
			casAuthenticationFilter
					.setAuthenticationManager(authenticationManager());
			return casAuthenticationFilter;
		}

		@Bean
		public CasAuthenticationProvider casAuthenticationProvider() {
			CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
			casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
			casAuthenticationProvider.setServiceProperties(serviceProperties());
			casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
			casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");

			return casAuthenticationProvider;
		}
		
		@Bean
	    public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> authenticationUserDetailsService() {
	        return new TestCasAuthenticationUserDetailsService();
	    }

		@Bean
	    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
	        return new Cas20ServiceTicketValidator("https://localhost:8443/cas");
	    }

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable().formLogin().disable()
					.addFilter(casAuthenticationFilter()).exceptionHandling()
					.authenticationEntryPoint(casAuthenticationEntryPoint())
					.and()
					.authorizeRequests() 
					.antMatchers("/rest/register").anonymous()
		        	.antMatchers("/rest/account/**").authenticated()
		        	.antMatchers("/rest/admin/**").hasAnyRole("admin")
        	;
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.authenticationProvider(casAuthenticationProvider());
		}

	}
	
	
	public static class TestCasAuthenticationUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

		@Override
		public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User("joe", "joe", authorities);
		}

	}

}
