package com.iss.wind.serevice.util;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class DateForPDFUtil {

    public static String transDate(String strDate) {

        //1. Create a Date from String
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //2. Test - Convert Date to Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date newDate = calendar.getTime();
        System.out.println("calendar-date:" + newDate);
        //3 get trans Date
        int year = calendar.get(Calendar.YEAR);//获取年份
        //int month = calendar.get(Calendar.MONTH) + 1;//获取月份
        int day = calendar.get(Calendar.DATE);//获取日
        //int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //int min = calendar.get(Calendar.MINUTE);
        String dayStr = day < 10 ? "0" + day : day + "";
        SimpleDateFormat sdfNew = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(sdfNew.format(newDate));
        Month monthName = localDate.getMonth();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String weekName = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String monName = monthName.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        return weekName + ", " + dayStr + " " + monName + " " + year;
    }

    public static String currentDate(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        String Date = simpleDateFormat.format(date);
        String transDate = transDate(Date);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
        simpleDateFormat1.setTimeZone(timeZone);
        String formatTime = simpleDateFormat1.format(date);
        String time = formatTime.substring(0, 5);
        return transDate + " at " + time;
    }

    public static String footerDate(Date date){
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    public static Boolean isPastDate(String str){
        boolean flag = false;
        Date nowDate = new Date();
        Date pastDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            pastDate = sdf.parse(str);
            if(pastDate.before(nowDate) || pastDate.equals(nowDate)){
                flag = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
