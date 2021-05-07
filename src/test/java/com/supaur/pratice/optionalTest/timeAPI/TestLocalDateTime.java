package com.supaur.pratice.optionalTest.timeAPI;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * 对时区的处理  ZonedDate、ZonedTime、ZonedDateTime
 *
 * DateTimeFormatter:格式化时间/日期
 *
 * TemporalAdjuster:时间校正器
 * TemporalAdjusters:TemporalAdjuster的工具类
 *
 * Duration:计算两个“时间”之间的间隔
 * Period:计算两个“日期”之间的间隔
 *
 * Instant:时间戳(以unix元年:1970年1月1日00:00:00到某个时间之间的毫秒值)
 *
 * LocalDate LocalTime LocalDateTime
 */

public class TestLocalDateTime {
    @Test
    public void test01(){
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime1=LocalDateTime.of(2021,01,10,17,15,56,211);
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2=localDateTime.plusYears(2);
        System.out.println(localDateTime2);
        LocalDateTime localDateTime3=localDateTime.minusYears(3);
        System.out.println(localDateTime3);
        System.out.println(localDateTime.getYear());

    }
    @Test
    public void test02(){
        Instant instant=Instant.now();//默认获取UTC时区
        System.out.println(instant);
        OffsetDateTime offsetDateTime=instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(instant.toEpochMilli());
    }
    @Test
    public void test3() throws InterruptedException {
        Instant instant=Instant.now();
        Thread.sleep(1000);
        Instant instant1 = Instant.now();
        System.out.println(Duration.between(instant,instant1).toMillis());
    }
    @Test
    public void test4(){
        LocalTime localTime=LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime1=LocalTime.now();
        System.out.println(Duration.between(localTime,localTime1).toMillis());
        LocalDate localDate=LocalDate.of(2021,1,1);
        LocalDate localDate1=LocalDate.of(2021,2,8);
        Period period = Period.between(localDate, localDate1);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getDays());
    }
    @Test
    public void test5(){
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime1=localDateTime.withDayOfMonth(10);
        System.out.println(localDateTime1);
        LocalDateTime localDateTime2=localDateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(localDateTime2);
        System.out.println("=====================");
        //定义下一个周一
        LocalDateTime localDateTime3=localDateTime.with((l)->{
            LocalDateTime localDateTime4= (LocalDateTime) l;
            DayOfWeek dayOfWeek = ((LocalDateTime) l).getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.SUNDAY)){
                return ((LocalDateTime) l).plusDays(1);
            }else if (dayOfWeek.equals(DayOfWeek.SATURDAY)){
                return ((LocalDateTime) l).plusDays(2);
            }else {
                return localDateTime4.plusDays(3);
            }
        });
        System.out.println(localDateTime3);
    }

    @Test
    public void test6(){
        //DateTimeFormatter:格式化时间/日期
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime=LocalDateTime.now();
        String format = localDateTime.format(dateTimeFormatter);
        System.out.println(format);
        DateTimeFormatter dateTimeFormatter1=DateTimeFormatter.ofPattern("yyyy年MM月dd日HH:mm:ss");
        String format2 = localDateTime.format(dateTimeFormatter1);
        String format1 = dateTimeFormatter1.format(localDateTime);
        System.out.println(format1);
        System.out.println("=========");
        System.out.println(format2);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format3 = dateTimeFormatter2.format(localDateTime);
        System.out.println(format3);
        LocalDateTime parse = localDateTime.parse(format2,dateTimeFormatter1);
        System.out.println(parse);
    }
    @Test
    public void test7(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }
    @Test
    public void test8(){
        LocalDateTime localDateTime=LocalDateTime.now(ZoneId.of("Europe/Tallinn"));
        ZonedDateTime zonedDateTime=localDateTime.atZone(ZoneId.of("Europe/Tallinn"));
        System.out.println(zonedDateTime);
        System.out.println(localDateTime);
    }

}
