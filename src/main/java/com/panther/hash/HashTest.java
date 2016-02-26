package com.panther.hash;

/**
 * hash test
 * Created by panther.dongdong on 2016/1/11.
 */
public class HashTest {
    public static void main(String[] args) {
//        h ^= (h >>> 20) ^ (h >>> 12);
//     h ^ (h >>> 7) ^ (h >>> 4);
        Integer integer = 1;
        System.out.println((integer.hashCode() >>> 20) ^ (integer.hashCode()));
    }
}
