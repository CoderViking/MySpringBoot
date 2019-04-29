package com.viking.springboot.MySpringBoot;

import com.viking.springboot.MySpringBoot.dao.WeatherRepository;
import com.viking.springboot.MySpringBoot.pojo.MyPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintStream;

@RestController
@EnableConfigurationProperties({MyPojo.class})
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

		SpringApplication application = new SpringApplication(MySpringBootApplication.class);
		Banner banner = new Banner() {
			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {

			}
		};
		application.setBanner(banner);
//		System.out.println("启动之前执行的代码");
		SpringApplication.run(MySpringBootApplication.class,args);
		System.out.println("启动之后执行的代码");
		MyPojo pojo = new MyPojo();
		System.out.println(pojo.toString());
		System.out.println(pojo.name);
	}

}