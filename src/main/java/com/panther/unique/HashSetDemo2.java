package com.panther.unique;

import com.panther.base.Student;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * hashset unique demo
 * Created by panther.dongdong on 2016/1/11.
 */
public class HashSetDemo2 {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(new Student1("zhang", 22));
        set.add(new Student1("wang", 23));
        set.add(new Student1("lee", 24));
        set.add(new Student1("sun", 25));
        set.add(new Student1("lee", 24));
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }
}

class Student1 {
    private String name;
    private int age;

    public Student1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "name= " + name + " age=" + age;
    }

    @Override
    public int hashCode() {
//        System.out.println(this.name + "...hashCode");
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
//        System.out.println(this.name + "...equal()");
        Student1 student = (Student1) obj;
        return this.name.equals(student.getName()) && this.age == student.getAge();
    }
}
