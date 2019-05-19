package com.viking.MySpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
<<<<<<< HEAD
import org.springframework.stereotype.Controller;
=======
>>>>>>> 175e22d676d95d538524290ec668472a5e2d6f1c
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
@Controller
//@ServletComponentScan //druid用于扫描所有的Servlet、filter、listener+
//@MapperScan("com.viking.MySpringBoot.mapper")
=======
@RestController
>>>>>>> 175e22d676d95d538524290ec668472a5e2d6f1c
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MySpringBootApplication {

	@RequestMapping("/")
	public String home(){
		return "index.html";
	}

<<<<<<< HEAD
//	@RequestMapping("use")
//	public String used(String key){
//		return "used:"+key;
//	}

=======
>>>>>>> 175e22d676d95d538524290ec668472a5e2d6f1c
	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class,args);
	}

}