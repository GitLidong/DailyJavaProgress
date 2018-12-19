package com.lidong.threaddemo.threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private final static SimpleDateFormat sdfyhm =
            new SimpleDateFormat("yyyyMMdd");


    //使用 synchronized 来保障同步，重量级锁，
    
    //改进
    //线程不安全是源于多线程使用了共享变量造成
    // 所以这里使用ThreadLocal来给每个线程单独创建副本变量
    public synchronized static Date parseymdhms(String source) {
        try {
            return sdfyhm.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
