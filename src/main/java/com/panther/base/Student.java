package com.panther.base;

import com.google.common.base.Objects;

/**
 * 测试Object.toStringHelper()的pojo
 * Created by panther.dongdong on 2015/11/16.
 */
public class Student {
    private int id;
    private String name;
    private String sex;
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", id)
                .add("name", name)
                .add("sex", sex)
                .add("age", age)
                .omitNullValues()
                .toString();
    }
}
