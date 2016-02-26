package com.panther.inner;

/**
 * Created by panther.dongdong on 2016/1/13.
 */
public class Parcel3 {
    class Content {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            this.label = whereTo;
        }


        String readLabel() {
            return label;
        }
    }

    public static void main(String[] args) {
        Parcel3 parcel3 = new Parcel3();
        Parcel3.Content c = parcel3.new Content();
        Parcel3.Destination destination = parcel3.new Destination("panther");
        System.out.println(destination.readLabel());
        System.out.println(destination.label);
        System.out.println(parcel3.toString());

    }

}
