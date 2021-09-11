package com.yunhalee.walkerholic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WalkerholicApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalkerholicApplication.class, args);
	}

}
