//package com.yevhenii.kpi.readmore.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/static/**").permitAll()
//                .antMatchers("/connect/**").permitAll()
//                .antMatchers("/twitter/**").permitAll()
//                .antMatchers("/user").permitAll()
//                .antMatchers("/api/session/").permitAll()
//                .antMatchers("/api/**").authenticated()
////                .anyRequest().authenticated()
//                .and().csrf().disable();
//    }
//
//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//}
