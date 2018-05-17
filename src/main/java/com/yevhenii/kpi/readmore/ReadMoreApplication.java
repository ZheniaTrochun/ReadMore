package com.yevhenii.kpi.readmore;

import com.yevhenii.kpi.readmore.exception.RegistrationException;
import com.yevhenii.kpi.readmore.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
public class ReadMoreApplication {

	private final AdminService adminService;

	private final ConnectionRepository connectionRepository;

    private final ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
	public ReadMoreApplication(AdminService adminService,
							   ConnectionRepository connectionRepository,
							   ConnectionFactoryLocator connectionFactoryLocator) {
		this.adminService = adminService;
		this.connectionRepository = connectionRepository;
		this.connectionFactoryLocator = connectionFactoryLocator;
	}

	@Bean
    public ConnectController connectController() {
        ConnectController controller = new ConnectController(connectionFactoryLocator,
                connectionRepository);
        return controller;
    }

//    default admin user creation
    @Bean
	public CommandLineRunner runner() {
    	return (args) -> {
    		try {
    			adminService.register("admin", "admin@mail.com", "admin");
			} catch (RegistrationException e) {
    			log.info("Default admin user is already exists");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReadMoreApplication.class, args);
	}
}