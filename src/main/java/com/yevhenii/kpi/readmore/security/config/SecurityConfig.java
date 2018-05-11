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

//    private final UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

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
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .antMatchers(HttpMethod.DELETE, "/book/review").hasRole("ROLE_ADMIN")
                .antMatchers(HttpMethod.POST, "/book").hasRole("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET, "/book/all").hasRole("ROLE_ADMIN")
                .antMatchers("/admin/register").hasRole("ROLE_ADMIN")
                .anyRequest().hasRole("ROLE_USER");
//                .and()
//                .addFilterBefore(new JwtLoginFilter("/user/login", authenticationManager()),
//                        UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtAuthenticationFilter(),
//                        UsernamePasswordAuthenticationFilter.class);
    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
////        default account creation
//        auth.authenticationProvider(authenticationProvider())
//                .inMemoryAuthentication()
//                .withUser("admin")
//                .password(encoder().encode("admin"))
//                .roles("USER");
//    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setPasswordEncoder(encoder());
//        authProvider.setUserDetailsService(userDetailsService);
//
//        return authProvider;
//    }
}
