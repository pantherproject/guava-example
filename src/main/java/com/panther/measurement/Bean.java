package com.panther.measurement;

/**
 * Created by panther.dongdong on 2016/1/18.
 */
public class Bean {
    private int total;  //=========sent=========提交的发送量+
    private String channel; //=======channel======通道e
    private String gwconfig; //======gwconfig=====网关e
    private String countrysign; //===countrysign===国家简称e
    private String numbertype; //====numtype===号码类型e
    private String deliver;  //====delivrd====达到量+
    private String undeliver;   //===undelivrd===未到达量+
    private String nonecode; //====nonecode===未知量+
    private String senddate = "2016-01-13 00:00:00"; //2016-01-13 00:00:00
    private String type;    //====type====账户e
    private String sented;   //===sented====已经发送条数+
    private String nosent;   //===nosent===未提交的数量+


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getGwconfig() {
        return gwconfig;
    }

    public void setGwconfig(String gwconfig) {
        this.gwconfig = gwconfig;
    }

    public String getCountrysign() {
        return countrysign;
    }

    public void setCountrysign(String countrysign) {
        this.countrysign = countrysign;
    }

    public String getNumbertype() {
        return numbertype;
    }

    public void setNumbertype(String numbertype) {
        this.numbertype = numbertype;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public String getUndeliver() {
        return undeliver;
    }

    public void setUndeliver(String undeliver) {
        this.undeliver = undeliver;
    }

    public String getNonecode() {
        return nonecode;
    }

    public void setNonecode(String nonecode) {
        this.nonecode = nonecode;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSented() {
        return sented;
    }

    public void setSented(String sented) {
        this.sented = sented;
    }

    public String getNosent() {
        return nosent;
    }

    public void setNosent(String nosent) {
        this.nosent = nosent;
    }


    @Override
    public String toString() {
        return total + "########" + channel + "########" + gwconfig + "########" + countrysign + "########" + numbertype
                + "########" + deliver + "########" + undeliver + "########" + nonecode + "########" + senddate + "########" + type + "########" + sented
                + "########" + nosent;
    }
}
