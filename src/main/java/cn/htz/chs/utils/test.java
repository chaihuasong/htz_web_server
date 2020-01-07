package cn.htz.chs.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class test {
    public static void main(String[] args) {
        String date = "2019/12/28";
        String date2 = "2019-12-28";
        System.out.println(strToFullDate(date).toString());
        System.out.println(strToDate(date2).toString());
        System.out.println(new Random().nextInt(3));
        for (int i = 0; i < 51; i++) {
            System.out.println(i / 17);
        }
    }

    public static Date strToFullDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        try {
            Date date = formatter.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date strToDate(String strDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        try {
            Date date = formatter.parse(strDate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
