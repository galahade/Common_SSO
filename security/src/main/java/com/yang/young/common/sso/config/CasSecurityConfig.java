package com.yang.young.common.sso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@Profile("CasAuthentication")
@EnableWebSecurity
public class CasSecurityConfig {

}
