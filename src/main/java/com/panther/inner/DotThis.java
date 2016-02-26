package com.panther.inner;

/**
 * Created by panther.dongdong on 2016/1/13.
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            return DotThis.this;
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dotThis = new DotThis(); //new 一个外围类
        DotThis.Inner dti = dotThis.inner(); //new一个内部类
        dti.outer().f(); //通过内部类生成对外围类的应用，调用外围类方法
    }
}
