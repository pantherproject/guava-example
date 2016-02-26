package com.panther.cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

/**
 * Created by panther.dongdong on 2015/11/19.
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode("8618611400087".getBytes());

    }
}
