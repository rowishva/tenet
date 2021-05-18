package com.tenet.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//FIXME remove -> exclude = {DataSourceAutoConfiguration.class when you add Datasource configuration
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
