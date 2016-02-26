package com.panther.inner;

import com.panther.inner.inter.Contents;
import com.panther.inner.inter.Destination;

/**
 * Created by panther.dongdong on 2016/1/13.
 */
public class Parcel4 {

    //内部类
    private class PContents implements Contents {

        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }


    //内部类
    protected class PDestination implements Destination {

        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    //指向内部类的引用
    public Destination destination(String s) {
        return new PDestination(s);
    }


    //指向内部类的引用
    public Contents contents() {
        return new PContents();
    }

    public static void main(String[] args) {
        Parcel4 parcel4 = new Parcel4();
        Contents contents = parcel4.contents();
        Destination destination = parcel4.destination("panther");

    }
}
