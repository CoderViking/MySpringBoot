package com.viking.MySpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MySpringBootApplication {

	@RequestMapping("/")
	public String home(){
		return "SpringBoot Start!欢迎使用";
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class,args);
	}

}