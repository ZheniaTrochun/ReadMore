package com.yevhenii.kpi.readmore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableOAuth2Client
@RestController
public class ReadMoreApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	OAuth2ClientContext clientContext;

	@RequestMapping("/")
	public String test() {
		return "TEST SUCCESS";
	}

	@RequestMapping("/user")
	public Principal getPrincipal(Principal principal) {
		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReadMoreApplication.class, args);
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**")
				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/", "/connect**", "/webjars/**")
				.permitAll()
				.anyRequest().permitAll()
//				.authenticated()
				.and()
				.logout()
				.logoutSuccessUrl("/").permitAll().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}

	private Filter ssoFilter() {

		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();

		OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(
				"/connect/facebook");
		OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), clientContext);
		facebookFilter.setRestTemplate(facebookTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(),
				facebook().getClientId());
		tokenServices.setRestTemplate(facebookTemplate);
		facebookFilter.setTokenServices(tokenServices);

		OAuth2ClientAuthenticationProcessingFilter twitterFilter = new OAuth2ClientAuthenticationProcessingFilter(
				"/connect/twitter");
		OAuth2RestTemplate twitterTemplate = new OAuth2RestTemplate(twitter(), clientContext);
		twitterFilter.setRestTemplate(twitterTemplate);
		tokenServices = new UserInfoTokenServices(twitterResource().getUserInfoUri(), twitter().getClientId());
		tokenServices.setRestTemplate(twitterTemplate);
		twitterFilter.setTokenServices(tokenServices);

		filters.add(facebookFilter);
		filters.add(twitterFilter);

		filter.setFilters(filters);

		return filter;
	}

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	@Bean
	@ConfigurationProperties("facebook.client")
	public AuthorizationCodeResourceDetails facebook() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("facebook.resource")
	public ResourceServerProperties facebookResource() {
		return new ResourceServerProperties();
	}


	@Bean
	@ConfigurationProperties("twitter.client")
	public AuthorizationCodeResourceDetails twitter() {
		return new AuthorizationCodeResourceDetails();
	}

	@Bean
	@ConfigurationProperties("twitter.resource")
	public ResourceServerProperties twitterResource() {
		return new ResourceServerProperties();
	}
}