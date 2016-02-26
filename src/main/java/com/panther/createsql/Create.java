package com.panther.createsql;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.*;
import java.util.List;

/**
 * Created by panther.dongdong on 2016/1/19.
 */
public class Create {
    private static final String SPLIT_SEPARATOR = "########";

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("./src/main/resources/sql.2016-01-13")));
        List<String> stringList = Files.readLines(new File("./src/main/resources/2016-01-13.result"), Charsets.UTF_8);
        String finalSQL = "INSERT INTO ChannelStatistic" +
                "(sent, channel, gwconfig, countrysign, numtype, delivrd, undelivrd, nonecode, senddate, type, sented, nosent) values";
        for (String s : stringList) {
            String temp[] = s.split(SPLIT_SEPARATOR);
            finalSQL += "('" + temp[0] + "','" + temp[1] + "','" + temp[2] + "','" + temp[3] + "','" + temp[4] + "','" + temp[5] + "','"
                    + temp[6] + "','" + temp[7] + "','" + temp[8] + "','" + temp[9] + "','" + temp[10] + "','" + temp[11] + "'),";
        }
        bufferedWriter.write(finalSQL);
        bufferedWriter.close();
        System.out.println(finalSQL);
    }
}
