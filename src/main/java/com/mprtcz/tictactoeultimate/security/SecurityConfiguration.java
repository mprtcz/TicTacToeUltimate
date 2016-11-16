package com.mprtcz.tictactoeultimate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        auth.inMemoryAuthentication().
                withUser("temporary").password("temporary").roles("ADMIN").and().
                withUser("user").password("password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/hello", "/", "/*", "/user").permitAll()
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/app/**", "/node_modules/**", "/jsp/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().exceptionHandling().authenticationEntryPoint(customAuthEntryPoint)
                .and().formLogin().successHandler(customAuthSuccessHandler)
                .and().formLogin().failureHandler(customAuthFailureHandler)
                .and().csrf().disable();
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
}