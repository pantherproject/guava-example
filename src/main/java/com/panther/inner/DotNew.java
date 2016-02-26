package com.panther.inner;

/**
 * Created by panther.dongdong on 2016/1/13.
 */
public class DotNew {
    public class Inner {

    }

    public static void main(String[] args) {
        DotNew dotNew = new DotNew();
        DotNew.Inner dni = dotNew.new Inner();
    }
}
