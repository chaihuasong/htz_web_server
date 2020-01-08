package cn.htz.chs.utils;

import cn.htz.chs.ddz.Person;
import com.alibaba.fastjson.JSON;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    public static void main(String[] args) {
        String date = "2019/12/28";
        String date2 = "2019-12-28";
        System.out.println(strToFullDate(date).toString());
        System.out.println(strToDate(date2).toString());
        Map<String, Person> info = new HashMap<>();
        for(int i = 0; i < 3; i++) {
            Person p = new Person();
            p.setUnionId("unionId" + i);
            info.put("pos" + i, p);
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(info);
        String str = jsonArray.get(0).toString();
        System.out.println(str);
        System.out.println(JSON.parseObject(str).get("pos0"));
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
