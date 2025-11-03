package com.RestApiwithKaran.journalApp.config;

import com.RestApiwithKaran.journalApp.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration  //Tells Spring that this class contains configuration beans.
@EnableWebSecurity  //Activates Spring Security and integrates it with Spring Boot.
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception   { // Main method to define which URLs are open, which require authentication, and what kind of login to use.
        /*This method provides a way to configure how requests are secured. It defines how request matching should be done and what
        security actions should be applied.
        http is an instance of class HttpSecurity
        */
        http.authorizeRequests()
                .antMatchers("/journal/**","/user/**").authenticated() //needs authenticated
                .antMatchers("/admin/**").hasRole("ADMIN") // admin api's will get authenticated by those users who are admins
                .anyRequest().permitAll() // rest all are permitted
                .and()
                .httpBasic();// no form based login, basic auth for postman
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();

        //Don’t create sessions.
        //Expect every request to include its own credentials (stateless).
        //Disable CSRF because we’re building an API, not a cookie-based site.


        /*
        CSRF (Cross-Site Request Forgery) is a security protection that makes sense for web forms with sessions,
         but it breaks APIs that don’t use cookies.
        By disabling it, you’re saying:
        “I’m building a REST API (stateless), not a browser form app. So CSRF tokens aren’t needed.”
        Without disabling CSRF, your POST, PUT, and DELETE requests would get blocked (403 Forbidden) when testing from Postman or React frontend.
         */
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*
         Think of AuthenticationManagerBuilder as a builder tool for telling Spring:
        “Hey, this is where and how you should find users when they try to log in.”
         So here, We are teaching spring that : where users come from (MongoDB), how passwords are checked (BCrypt)
         */


        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //Tells Spring to fetch user details from MongoDB via service we created.
        // then... Defines how to verify passwords
        //Uses BCrypt to compare entered vs stored password
        super.configure(auth);
        //
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}


