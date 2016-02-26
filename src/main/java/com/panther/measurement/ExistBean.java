package com.panther.measurement;

/**
 * 已经存在ChannelStatistic中的数据
 * Created by panther.dongdong on 2016/1/18.
 */
public class ExistBean {
    private int id;
    private String type; //===type===账户e
    private String numtype;//====numbertype===号码类型e
    private String channel;//=======channel======通道e
    private String sent; //total=========提交的发送量+
    private String delivrd; //deliver====达到量+
    private String undelivrd;//undeliver===未到达量+
    private String nonecode;//nonecode未知量+
    private String senddate = "2016-01-13 00:00:00";//发送日期
    private String sented;//sented====已经发送条数+
    private String nosent;//nosent===未提交的数量+
    private String charges;
    private String nocharges;
    private String sendfail;
    private String gwconfig;//gwconfig======gwconfig=====网关e
    private String countrysign;//countrysign===国家简称e


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumtype() {
        return numtype;
    }

    public void setNumtype(String numtype) {
        this.numtype = numtype;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getDelivrd() {
        return delivrd;
    }

    public void setDelivrd(String delivrd) {
        this.delivrd = delivrd;
    }

    public String getUndelivrd() {
        return undelivrd;
    }

    public void setUndelivrd(String undelivrd) {
        this.undelivrd = undelivrd;
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

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public String getNocharges() {
        return nocharges;
    }

    public void setNocharges(String nocharges) {
        this.nocharges = nocharges;
    }

    public String getSendfail() {
        return sendfail;
    }

    public void setSendfail(String sendfail) {
        this.sendfail = sendfail;
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

    @Override
    public String toString() {
        return sent + "########" + channel + "########" + gwconfig + "########" + countrysign + "########" + numtype + "########" + delivrd
                + "########" + undelivrd + "########" + nonecode + "########" + senddate + "########" + type + "########" + sented + "########"
                + nosent;
    }
}
