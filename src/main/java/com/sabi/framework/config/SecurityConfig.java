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
                "/api/v1/user/activateUser", "/api/v1/authenticate/login", "/api/v1/authenticate/generatepassword","/api/v1/authenticate/logout","/api/v1/agent/passwordactivation",
                "/api/v1/user/forgetpassword","/api/v1/agent/signup","/api/v1/authenticate/externaltoken","/api/v1/agent/validateotp",
                "/api/v1/agent/resendotp","/api/v1/agent/activateUser","/api/v1/ping/check","/api/v1/globalAuth",
                "/logistics/api/v1/authenticate/login","/logistics/api/v1/authenticate/generatepassword","/logistics/api/v1/authenticate/logout","/logistics/api/v1/user/activateUser","/api/v1/user/findbyemail",
                "/logistics/api/v1/user/forgetpassword","/logistics/api/v1/authenticate/externaltoken","/api/v1/state/list","/api/v1/state/page", "/api/v1/lga/list","/api/v1/lga/page","/api/v1/country/list","/api/v1/country/page",
                "/api/v1/partner/completesignup","/api/v1/partner/signup","/logistics/api/v1/state/list","/logistics/api/v1/state/page","/api/v1/partner/externalsignup","/api/v1/authenticate/admin/login",
                "/logistics/api/v1/lga/list","/logistics/api/v1/lga/page","/logistics/api/v1/country/list","/logistics/api/v1/country/page","/api/v1/partner/details/{supplierId}",
                "/api/v1/assettypeproperties/list","/api/v1/assettypeproperties/page","/api/v1/partner/passwordactivation",
                "/api/v1/partnercategory/list","/api/v1/triprequest/shipmenttrip",
                "/supplier/api/v1/authenticate/login","/supplier/api/v1/authenticate/generatepassword",
                "/supplier/api/v1/authenticate/logout","/supplier/api/v1/user/activateUser","/api/v1/supplieruser/passwordactivation",
                "/supplier/api/v1/user/forgetpassword","/supplier/api/v1/authenticate/externaltoken",
                "/api/v1/supplier/completesignup","/api/v1/supplier/signup","/api/v1/supplier/assetType",
                "/supplier/api/v1/state/list","/supplier/api/v1/state/page","/api/v1/suppliercategory/list","supplier/api/v1/globalAuth",
                "/supplier/api/v1/lga/list","/supplier/api/v1/lga/page","/supplier/api/v1/country/list","/supplier/api/v1/country/page",
                        "/api/v1/organisationtype/active/list",
                        "/api/v1/projectowner/signup",
                        "/api/v1/projectowner/completesignup",
                        "/api/v1/enumerator/completesignup",
                        "/api/v1/enumerator/signup",
                        "/api/v1/enumerator/passwordactivation",
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
