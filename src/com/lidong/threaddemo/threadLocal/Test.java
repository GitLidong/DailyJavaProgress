package com.lidong.threaddemo.threadLocal;

import java.util.Date;

import static com.lidong.threaddemo.threadLocal.DateUtilThreadLocal.MDHMSS;

public class Test {

    public static void main(String[] args) {
        DateUtilThreadLocal.formatDate(new Date(), MDHMSS);

        new Thread(() -> {
            DateUtilThreadLocal.formatDate(new Date(), MDHMSS);
        }).start();

        new Thread(() -> {
            DateUtilThreadLocal.formatDate(new Date(), MDHMSS);
        }).start();

    }
}
