package com.panther.measurement;

import java.io.IOException;
import java.util.List;

/**
 * Created by panther.dongdong on 2016/1/18.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Deal deal = Deal.getInstance();
        deal.deal();
    }
}
