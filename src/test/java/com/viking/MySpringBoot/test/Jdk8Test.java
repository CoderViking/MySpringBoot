package com.viking.MySpringBoot.test;

import org.junit.Test;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Viking on 2019/5/30
 * JDK8特性测试
 */
public class Jdk8Test {
    @Test
    public void instantTest(){
        Instant instant = Instant.now();
        System.out.println("instant:"+instant);
        Date date = new Date();
        System.out.println("date:"+date);
    }
    @Test
    public void localDateTimeTest(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
    }
    @Test
    public void dateTimeFormatTest(){
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
//        String format = dateTimeFormatter.format(Instant.now());
//        System.out.println(format);
    }
}
