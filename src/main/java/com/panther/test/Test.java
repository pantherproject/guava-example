package com.panther.test;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

/**
 * test(maven项目建立成功的测试)
 * Created by panther.dongdong on 2015/11/13.
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class.getName());
    private static final String FILE_IN = "D://in.txt";
    private static final String TYPE_IN = "D://type.txt";
    private static final String FILE_OUT = "D://template.txt";

    public static void main(String[] args) throws InterruptedException, IOException {
//        demoFileRead(FILE_IN, TYPE_IN);
//        java.sql.Date date = new java.sql.Date("2013");
//        System.out.println(date);
//        n
//        String a = "123";
//        String b = new String("123");
        List<String> paramList = Lists.newArrayList("1", "2", "3", "4", "5");
        List<String> temp = paramList.subList(2, 4);
        for (String s : temp) {
            System.out.println(s);
        }
    }

    public static void demoFileRead(final String testFilePath, final String TYPE_IN) throws InterruptedException, IOException {

        File testFile = new File(testFilePath);
        File typeFile = new File(TYPE_IN);
        List<String> lines = null;
        List<String> types = null;
        try {
            lines = Files.readLines(testFile, Charsets.UTF_8);
            types = Files.readLines(typeFile, Charsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("读入文件出现异常:{}", e.getMessage());
        }
        int i = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(FILE_OUT)));
        for (String type : types) {
            for (String s : lines) {
                String[] template = s.split("#########");
                String content = template[5].replaceAll("s%", "%s");
                String insert = "INSERT INTO template_assistant(account, tem_name, applicant,auditor, groupname, tem_content, type, tem_variabe, status, applytime, update_time,rejectreason, remark, use_status, business_type) values(";
                insert += "'" + type + "','','" + template[2] + "','panther.dongdong','" + template[0] + "','" + content + "',1,'" + template[4] + "',1,'2015-12-18 16:24:00','2015-12-18 16:24:00','','plane',0,'" + template[3] + "');";
                System.out.println(insert);
                ++i;
                writer.write(insert + "\n");
            }
        }
        System.out.println(i);
        writer.close();

    }
}
