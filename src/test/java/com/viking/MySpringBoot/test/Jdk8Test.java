package com.viking.MySpringBoot.test;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
    }
    @Test
    public void dateTimeFormatTest(){
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
//        String format = dateTimeFormatter.format(Instant.now());
//        String format = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss").format(Instant.now());
//        System.out.println(format);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")));
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);
    }
    @Test
    public void testLocalDate() throws InterruptedException {
        LocalDate localDate = LocalDate.now();
        System.out.println("今天的日期是："+localDate);
        int year = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayOfMonth = localDate.getDayOfMonth();
        int dayOfYear = localDate.getDayOfYear();
        System.out.println(year+"年\t"+monthValue+"月\t\t本月第："+dayOfMonth+"日\t今年第："+dayOfYear+"天");
        LocalDate specialDay = LocalDate.of(2019,8,30);
        System.out.println("指定日期："+specialDay);
        LocalDate yearDay = LocalDate.ofYearDay(2019, 300);
        System.out.println("2019年第300天是："+yearDay);

        System.out.println("判断两个日期是否是同一天:"+LocalDate.of(2019,8,30).equals(LocalDate.now()));


//        LocalDate date = LocalDate.ofYearDay(LocalDate.now().getYear(), 1);
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now().getYear(),1,1,0,0,0,0);
//        LocalDate dateTime = LocalDate.ofYearDay(LocalDate.now().getYear(),1);

        long milli1Second = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        Instant from = Instant.from(date);
//        long milli = from.toEpochMilli();
        Instant instant = Instant.ofEpochMilli(milli1Second);
        Date date = new Date(milli1Second);
        System.out.println("milli:"+milli1Second);
        System.out.println("instant:"+instant);
        System.out.println("date:"+date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        System.out.println("calender:"+calendar.getTime().getTime());
        System.out.println("calender.getTime:"+calendar.getTime());
        LocalDate dateOfBirth = LocalDate.of(1999,8,30);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(),dateOfBirth.getDayOfMonth());
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime:"+localTime);
        LocalTime plusHours = localTime.plusHours(3);
        System.out.println("plus3Hours:"+plusHours);
        System.out.println("当前小时数："+localTime.getHour());
        System.out.println("当前分钟数："+localTime.getMinute());
        System.out.println("当前秒数："+localTime.getSecond());
        System.out.println("当前日期:"+localDate);
        LocalDate plus = localDate.plus(-1, ChronoUnit.WEEKS);
        LocalDate minus = localDate.minus(-1, ChronoUnit.WEEKS);
        System.out.println("一周后日期："+plus);
        System.out.println("一周前日期："+minus);
        String format = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("格式化日期："+format);
        Clock clock = Clock.systemUTC();
        System.out.println("当前时刻："+clock.instant()+8);
        System.out.println("当前时钟："+clock.millis());
        String dateText = "2019/08/15";
        LocalDate parse = LocalDate.parse(dateText,DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        LocalDate parse = LocalDate.parse(dateText);
        System.out.println("解析文本后日期为："+parse);
        Duration duration = Duration.ofDays(60);
        System.out.println("duration:"+duration.abs());
        LocalTime specialTime = LocalTime.of(16, 55, 59);
        System.out.println("specialTime:"+specialTime);
        ValueRange range = specialTime.range(ChronoField.HOUR_OF_DAY);
        System.out.println("小时的取值范围:"+range);
        ValueRange monthRange = localDate.range(ChronoField.MONTH_OF_YEAR);
        System.out.println("月份的取值："+monthRange);
        ValueRange yearRange = localDate.range(ChronoField.YEAR);
        System.out.println("年份的取值："+yearRange);
        LocalTime truncatedHour = localTime.truncatedTo(ChronoUnit.HOURS);
        System.out.println("truncatedHour:"+truncatedHour);
        LocalTime truncatedMinutes = localTime.truncatedTo(ChronoUnit.MINUTES);
        System.out.println("truncatedMinutes:"+truncatedMinutes);
        LocalTime truncatedSeconds = localTime.truncatedTo(ChronoUnit.SECONDS);
        System.out.println("truncatedSeconds:"+truncatedSeconds);
        System.out.println("now:"+LocalTime.now());
//        Thread.sleep(5000);
        System.out.println("now:"+LocalTime.now());
        System.out.println("truncatedSeconds:"+truncatedSeconds);
        LocalDateTime localDateTime = localTime.atDate(localDate);
        System.out.println("localDateTime:"+localDateTime);
        LocalDateTime atTime = localDate.atTime(localTime);
        System.out.println("atTime:"+atTime);
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);
        String now = localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now);
        LocalDateTime dateTime1 = LocalDateTime.of(2018, 5, 20, 13, 14, 0);
        LocalDateTime dateTime2 = LocalDateTime.now();
        Instant toInstant = dateTime2.toInstant(ZoneOffset.UTC);
        long milli = toInstant.toEpochMilli();
        System.out.println("milli:"+milli);
        System.out.println("Date.getTime():"+new Date().getTime());
        System.out.println("toInstant:"+toInstant);
        System.out.println("dateTime1:"+dateTime1);
        long until = dateTime1.until(dateTime2, ChronoUnit.DAYS);
        System.out.println(dateTime2);
        System.out.println("相差天数:"+until);
        LocalDateTime time = LocalDateTime.ofEpochSecond(milli/1000, 0, ZoneOffset.UTC);
        LocalDateTime time1 = LocalDateTime.ofInstant(toInstant, ZoneId.systemDefault());
        System.out.println("time:"+time);
        System.out.println("time1:"+time1);
        LocalDateTime dateTime3 = dateTime2.with(ChronoField.HOUR_OF_DAY, 8).with(ChronoField.MINUTE_OF_HOUR,0).with(ChronoField.SECOND_OF_MINUTE,0).with(ChronoField.NANO_OF_SECOND,0);
        System.out.println("dateTime3:"+dateTime3);

        Instant instant1 = Instant.now();
        Instant instant2 = Instant.now(Clock.systemDefaultZone());
        Instant instant3 = Instant.ofEpochMilli(1567737396610L);
        Instant instant4 = Instant.ofEpochSecond(1567737390L);
        Instant instant5 = Instant.ofEpochSecond(1567737390L,0L);
        Instant instant6 = Instant.from(instant4);
        Instant instant7 = Instant.parse("2019-01-01T16:20:00.000Z");
        System.out.println(instant1);
        System.out.println(instant2);
        System.out.println(instant3);
        System.out.println(instant4);
        System.out.println(instant5);
        System.out.println(instant6);
        System.out.println(instant7);
        System.out.println(instant1.toEpochMilli());
        System.out.println(instant1.toString());
    }
    @Test
    public void testThread(){
//        Thread task = new Thread();
//        task.start();
        Integer a = Integer.MAX_VALUE;
        Integer b = Integer.MIN_VALUE;
        float c = Float.MAX_VALUE;
        float d = Float.MIN_VALUE;
//        BigInteger e = new BigInteger("3.1415926");
        BigDecimal e = new BigDecimal("3.1415926");
        BigDecimal f = new BigDecimal("3.14159263");
        BigDecimal x = f.subtract(e);
        BigDecimal y = f.add(e);
        System.out.println("max:"+a);
        System.out.println("min:"+new BigDecimal(b).toPlainString());
        System.out.println("max:"+new BigDecimal(c).toPlainString());
        System.out.println("min:"+new BigDecimal(d).toPlainString());
        System.out.println("x="+x.toPlainString());
        System.out.println("y="+y.toString());
        System.out.println("z="+new BigDecimal("-2").toPlainString());
        int sum = 1;
        for (int i =1 ;i<=32;i++){
            sum=sum*2;
        }
        System.out.println("2^64:"+sum);
        float float_max = 1;
        for (int i = 1;i<=127;i++){
            float_max = float_max * 2;
        }
        float g = 340282356700000000000000000000000000000F;
        //170141183460469231731687303715884105728
        //340282366920938463463374607431768211456
        //340282346638528859811704183484516925440
        System.out.println("float_max="+new BigDecimal(g).toPlainString());

        Integer[] myArray = { 1, 2, 3 };
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
//        Integer [] array = (Integer[]) myList.get(0);
        System.out.println(myList.get(2));//3
        myArray[2] = 0;
        System.out.println(myList.get(2));//0

        List myList1 = Arrays.stream(myArray).collect(Collectors.toList());
//基本类型也可以实现转换（依赖boxed的装箱操作）
        int [] myArray2 = { 1, 2, 3 };
        List myList2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        Object[] objects = myList.toArray();
        System.out.println(Arrays.toString(objects));
    }
}
