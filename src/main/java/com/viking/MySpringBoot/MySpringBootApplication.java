package com.viking.MySpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@ServletComponentScan //druid用于扫描所有的Servlet、filter、listener+
//@MapperScan("com.viking.MySpringBoot.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MySpringBootApplication {

	@RequestMapping("/")
	public ModelAndView home(){
		ModelAndView model = new ModelAndView();
		model.setViewName("ace/index");
		return model;
	}

//	@RequestMapping("use")
//	public String used(String key){
//		return "used:"+key;
//	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class,args);
	}

}