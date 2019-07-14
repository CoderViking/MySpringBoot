package com.viking.MySpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MySpringBootApplication {

	@RequestMapping("/")
	public ModelAndView home(){
		ModelAndView model = new ModelAndView();
		model.setViewName("/my/login");
		return model;
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class,args);
	}

}