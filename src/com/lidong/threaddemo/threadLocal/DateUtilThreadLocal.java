package com.lidong.threaddemo.threadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class DateUtilThreadLocal {

    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap =
            new HashMap<>();

    private static Logger logger = Logger.getLogger(DateUtilThreadLocal.class.getName());

    public final static String MDHMSS = "MMddHHmmssSSS";
    public final static String YMDHMS = "yyyyMMddHHmmss";
    public final static String YMDHMS_ = "yyyy-MM-dd HH:mm:ss";
    public final static String YMD = "yyyyMMdd";
    public final static String YMD_ = "yyyy-MM-dd";
    public final static String HMS = "HHmmss";


    public static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> sdfThread = sdfMap.get(pattern);
        if (sdfThread == null) {
            synchronized (DateUtilThreadLocal.class) {
                sdfThread = sdfMap.get(pattern);
                if (sdfThread == null) {
                    logger.info("put new sdf of pattern " + pattern + " to map");
                    sdfThread = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            logger.info("thread: " + Thread.currentThread() + " init pattern: " + pattern);
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, sdfThread);
                }
            }
        }
        return sdfThread.get();
    }

    public static Date parseDate(String date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        try {
            return getSdf(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.info("解析的格式不支持:" + pattern);
        }
        return null;
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return getSdf(pattern).format(date);
        }
    }

}
