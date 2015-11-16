package com.panther.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在设计模式中对Builder模式的定义是用于构建复杂对象的一种模式，
 * 所构建的对象往往需要多步初始化或赋值才能完成。
 * 那么，在实际的开发过程中，我们哪些地方适合用到Builder模式呢？
 * 其中使用Builder模式来替代多参数构造函数是一个比较好的实践法则。
 * Created by panther.dongdong on 2015/11/16.
 */
public class Student {
    private static final Logger LOGGER = LoggerFactory.getLogger(Student.class.getName());

    private int id;
    private int age;
    private String name;
    private String sex;
    private String love;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public static Builder create() {
        return new Builder();
    }

    public Student(Builder builder) {
        this.id = builder.id;
        this.age = builder.age;
        this.name = builder.name;
        this.sex = builder.sex;
        this.love = builder.love;
        this.phone = builder.phone;
    }

    public static class Builder {

        private int id = 0;
        private int age = 0;
        private String name = null;
        private String sex = null;
        private String love = null;
        private String phone = null;

        //构建的步骤
        public Builder addId(int id) {
            this.id = id;
            return this;
        }

        public Builder addAge(int age) {
            this.age = age;
            return this;
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder addLove(String love) {
            this.love = love;
            return this;
        }

        public Builder addPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", love='" + love + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    /**
     * 测试生成Student
     *
     * @param args
     */
    public static void main(String[] args) {
        Student student = Student.create().addAge(18).addName("panther").addLove("play")
                .addPhone("110").addSex("男").build();
        LOGGER.info("{}", student.toString());
    }
}
