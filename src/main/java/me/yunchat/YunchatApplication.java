package me.yunchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YunchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(YunchatApplication.class, args);
	}

}
