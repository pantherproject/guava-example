package com.panther.file;

/**
 * Created by panther.dongdong on 2015/11/27.
 */
public class Message {
    private String phone;
    private String messgae;
    private int amount;
    private String cardNo;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        String s = "13512784551##############尊敬的客户， 感谢您参加去哪儿网&万豪-丽思卡尔顿酒店活动，获得####元旅行卡。卡号：####，密码：#### 请注册/登录去哪儿后，在礼品卡页面（http://card.qunar.com/ ）领使，如有疑问电询 400-819-0100##############800##############;8661087903000963039##############902551\n";
        System.out.println(s.split("##############")[1]);
    }
}
