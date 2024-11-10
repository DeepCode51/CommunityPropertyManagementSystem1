package com.yx.util;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateTimeCom {




    /**
     * 计算两个时间点之间的天数
     */
    public static long getBetweenDay(Date jzsj) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(jzsj);					//放入Date类型数据
        int year=calendar.get(Calendar.YEAR);					//获取年份
        int month=calendar.get(Calendar.MONTH);					//获取月份
        int day=calendar.get(Calendar.DATE);
        LocalDate start = LocalDate.of(year,month,day);
        LocalDate now = LocalDate.now();
        return getBetweenDay(start, now);
    }

    /**
     * 计算两个时间点之间的天数
     */
    public static long getBetweenDay(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay();
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1 截至时间
     * @param date2 当前时间
     * @return
     */
    public static int getBetweenDay(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }


    }
