package com.panther;

import com.google.common.collect.Maps;
import javassist.bytecode.stackmap.BasicBlock;

import java.util.Date;
import java.util.Map;

/**
 * Created by panther.dongdong on 2015/12/29.
 */
public class Main {
    public static void main(String[] args) {
//        String a = "123";
//        String date = "2016-10-12-00:00:00";
//        System.out.println(date.substring(0, 10));
//        Date date1 = new Date();
//        System.out.println(date1);
//        String date = "2016-01-20";
//        Date
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put("uid", "sms_itf_01");
        paramMap.put("psw", "faef7c6eb7cff68467bee4eee66a3f11");
        paramMap.put("type", "qunar_train");
        paramMap.put("start", "2015-11-01");
        paramMap.put("mobile", "8618611400087");

        try {
            String pattern = "在哪啊(）";
            String test = "test";
            System.out.println(test.matches(pattern));
        } catch (Exception e) {
            System.out.println("success");
        }
    }
}
