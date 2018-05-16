package com.yevhenii.kpi.readmore.security.config;

//import com.yevhenii.kpi.readmore.security.UserDetailsServiceImpl;
//import com.yevhenii.kpi.readmore.security.filter.JwtAuthenticationFilter;
//import com.yevhenii.kpi.readmore.security.filter.JwtLoginFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/swagger-ui.html",
                        "/swagger-ui.html/**",
                        "/api/**",
                        "/swagger-resources/**",
                        "//swagger-resources/**",
                        "/v2/**",
                        "/static/**",
                        "/webjars/**",
                        "/null/").permitAll()
                .antMatchers("/", "/user").permitAll()
                .antMatchers("/signin/twitter").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/book/test").permitAll()
                .antMatchers("/actuator", "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/admin/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .antMatchers("/book/review").authenticated() //.hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/book").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/book").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/book/all").hasRole("ADMIN")
                .antMatchers("/admin/register").hasRole("ADMIN")
                .antMatchers("/user/username").hasAnyRole("ADMIN", "USER")
                .anyRequest().hasRole("USER");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
