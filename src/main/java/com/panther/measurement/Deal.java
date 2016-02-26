package com.panther.measurement;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * Created by panther.dongdong on 2016/1/18.
 */
public class Deal {
    private static final Logger LOGGER = LoggerFactory.getLogger(Deal.class);
    private static final String SPLIT_SEPARATOR = "########";

    private static final String IN_FILE_OLD = "./src/main/resources/2016-01-13.old";
    private static final String IN_FILE_EXIST = "./src/main/resources/2016-01-13";
    private static final String OUT_FILE = "./src/main/resources/2016-01-13.result";
    private static final Deal instance = new Deal();

    public static Deal getInstance() {
        return instance;
    }

    public List<Bean> deal() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(OUT_FILE)));
        List<Bean> beanList = Lists.newArrayList();
        //todo 读IN_FILE_OLD文件 List<Bean>
        List<Bean> oldBeanList = getBeans();
        //todo 读IN_FILE_EXIST文件 List<ExistBean>
        List<ExistBean> existBeanList = getExistBeans();
        //todo 根据channel，gwconfig，countrysign，numbertype，type相等的将(total+sent) as sent,(deliver+deliver) deliver,
        //todo  (undeliver+undeliver) as undeliver,(nonecode+nonecode) as nonecode,(sented+sented) as sented,(nosent+nosent) as nosent
        //todo 得到结果
        getResult(oldBeanList, existBeanList, bufferedWriter);
//        System.out.println("##############################################################################################################");
//        bufferedWriter.write("##############################################################################################################" + "\n");
        getadd(oldBeanList, existBeanList, bufferedWriter);
        bufferedWriter.close();
        return beanList;
    }


    /**
     * 从存在的数据中找出相应的数据
     *
     * @param oldBeanList
     * @param existBeanList
     */
    private void getResult(List<Bean> oldBeanList, List<ExistBean> existBeanList, BufferedWriter bufferedWriter) throws IOException {
        for (ExistBean existBean : existBeanList) {
            if (check(existBean, oldBeanList) == null) {
//                System.out.println(existBean.toString());
                bufferedWriter.write(existBean.toString() + "\n");
            } else {
                System.out.println(check(existBean, oldBeanList).toString());
                bufferedWriter.write(check(existBean, oldBeanList).toString() + "\n");
            }
        }
    }


    /**
     * 从新加的数据找出有价值的数据
     *
     * @param oldBeanList
     * @param existBeanList
     */
    public void getadd(List<Bean> oldBeanList, List<ExistBean> existBeanList, BufferedWriter bufferedWriter) throws IOException {
        for (Bean bean : oldBeanList) {
            if (!check(bean, existBeanList)) {
//                System.out.println(bean.toString());
                bufferedWriter.write(bean.toString() + "\n");
            }
        }
    }


    public boolean check(Bean bean, List<ExistBean> existBeanList) {
        for (ExistBean existBean : existBeanList) {
            if (bean.getChannel().equals(existBean.getChannel()) && bean.getGwconfig().equals(existBean.getGwconfig()) && bean.getCountrysign().equals(existBean.getCountrysign()) && bean.getNumbertype().equals(existBean.getNumtype()) && bean.getType().equals(existBean.getType())) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return
     */
    private Bean check(ExistBean existBean, List<Bean> oldBeanList) {
        Bean result = null;
        for (Bean bean : oldBeanList) {
            if (bean.getChannel().equals(existBean.getChannel()) && bean.getGwconfig().equals(existBean.getGwconfig()) && bean.getCountrysign().equals(existBean.getCountrysign()) && bean.getNumbertype().equals(existBean.getNumtype()) && bean.getType().equals(existBean.getType())) {
                result = new Bean();
                result.setTotal(bean.getTotal() + Integer.parseInt(existBean.getSent()));
                result.setChannel(bean.getChannel());
                result.setGwconfig(bean.getGwconfig());
                result.setCountrysign(bean.getCountrysign());
                result.setNumbertype(bean.getNumbertype());
                result.setType(bean.getType());
                result.setDeliver(String.valueOf(Integer.parseInt(bean.getDeliver()) + Integer.parseInt(existBean.getDelivrd())));
                result.setUndeliver(String.valueOf(Integer.parseInt(bean.getUndeliver()) + Integer.parseInt(existBean.getUndelivrd())));
                result.setNonecode(String.valueOf(Integer.parseInt(bean.getNonecode()) + Integer.parseInt(existBean.getNonecode())));
                result.setSenddate(bean.getSenddate());
                result.setSented(String.valueOf(Integer.parseInt(bean.getSented()) + Integer.parseInt(existBean.getSented())));
                result.setNosent(String.valueOf(Integer.parseInt(bean.getNosent()) + Integer.parseInt(existBean.getNosent())));
            }
        }
        return result;
    }


    /**
     * 得到beans from IN_FILE_OLD
     *
     * @return List
     */
    public List<Bean> getBeans() {
        List<Bean> beanList = Lists.newArrayList();
        List<String> stringList = null;
        try {
            stringList = Files.readLines(new File(IN_FILE_OLD), Charsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
        if (stringList != null) {
            for (String s : stringList) {
                String temp[] = s.split(SPLIT_SEPARATOR);
                Bean bean = new Bean();
                bean.setTotal(Integer.parseInt(temp[0])); //提交的发送量
                bean.setChannel(temp[1]); //通道
                bean.setGwconfig(temp[2]); //网关
                bean.setCountrysign(temp[3]); //国家简称
                bean.setNumbertype(temp[4]); //号码类型
                bean.setDeliver(temp[5]); //达到量
                bean.setUndeliver(temp[6]);//未到达量
                bean.setNonecode(temp[7]);//未知量
                bean.setSenddate(temp[8]); //发送日期
                bean.setType(temp[9]);//账户类型
                bean.setSented(temp[10]);//已经发送条数
                bean.setNosent(temp[11]);//未提交的数量
                beanList.add(bean);
            }
        }
        return beanList;
    }


    /**
     * 得到已经存在的bean
     *
     * @return
     */
    public List<ExistBean> getExistBeans() {
        List<ExistBean> existBeanList = Lists.newArrayList();
        List<String> stringList = null;
        try {
            stringList = Files.readLines(new File(IN_FILE_EXIST), Charsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
        if (stringList != null) {
            for (String s : stringList) {
                String temp[] = s.split(SPLIT_SEPARATOR);
                ExistBean existBean = new ExistBean();
                existBean.setId(Integer.parseInt(temp[0]));
                existBean.setType(temp[1]);
                existBean.setNumtype(temp[2]);
                existBean.setChannel(temp[3]);
                existBean.setSent(temp[4]);
                existBean.setDelivrd(temp[5]);
                existBean.setUndelivrd(temp[6]);
                existBean.setNonecode(temp[7]);
                existBean.setSenddate(temp[8]);
                existBean.setSented(temp[9]);
                existBean.setNosent(temp[10]);
                existBean.setCharges(temp[11]);
                existBean.setNocharges(temp[12]);
                existBean.setSendfail(temp[13]);
                existBean.setGwconfig(temp[14]);
                existBean.setCountrysign(temp[15]);
                existBeanList.add(existBean);
            }
        }
        return existBeanList;
    }
}
