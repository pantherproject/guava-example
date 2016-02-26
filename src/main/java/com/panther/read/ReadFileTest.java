package com.panther.read;

import qunar.agile.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 火车票上行补发短信sql的生成
 * Created by panther.dongdong on 2016/1/13.
 */
public class ReadFileTest {
    private final static String INPUT_FILE = "./src/main/resources/qunar.txt";

    public static void main(String[] args) throws IOException {

        String[] stringList = Files.readLines(new File(INPUT_FILE));
        System.out.println(stringList.length);
        for (int i = 0; i < stringList.length; i += 3) {
            String temp0[] = stringList[i].split("########");
            String temp1[] = stringList[i + 1].split("########");
            String temp2[] = stringList[i + 2].split("########");
//            System.out.println(temp0[0].trim().equals(temp2[0].trim()));
//            System.out.println(temp0[5] + temp1[5] + temp2[5]);
            String insert = "insert into train_inbox(receiveTime, mobile, phone, message, retry, pushTime, operator, status, report, hostname)" +
                    "values(now(),'" + temp0[0] + "','106905998080','" + (temp0[3].trim() + temp1[3].trim() + temp2[3].trim()) + "','0'," +
                    "now(), '百悟科技', '0', '0', 'l-sms2.wap.cn6');";
//            System.out.println(insert);
            System.out.println(temp0[0]);
        }
    }
}
