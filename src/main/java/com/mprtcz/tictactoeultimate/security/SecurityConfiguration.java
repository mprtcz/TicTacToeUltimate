package com.mprtcz.tictactoeultimate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

    @Autowired
    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/hello", "/", "/*").permitAll()
                .antMatchers("/api/**").permitAll() //disabled security, remove to enable again
                .antMatchers("/app/**", "/node_modules/**", "/jsp/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password");
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