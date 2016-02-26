//package com.panther.file;
//
//import com.google.common.base.Charsets;
//import com.google.common.collect.Lists;
//import com.google.common.io.Files;
//import com.qunar.sms.SMS;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.util.Date;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static com.google.common.base.Preconditions.*;
//
///**
// * Guava的文件写入
// * Guava的Files类中提供了几个write方法来简化向文件中写入内容的操作，
// * 下面的例子演示 Files.write(byte[],File)的用法。
// * Created by panther.dongdong on 2015/11/15.
// */
//public class FileOperate {
//    private static final Logger LOGGER = LoggerFactory.getLogger(FileOperate.class.getName());
//    private final static String OUTPUT_FILE = "D://result.txt";
//    private final static String INPUT_FILE = "D://phone.txt";
//
//    public static void main(String[] args) throws InterruptedException {
////        LOGGER.info("写入文件之前");
////        demoFileWrite(OUTPUT_FILE, "这是写入的内容");
////        LOGGER.info("写入文件之后");
////        LOGGER.info("读文件之前");
////        demoFileRead(OUTPUT_FILE);
////        LOGGER.info("读文件之后");
//        demoFileRead(INPUT_FILE);
////        System.setProperty("SMS.SERVICE.URL", "http://l-sms6.wap.cn1.qunar.com:8080/mon/req");
////        int result = SMS.send("qunar_train", new Date(), new String[]{"8618611400087","8613552735578"}, "您的火车票未能出票，因您的12306账号处于注册审核中被限制使用，解决办法请点击：http://d.qunar.com/Bn44cg。总金额136.00元将在15个工作日内到账。", null);
////        System.out.println(result);
////        String s = "您的火车票未能出票，因您的12306账号处于注册审核中被限制使用，解决办法请点击：http://d.qunar.com/Bn44cg。总金额136.00元将在15个工作日内到账。";
////        String reegx  = "您的火车票未能出票，因您的12306账号处于注册审核中被限制使用，解决办法请点击：%s。总金额%s元将在15个工作日内到账。";
////        reegx = reegx.replace("%s",".*");
////        System.out.println(s.matches(reegx));
////        Pattern pattern = Pattern.compile(reegx);
////        Matcher matcher = pattern.matcher(s);
////        System.out.println(matcher.find());
//    }
//
//    /**
//     * guava将信息写入到文件中的方法
//     *
//     * @param fileName 文件名字
//     * @param contents 写入的内容
//     */
//    public static void demoFileWrite(final String fileName, final String contents) {
//        checkNotNull(fileName, "Provided file name for writing must NOT be null.");
//        checkNotNull(contents, "Unable to write null contents.");
//        final File newFile = new File(fileName);
//        try {
//            Files.write(contents.getBytes(), newFile);
//        } catch (Exception e) {
//            LOGGER.error("ERROR trying to write to file '" + fileName + "' - "
//                    + e.toString());
//        }
//    }
//
//
////    public static void demoFileRead(final String testFilePath) throws InterruptedException {
////
////        File testFile = new File(testFilePath);
////        List<String> lines = null;
////        try {
////            lines = Files.readLines(testFile, Charsets.UTF_8);
////        } catch (Exception e) {
////            LOGGER.error("读入文件出现异常:{}", e.getMessage());
////        }
////        int result = -1;
////        List<Message> messageList = Lists.newArrayList();
////        for (String s : lines) {
////            String messagearray[] = s.split("##############");
////            Message message = new Message();
////            message.setPhone(messagearray[0]);
////            message.setMessgae(messagearray[1]);
////            message.setMessgae(message.getMessgae().replaceFirst("####", messagearray[2]));
////            message.setMessgae(message.getMessgae().replaceFirst("####", messagearray[3]));
////            message.setMessgae(message.getMessgae().replaceFirst("####", messagearray[4]));
////            messageList.add(message);
////        }
////
////
////        for (Message message : messageList) {
////            LOGGER.info("{},{}", message.getMessgae(), message.getPhone());
////            result = SMS.send("qv_pay_tuiguang", new Date(), new String[]{message.getPhone()}, message.getMessgae(), null);
////            LOGGER.info("result = {},phone = {},messgae = {}", result, message.getPhone(), message.getMessgae());
////        }
////        StringBuilder log = new StringBuilder();
////        for (String s : lines) {
////            s = s.replace("###606", "");
//////            LOGGER.info(s);
////            result = SMS.send("qunar_tuiguang_hotel", new Date(), new String[]{s}, "【去哪儿50元返现】去哪儿携手陆金所返现！2015年12月31日前成功注册陆金所并投资1元理财产品，得50元返现，可提现（10日内返到去哪儿账户）。年化收益率>4.5%，随时提取（T+1到账）。转发无效，入口http://t.lu.com/r/Nan8y", null);
////            LOGGER.info("result = {},phone = {}", result, s);
////            Thread.sleep(100);
////            log.append(s).append("###").append(result).append("\n");
////
////        }
////        demoFileWrite(OUTPUT_FILE, log.toString());
////        LOGGER.info("{}", lines.size());
////    }
//
//
//    public static void demoFileRead(final String testFilePath) throws InterruptedException {
//
//        File testFile = new File(testFilePath);
//        List<String> lines = null;
//        try {
//            lines = Files.readLines(testFile, Charsets.UTF_8);
//        } catch (Exception e) {
//            LOGGER.error("读入文件出现异常:{}", e.getMessage());
//        }
//        int result = -1;
//        StringBuilder log = new StringBuilder();
//        for (String s : lines) {
////            LOGGER.info(s);
////            result = SMS.send("qunar_tuiguang", new Date(), new String[]{s}, "200元现金等你来拿，可提现！再送旅行大礼包，订酒店立享5折起，戳http://d.qunar.com/IJbIBn", null);
////            LOGGER.info("result = {},phone = {}", result,s);
////            Thread.sleep(20);
////        log.append(s).append("###").append(result).append("\n");
//        }
//        LOGGER.info("总共:{}条", lines.size());
//        demoFileWrite(OUTPUT_FILE, log.toString());
//
////        result = SMS.send("qunar_tuiguang_hotel", new Date(), new String[]{"8618611400087"}, "200元现金等你来拿，可提现！再送旅行大礼包，订酒店立享5折起~戳http://d.qunar.com/IJbIBn", null);
//    }
//}
//
