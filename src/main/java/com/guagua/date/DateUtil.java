package com.guagua.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;

/**
 * @author guagua
 * @date 2023/3/18 06:24
 * @describe
 */
public class DateUtil {

    @Test
    public void getTodayStartAndEnd() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println(now); // 2023-03-18T06:36:11.358

        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        System.out.println(today); // 2023-03-18

        LocalDate tomorrow = today.plusDays(1L);
        System.out.println(tomorrow); // 2023-03-19

        long epochSecond = Instant.now().getEpochSecond();
        System.out.println(epochSecond); // 1679092571

        long milli = Instant.now().toEpochMilli();
        System.out.println(milli); // 1679092681782

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZoneId zone = ZoneId.of("Asia/Shanghai");
        long now1 = LocalDateTime.now().atZone(zone).toInstant().toEpochMilli();
        System.out.println(now1);

        // 当天开始时间
        long start = LocalDate.now().atStartOfDay().atZone(zone).toInstant().toEpochMilli();
        String strStart = Instant.ofEpochMilli(start).atZone(zone).format(formatter);
        System.out.println("当天开始时间：" + strStart);

        // 当天结束时间
        long end = LocalDate.now().atStartOfDay().plusDays(1).atZone(zone).toInstant().toEpochMilli();
        String strEnd = Instant.ofEpochMilli(end).atZone(zone).format(formatter);
        System.out.println("当天结束时间：" + strEnd);

        // 本月第一天
        LocalDate now2 = LocalDate.now();
        LocalDate first = now2.with(TemporalAdjusters.firstDayOfMonth());
        long monthStart = LocalDateTime.of(first, LocalTime.MIN).atZone(zone).toInstant().toEpochMilli();
        System.out.println("本月第一天时间戳：" + monthStart);

        String format = LocalDateTime.ofInstant(Instant.ofEpochMilli(monthStart), zone).atZone(zone).format(formatter);
        System.out.println("本月第一天：" + format);

        // 本月最后一天
        LocalDate monthEnd = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        long milli1 = LocalDateTime.of(monthEnd, LocalTime.MAX).atZone(zone).toInstant().toEpochMilli();
        String monthEndStr = Instant.ofEpochMilli(milli1).atZone(zone).format(formatter);
        System.out.println("本月最后一天：" + monthEndStr);

        // 本周第一天
        int dayOfWeek = LocalDate.now().getDayOfWeek().getValue();
        LocalDate firstDayOfWeek = LocalDate.now().minusDays(dayOfWeek - 1);
        long milli2 = LocalDateTime.of(firstDayOfWeek, LocalTime.MIN).atZone(zone).toInstant().toEpochMilli();
        String format1 = Instant.ofEpochMilli(milli2).atZone(zone).format(formatter);
        System.out.println("本周第一天："+ format1);

        // 本周最后一天
        LocalDate lastDayOfWeek = LocalDate.now().plusDays(7 - dayOfWeek);
        long milli3 = LocalDateTime.of(lastDayOfWeek, LocalTime.MAX).atZone(zone).toInstant().toEpochMilli();
        String format2 = Instant.ofEpochMilli(milli3).atZone(zone).format(formatter);
        System.out.println("本周最后一天："+ format2);



    }
}
