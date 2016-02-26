package com.panther.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务 demo
 * Created by panther.dongdong on 2015/12/3.
 */
public class TimerDemo {
    public static void main(String[] args) {
        time1();
    }


    public static void time1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("------设定任务test----");
            }
        }, 5000);
    }

    public static void time2() {

    }


}
