package com.viking.MySpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MapperScan("com.viking.MySpringBoot.mapper")
@SpringBootApplication
public class MySpringBootApplication {

	@RequestMapping("/")
	public String home(){
		return "SpringBoot Start!欢迎使用";
	}

	@RequestMapping("use")
	public String used(String key){
		return "used:"+key;
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class,args);
	}

}