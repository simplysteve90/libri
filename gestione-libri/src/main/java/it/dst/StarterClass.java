package it.dst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages ="it.dst")
@EnableJpaRepositories(basePackages = "it.dst.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "it.dst.model")
public class StarterClass {

	public static void main(String[] args) {
		SpringApplication.run(StarterClass.class, args);

	}
}