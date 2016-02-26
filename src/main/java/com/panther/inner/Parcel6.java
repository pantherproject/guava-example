package com.panther.inner;

/**
 * Created by panther.dongdong on 2016/1/14.
 */
public class Parcel6 {
    private void internalTracking(boolean b) {
        if (b) {
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    this.id = s;
                }

                String getSlip() {
                    return id;
                }
            }
            TrackingSlip trackingSlip = new TrackingSlip("slip");
            String s = trackingSlip.getSlip();
            System.out.println(s);
        }
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel6 parcel6 = new Parcel6();
        parcel6.track();
    }
}
