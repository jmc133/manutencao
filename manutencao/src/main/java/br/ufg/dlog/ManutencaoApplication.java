package br.ufg.dlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@ComponentScan(basePackages = {"br.ufg.dlog.*"})
@EntityScan(basePackages = "br.ufg.dlog.*")
@EnableJpaRepositories(basePackages = {"br.ufg.dlog.repository"})
@EnableTransactionManagement
@EnableWebMvc
@SpringBootApplication
public class ManutencaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManutencaoApplication.class, args);
		
	/*	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("22");
		System.out.print("senha  "+result+"   ****");*/
	}
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	}

}


