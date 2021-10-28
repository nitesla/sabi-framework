package com.sabi.framework.config;


import com.sabi.framework.security.AuthenticationFilter;
import com.sabi.framework.security.TokenAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Order(1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().antMatchers("/",
                "/public/**", "/favicon.ico", "/pub/**",
                "/api/v1/user/activateUser", "/api/v1/authenticate/login","/api/v1/authenticate/logout","/api/v1/agent/passwordactivation",
                "/api/v1/user/forgetpassword","/api/v1/agent/signup","/api/v1/authenticate/externaltoken","/api/v1/agent/validateotp",
                "/api/v1/agent/resendotp","/api/v1/agent/activateUser","/api/v1/ping/check",
                "/logistics/api/v1/authenticate/login","/logistics/api/v1/authenticate/logout","/logistics/api/v1/user/activateUser",
                "/logistics/api/v1/user/forgetpassword","/logistics/api/v1/authenticate/externaltoken",
                "/api/v1/partner/completesignup","/api/v1/partner/signup",
                "/v2/api-docs","/actuator/health","/actuator/**",
                "/swagger-ui.html/**",
                "/webjars/springfox-swagger-ui/**",
                "/swagger-resources/**",
                "/csrf"

        ).
                permitAll().
                anyRequest().authenticated().and().
                cors().and().
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

        http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider tokenAuthenticationProvider() {
        return new TokenAuthenticationProvider();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }









}
