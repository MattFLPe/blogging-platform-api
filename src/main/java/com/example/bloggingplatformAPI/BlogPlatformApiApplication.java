package com.example.bloggingplatformAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class BlogPlatformApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogPlatformApiApplication.class, args);
	}

}
