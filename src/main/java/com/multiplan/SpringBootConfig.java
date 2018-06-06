package com.multiplan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootConfig {

	private static ConfigurableApplicationContext springContext = null;

	public static void main(String[] args) throws Exception {
		SpringBootConfig.getSpringContext(args);
	}

	public static ConfigurableApplicationContext getSpringContext(String[] args) {
		if (springContext == null) {
			springContext = SpringApplication.run(SpringBootConfig.class, args);
		}
		return springContext;

	}

}
