package com.yevhenii.kpi.readmore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;

import java.security.Principal;

//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

//import org.springframework.social.twitter.api.Twitter;
//import org.springframework.social.twitter.api.TwitterProfile;
//import org.springframework.social.twitter.api.impl.TwitterTemplate;
//import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@SpringBootApplication
//@EnableOAuth2Client
@RestController
public class ReadMoreApplication {

//	@Autowired
//	ConnectionFactoryLocator connectionFactoryLocator;

//	@Autowired
//	UsersConnectionRepository usersConnectionRepository;


//	@Autowired
//	SocialConfigurer socialConfigurer;

//	@Autowired
//	OAuth2ClientContext clientContext;

//	@Autowired
//	Twitter twitter;

//	@Autowired
//	ConnectionRepository repository;


	@RequestMapping("/")
	public String test() {
		return "TEST SUCCESS";
	}

//	@RequestMapping("/twitter")
//	public String twitterConnect() {
//		if (repository.findPrimaryConnection(Twitter.class) == null) {
//			return "redirect:/connect/twitter";
//		}
//
//		return String.join(", ", twitter.friendOperations().getFriends().stream().map(TwitterProfile::getName).collect(Collectors.toList()));
//	}

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

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/**")
//				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
//				.authorizeRequests()
//				.antMatchers("/", "/connect**", "/webjars/**")
//				.permitAll()
//				.anyRequest().permitAll()
////				.authenticated()
//				.and()
//				.logout()
//				.logoutSuccessUrl("/").permitAll().and().csrf()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//	}

//	private Filter ssoFilter() {
//
//		CompositeFilter filter = new CompositeFilter();
//		List<Filter> filters = new ArrayList<>();
//
//		OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter(
//				"/connect/facebook");
//		OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), clientContext);
//		facebookFilter.setRestTemplate(facebookTemplate);
//		UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(),
//				facebook().getClientId());
//		tokenServices.setRestTemplate(facebookTemplate);
//		facebookFilter.setTokenServices(tokenServices);
//
////		OAuth2ClientAuthenticationProcessingFilter twitterFilter = new OAuth2ClientAuthenticationProcessingFilter(
////				"/connect/twitter");
////		OAuth2RestTemplate twitterTemplate = new OAuth2RestTemplate(twitter(), clientContext);
////		twitterFilter.setRestTemplate(twitterTemplate);
////		tokenServices = new UserInfoTokenServices(twitterResource().getUserInfoUri(), twitter().getClientId());
////		tokenServices.setRestTemplate(twitterTemplate);
////		twitterFilter.setTokenServices(tokenServices);
//
//		filters.add(facebookFilter);
////		filters.add(twitterFilter);
//
//		filter.setFilters(filters);
//
//		return filter;
//	}
//
//	@Bean
//	public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
//		FilterRegistrationBean registration = new FilterRegistrationBean();
//		registration.setFilter(filter);
//		registration.setOrder(-100);
//		return registration;
//	}
//
//	@Bean
//	@ConfigurationProperties("facebook.client")
//	public AuthorizationCodeResourceDetails facebook() {
//		return new AuthorizationCodeResourceDetails();
//	}
//
//	@Bean
//	@ConfigurationProperties("facebook.resource")
//	public ResourceServerProperties facebookResource() {
//		return new ResourceServerProperties();
//	}
//
//
//	@Bean
//	public SocialConfigurer socialConfigurerAdapter(DataSource dataSource) {
//		// https://github.com/spring-projects/spring-social/blob/master/spring-social-config/src/main/java/org/springframework/social/config/annotation/SocialConfiguration.java#L87
//		return new DatabaseConfigurer(dataSource);
//	}

//	@RequestMapping("/publish-test")
//	public String publishOnFacebook(Principal principal) {
//		return postOnFacebook(principal.getName(), "me", "test from app");
//	}
//
//	public String postOnFacebook(String userId, String pageId, String message) {
//		ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(userId);
//		Connection<Facebook> facebookConnection = connectionRepository.getPrimaryConnection(Facebook.class);
//		Facebook facebook = facebookConnection.getApi();
//
//		return facebook.feedOperations().post(pageId, message);
//	}

//	@Bean
//	@Scope(value="request", proxyMode= ScopedProxyMode.INTERFACES)
//	public Twitter twitter(ConnectionRepository repository) {
//		Connection<Twitter> twitter = repository.findPrimaryConnection(Twitter.class);
//		return twitter != null ? twitter.getApi() : new TwitterTemplate();
//	}
//
////	@Bean
////	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
////	public ConnectionRepository connectionRepository() {
////		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        if (authentication == null) {
////			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
////		}
////        return usersConnectionRepository().createConnectionRepository(authentication.getName());
////	}
//
//	@Autowired
//	private Environment environment;
//
//	@Autowired
//	private DataSource dataSource;
//
////	@Bean
////	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
////	public ConnectionFactoryLocator connectionFactoryLocator() {
////		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
////		registry.addConnectionFactory(new TwitterConnectionFactory(environment.getProperty("twitter.consumerKey"),
////				environment.getProperty("twitter.consumerSecret")));
////		registry.addConnectionFactory(new FacebookConnectionFactory(environment.getProperty("facebook.clientId"),
////				environment.getProperty("facebook.clientSecret")));
////		return registry;
////	}
//
//	@Bean
//	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
//	public ConnectionFactoryLocator connectionFactoryLocator() {
//		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//		registry.addConnectionFactory(new TwitterConnectionFactory(environment.getProperty("spring.social.twitter.appId"),
//				environment.getProperty("spring.social.twitter.appSecret")));
//		return registry;
//	}
//
//	@Bean
//	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
//	public UsersConnectionRepository usersConnectionRepository() {
//		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), Encryptors.noOpText());
//	}
//
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//	public ConnectionRepository connectionRepository() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null) {
//			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
//		}
//		return usersConnectionRepository().createConnectionRepository(authentication.getName());
//	}
//
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//	public Twitter twitter() {
//		Connection<Twitter> twitter = connectionRepository().findPrimaryConnection(Twitter.class);
//		return twitter != null ? twitter.getApi() : null;
//	}
//
//	@Bean
//	public ConnectController connectController() {
//		SinglePageConnectController connectController = new SinglePageConnectController(connectionFactoryLocator(), connectionRepository());
//		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
//		connectController.addInterceptor(new PopupDialogConnectInterceptor());
//		connectController.addInterceptor(new TweetAfterConnectInterceptor());
//		return connectController;
//	}

//	@Bean
//	@ConfigurationProperties("twitter.client")
//	public AuthorizationCodeResourceDetails twitter() {
//		return new AuthorizationCodeResourceDetails();
//	}
//
//	@Bean
//	@ConfigurationProperties("twitter.resource")
//	public ResourceServerProperties twitterResource() {
//		return new ResourceServerProperties();
//	}
}