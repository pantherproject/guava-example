package com.panther.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * guava的一些简单应用
 * 预先判断Preconditions
 * Object.toStringHelper()
 * Stopwatch(计时器)
 * CharMatcher(字符匹配)
 * String Joining 字符串连接
 * String Splitting字符串分割
 * Created by panther.dongdong on 2015/11/16.
 */
public class BaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class.getName());

    public static void main(final String[] args) {

        final Stopwatch stopwatch = Stopwatch.createUnstarted().start();
        LOGGER.info("原生类型判断开始");
        Double b = 2.0;
        setRating(b);
        LOGGER.info("原生类型判断结束");
        LOGGER.info("guava判断开始");
        setRatingGuava(b);
        LOGGER.info("guava判断结束");
        LOGGER.info("guava字符匹配开始");
        matchGuava();
        LOGGER.info("guava字符匹配结束");
        LOGGER.info("guava字符串连接接结束");
        joinGuava();
        LOGGER.info("guava字符串连接结束");
        LOGGER.info("guava字符串分割结束");
        splitGuava();
        LOGGER.info("guava字符串分割结束");
        LOGGER.info("Object.toStringHelper()测试开始");
        LOGGER.info(new Student().toString());
        LOGGER.info("Object.toStringHelper()测试结束");
        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        LOGGER.info("总共耗时:{}", nanos);
    }

    /**
     * 原生类型的判断
     *
     * @param rating double参数
     */
    public static void setRating(Double rating) {
        if (rating == null) {
            throw new NullPointerException();
        }
        LOGGER.info("r = {}", rating);
    }

    /**
     * 使用guava后代码更整洁
     *
     * @param rating double参数
     */
    public static void setRatingGuava(Double rating) {
        Double r = checkNotNull(rating);
        LOGGER.info("r = {}", r);
    }

    /**
     * guava字符匹配
     * todo see
     */
    public static void matchGuava() {
        String userInput = "nihao1234-1";
        CharMatcher ID_MATCHER = CharMatcher.DIGIT.or(CharMatcher.is('-')).or(CharMatcher.JAVA_LOWER_CASE);
        LOGGER.info(ID_MATCHER.retainFrom(userInput));
    }

    /**
     * 字符串链接
     * String Joining 字符串连接
     * 可以快速地把一组字符数组连接成为用特殊符合连接的一个字符串，
     * 如果这组字符中有null值的话，我们可以使用skipNulls或是useForNull来控制我们要输出的内容。
     */
    public static void joinGuava() {
        Joiner JOINER = Joiner.on(",").useForNull("null");
        Joiner JOINER1 = Joiner.on(",").skipNulls();

        String str = JOINER.join("hello", "world", null, "qiyadeng");
        String str1 = JOINER1.join("hello", "world", null, "qiyadeng");

        //hello,world,null,qiyadeng
        LOGGER.info("str is : {}", str);
        LOGGER.info("str1(不带null) is : {}", str1);
    }

    /**
     * String Splitting字符串分割
     * 有这样一组字符串”hello,,qiyadeng,com,”
     * 我们用split(“,”)分割字符串,
     * 得到的结果是["hello","","qiyaeng","com"]
     * ，但是我们如果希望的是把空值去掉，还需要另外处理，
     * 使用guava的Splitter可以简单做到。
     */

    public static void splitGuava() {
        Iterable<String> splitStr = Splitter.on(',').trimResults().omitEmptyStrings().split("hello,,qiyadeng,com");
        for (String string : splitStr) {
            LOGGER.info(string);
        }
    }
}

/**
 * guava中的计时器
 * Stopwatch stopwatch = new Stopwatch().start();
 * //do something test
 * for (int i = 0; i < 10000; i++) {
 * }
 * long nanos = stopwatch.elapsed(TimeUnit.NANOSECONDS);
 * System.out.println(nanos);
 */
