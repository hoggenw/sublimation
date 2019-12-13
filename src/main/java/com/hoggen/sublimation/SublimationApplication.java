package com.hoggen.sublimation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SublimationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SublimationApplication.class, args);


	}

}
