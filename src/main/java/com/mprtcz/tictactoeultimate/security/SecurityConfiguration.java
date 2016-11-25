package com.mprtcz.tictactoeultimate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Created by Azet on 2016-11-04.
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final
    CustomUserDetailsService customUserDetailsService;

    private final
    CustomAuthEntryPoint customAuthEntryPoint;

    private final
    CustomAuthFailureHandler customAuthFailureHandler;

    private final
    CustomAuthSuccessHandler customAuthSuccessHandler;

    @Autowired
    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService,
                                 CustomAuthEntryPoint customAuthEntryPoint, CustomAuthFailureHandler customAuthFailureHandler, CustomAuthSuccessHandler customAuthSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthEntryPoint = customAuthEntryPoint;
        this.customAuthFailureHandler = customAuthFailureHandler;
        this.customAuthSuccessHandler = customAuthSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//                withUser("temporary").password("temporary").roles("ADMIN").and().
//                withUser("user").password("password").roles("USER").and().
//                withUser("azot").password("asdasd").roles("USER");
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/api/hello", "/", "/*", "/user", "/user/add").permitAll()
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/app/**", "/node_modules/**", "/jsp/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().exceptionHandling().authenticationEntryPoint(customAuthEntryPoint)
                .and().formLogin().successHandler(customAuthSuccessHandler)
                .and().formLogin().failureHandler(customAuthFailureHandler)
                .and()
                .csrf().disable()
                .sessionManagement().maximumSessions(12).sessionRegistry(sessionRegistry())
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }
}