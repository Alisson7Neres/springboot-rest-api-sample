package br.com.springboot.springboot_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EntityScan(basePackages = { "br.com.springboot.springboot_model" })
@EnableJpaRepositories(basePackages = { "br.com.springboot.springboot_repository" })
@ComponentScan(basePackages = { "br.com.springboot.springboot_rest.controllers" })

public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// O projeto não localizou a pasta static automáticamente, tive que fazer na mão.
	@Bean
    WebMvcConfigurer webMvcConfigurer() {
    	return new WebMvcConfigurerAdapter() {
    	@Override
    	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    		registry.addResourceHandler("/static/**")
    		.addResourceLocations("classpath:/static/");
    	}
      };
    }

}
