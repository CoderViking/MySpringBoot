package com.viking.springboot.MySpringBoot;

import com.viking.springboot.MySpringBoot.dao.WeatherRepository;
import com.viking.springboot.MySpringBoot.pojo.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTests {
	@Autowired
	private WeatherRepository weatherRepository;

	@Test
	public void contextLoads() {
		Weather weather = new Weather();
		weather.setWeather("冰雹");
		weather.setTemperature(10.0f);
		weather.setTip("冰雹天气，请注意预防自然灾害");
		weather.setDate(new Date());
		weatherRepository.save(weather);
	}

	@Test
	public void testQuery(){
		Page<Weather> page = weatherRepository.findAll(PageRequest.of(0, 2));
		System.out.println("list:"+page.getContent());
		System.out.println("total:"+page.getTotalElements());
	}

}
