package br.com.bibliotecabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.bibliotecabackend.model"})
@ComponentScan(basePackages = {"br.*"})
@EnableJpaRepositories(basePackages = {"br.com.bibliotecabackend.repository"})
@EnableTransactionManagement
@RestController
public class BibliotecaBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaBackendApplication.class, args);
	}

}
