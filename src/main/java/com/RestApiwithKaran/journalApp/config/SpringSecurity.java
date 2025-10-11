package com.RestApiwithKaran.journalApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration  //Tells Spring that this class contains configuration beans.
@EnableWebSecurity  //Activates Spring Security and integrates it with Spring Boot.
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception { // Main method to define which URLs are open, which require authentication, and what kind of login to use.
        /*This method provides a way to configure how requests are secured. It defines how request matching should be done and what
        security actions should be applied.
        http is an instance of class HttpSecurity
        */
        http
                .authorizeRequests()
                    .antMatchers("/hello").permitAll() //permits all requests to hello without authentication
                    .anyRequest().authenticated() // any other requests need to be authenticated and a form-based login will be used
                //we can access /hello requests without any authentication but if we try to access any other requests, we will be redirected to a login form
                .and()
                .formLogin();
    }

}


