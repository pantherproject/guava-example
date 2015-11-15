package com.panther.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Guava的文件写入
 * Guava的Files类中提供了几个write方法来简化向文件中写入内容的操作，
 * 下面的例子演示 Files.write(byte[],File)的用法。
 * Created by panther.dongdong on 2015/11/15.
 */
public class FileOperate {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileOperate.class.getName());
    private final static String OUTPUT_FILE = "./src/main/resources/file/test";

    public static void main(String[] args) {
        LOGGER.info("写入文件之前");
        demoFileWrite(OUTPUT_FILE, "这是写入的内容");
        LOGGER.info("写入文件之后");
        LOGGER.info("读文件之前");
        demoFileRead(OUTPUT_FILE);
        LOGGER.info("读文件之后");
    }

    /**
     * guava将信息写入到文件中的方法
     *
     * @param fileName 文件名字
     * @param contents 写入的内容
     */
    public static void demoFileWrite(final String fileName, final String contents) {
        checkNotNull(fileName, "Provided file name for writing must NOT be null.");
        checkNotNull(contents, "Unable to write null contents.");
        final File newFile = new File(fileName);
        try {
            Files.write(contents.getBytes(), newFile);
        } catch (Exception e) {
            LOGGER.error("ERROR trying to write to file '" + fileName + "' - "
                    + e.toString());
        }
    }


    public static void demoFileRead(final String testFilePath) {

        File testFile = new File(testFilePath);
        List<String> lines = null;
        try {
            lines = Files.readLines(testFile, Charsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("读入文件出现异常:{}", e.getMessage());
        }
        for (String line : lines) {
            LOGGER.info(line);
        }
    }
}
