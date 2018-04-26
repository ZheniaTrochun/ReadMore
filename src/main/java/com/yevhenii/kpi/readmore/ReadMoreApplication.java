package com.yevhenii.kpi.readmore;

import com.yevhenii.kpi.readmore.model.Book;
import com.yevhenii.kpi.readmore.model.User;
import com.yevhenii.kpi.readmore.repository.BookRepository;
import com.yevhenii.kpi.readmore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableWebMvc
public class ReadMoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadMoreApplication.class, args);
	}
}
