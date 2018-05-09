package com.yevhenii.kpi.readmore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

//import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
//import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
//import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;

//import org.springframework.social.twitter.api.Twitter;
//import org.springframework.social.twitter.api.TwitterProfile;
//import org.springframework.social.twitter.api.impl.TwitterTemplate;
//import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@SpringBootApplication
//@EnableOAuth2Client
@RestController
@Slf4j
public class ReadMoreApplication {

//	@Autowired
//	ConnectionFactoryLocator connectionFactoryLocator;
//
//	@Autowired
//	UsersConnectionRepository usersConnectionRepository;
//
//
//	@Autowired
//	SocialConfigurer socialConfigurer;
//
//	@Autowired
//	OAuth2ClientContext clientContext;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ConnectionRepository connectionRepository;

	@Autowired
    ConnectionFactoryLocator connectionFactoryLocator;

    @Bean
    public ConnectController connectController() {
        ConnectController controller = new ConnectController(connectionFactoryLocator,
                connectionRepository);
//        controller.addInterceptor(new TweetAfterConnectInterceptor(connectionRepository));
        return controller;
    }

	@RequestMapping("/user")
	public Principal getPrincipal(Principal principal) {
    	Connection<Twitter> tw = connectionRepository.findPrimaryConnection(Twitter.class);
		return principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(ReadMoreApplication.class, args);
	}

//	@Bean
//	public RequestContextListener requestContextListener() {
//		return new RequestContextListener();
//	}

//	@RequestMapping(value = "/connect/twitter", method = RequestMethod.GET)
//	public ModelAndView callback(@RequestParam String oauth_token, @RequestParam String oauth_verifier, ServletRequest request, ServletResponse response) {
//		log.info("test from connect/twitterConnected");
////		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
////			return new ModelAndView("redirect:/connect/twitter");
////		}
//
//		Map map = request.getParameterMap();
//
//		HttpServletRequest req = (HttpServletRequest) request;
//
//		String query = req.getQueryString();
//
//// error here
////		UserProfile profile = connectionRepository.findPrimaryConnection(Twitter.class).fetchUserProfile();
////		String username = profile.getUsername();
////		UsernamePasswordAuthenticationToken token =
////				new UsernamePasswordAuthenticationToken(username, null, null);
////		SecurityContextHolder.getContext().setAuthentication(token);
//
//
//
//		return new ModelAndView("redirect:/");
//	}

//	@RequestMapping(value = "/connect/connect/twitterConnected", method = RequestMethod.GET)
//	public ModelAndView connect(ServletRequest request) {
//		log.info("test from connect/twitterConnected");
//		if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
//			return new ModelAndView("redirect:/connect/twitter");
//		}
//
//        Map map = request.getParameterMap();
//
//		HttpServletRequest req = (HttpServletRequest) request;
//
//		String query = req.getQueryString();
//
//		UserProfile profile = connectionRepository.findPrimaryConnection(Twitter.class).fetchUserProfile();
//		String username = profile.getUsername();
//		UsernamePasswordAuthenticationToken token =
//				new UsernamePasswordAuthenticationToken(username, null, null);
//		SecurityContextHolder.getContext().setAuthentication(token);
//
//		return new ModelAndView("redirect:/");
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/**")
////				.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
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


//	@Bean
//	public SocialConfigurer socialConfigurerAdapter(DataSource dataSource) {
//		// https://github.com/spring-projects/spring-social/blob/master/spring-social-config/src/main/java/org/springframework/social/config/annotation/SocialConfiguration.java#L87
//		return new DatabaseConfigurer(dataSource);
//	}


//	@RequestMapping("/permission")
//	public String askPermissionFromFacebook(Principal principal) {
//
//		return "redirect:https://graph.facebook.com/oauth/authorize?" +
//				"scope=manage_pages,publish_pages" +
//				"&client_id=" + principal.getName() +
//				"&display=page&redirect_uri=https://read-more.herokuapp.com/connect/facebook";
//	}
//
//
//	@RequestMapping("/publish-test")
//	public String publishOnFacebook(Principal principal) {
//		String tokenValue = (String)((OAuth2Authentication) principal).getCredentials();
//		String contextToken = clientContext.getAccessToken().getValue();
//
//		log.info("token from context: " + contextToken);
//		log.info("token from principal: " + tokenValue);
//
//		FacebookTemplate fbTemplate = new FacebookTemplate(contextToken);
////		fbTemplate.setRequestFactory(restTemplate);
//		return fbTemplate.feedOperations().updateStatus("test from app");
//	}

//	EAAeT1TPQypsBAITgVhUCTWZBZCrsqvmpn6ZBGYzeHZCmlz5t6I5OVm9ifKYSTBHIrXohpyPYD2cmPCXuyx3eDKAw1626o6VtbpLIEFxE7uou3y4PaqEyISvGc2azWL7wW7ZAcTpXfbSTHtejvbZAJc3jWukqlct5KtJwxtoqA62QZDZD

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