//package com.panther.cipher;
//
//import com.qunar.corp.publicservice.common.bean.Environment;
//import com.qunar.tc.core.info.api.KeyType;
//import com.qunar.tc.core.info.client.decrypt.InfoDecryptClient;
//import com.qunar.tc.core.info.client.decrypt.InfoDecryptClientImpl;
//import com.qunar.tc.core.info.client.encrypt.InfoEncryptClient;
//import com.qunar.tc.core.info.client.encrypt.InfoEncryptClientImpl;
//import com.qunar.tc.core.info.exception.DecryptFailException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
///**
// * 数据加密
// * Created by panther.dongdong on 2015/11/19.
// */
//public class Encrypt {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(Encrypt.class.getName());
//    private InfoEncryptClient infoEncryptClient;
//    private InfoDecryptClient infoDecryptClient;
//
//    public Encrypt(InfoEncryptClient infoEncryptClient, InfoDecryptClient infoDecryptClient) {
//        this.infoEncryptClient = infoEncryptClient;
//        this.infoDecryptClient = infoDecryptClient;
//    }
//
//    public InfoEncryptClient getInfoEncryptClient() {
//        return infoEncryptClient;
//    }
//
//    public void setInfoEncryptClient(InfoEncryptClient infoEncryptClient) {
//        this.infoEncryptClient = infoEncryptClient;
//    }
//
//    public InfoDecryptClient getInfoDecryptClient() {
//        return infoDecryptClient;
//    }
//
//    public void setInfoDecryptClient(InfoDecryptClient infoDecryptClient) {
//        this.infoDecryptClient = infoDecryptClient;
//    }
//
//    public static void main(String[] args) throws DecryptFailException, IOException {
//        InfoEncryptClient infoEncryptClient1 = new InfoEncryptClientImpl(Environment.DEV);
//        InfoDecryptClient infoDecryptClient1 = new InfoDecryptClientImpl(Environment.DEV);
//        Encrypt encrypt = new Encrypt(infoEncryptClient1, infoDecryptClient1);
//        String enresult = encrypt.getInfoEncryptClient().encrypt("8618611400087", KeyType.phone);
//        LOGGER.info("enresult = {}", enresult);
//        System.out.println(encrypt.getInfoEncryptClient().encrypt(enresult, KeyType.phone));
//        String t = encrypt.getInfoEncryptClient().encrypt(encrypt.getInfoEncryptClient().encrypt(enresult, KeyType.phone), KeyType.phone);
//        System.out.println(t);
////        String deresult = encrypt.getInfoDecryptClient().decrypt(enresult, KeyType.phone);
////        BASE64Decoder base64Decoder = new BASE64Decoder();
////        byte[] deresult = base64Decoder.decodeBuffer("SOZF");
////        for (byte b : deresult) {
////            LOGGER.info("{}", b);
////        }
//////        LOGGER.info("deresult = {}", deresult)""
//    }
//}
//
