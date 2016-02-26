package com.panther.stack;

/**
 * JVM Stack Space use up
 * Created by panther.dongdong on 2016/1/11.
 */
public class StackSpaceTest {

    private static long count = 0;

    public static void main(String[] args) {
        test(1);
    }

    public static void test(long a) {
        System.out.println(count++);
        test(a);
    }
}
