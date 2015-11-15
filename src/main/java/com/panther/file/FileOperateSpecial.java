package com.panther.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * 当读取的文件太大就不能直接放入内存中，这样会把内存充爆
 * 出现OutofMemoryError
 * 大文件处理可以使用readLines方法的另一个重载。
 * 下面的例子演示从一个大文件中逐行读取文本，并做行号计数。
 * Created by panther.dongdong on 2015/11/15.
 */
public class FileOperateSpecial {
    private final static String INPUT_FILE = "./src/main/resources/file/read.txt";
    private final static Logger LOGGER = LoggerFactory.getLogger(FileOperateSpecial.class.getName());

    static class ReadLine implements LineProcessor<Integer> {

        private Integer totalRow = 0;

        @Override
        public boolean processLine(String line) throws IOException {
            LOGGER.info("文件的信息是:{}", line);
            totalRow++;
            return true;
        }

        @Override
        public Integer getResult() {
            return totalRow;
        }
    }

    public static void main(String[] args) {
        File testFile = new File(INPUT_FILE);
        ReadLine counter = new ReadLine();
        try {
            Files.readLines(testFile, Charsets.UTF_8, counter);
        } catch (Exception e) {
            LOGGER.error("读取文件发生异常:{},异常信息:{}", e, e.getMessage());
        }
        LOGGER.info("文件的总行数:{}", counter.getResult());
    }
}

/**
 * 总结：
 * 这个readLines的重载，需要我们实现一个LineProcessor的泛型接口，
 * 在这个接口的实现方法processLine方法中我们可以对行文本进行处理，
 * getResult方法可以获得一个最终的处理结果，这里我们只是简单的返回了一个行计数。
 * 另外还有readBytes方法可以对文件的字节做处理，
 * readFirstLine可以返回第一行的文本，
 * Files.toString(File,Charset)可以返回文件的所有文本内容。
 */
