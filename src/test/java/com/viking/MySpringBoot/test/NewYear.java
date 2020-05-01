package com.viking.MySpringBoot.test;

import java.time.LocalDate;

/**
 * Created by Viking on 2020/1/1
 */
public class NewYear {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date.getYear()+" 继续奋斗~");
    }
}
