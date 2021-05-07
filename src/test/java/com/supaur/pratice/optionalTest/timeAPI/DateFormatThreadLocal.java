package com.supaur.pratice.optionalTest.timeAPI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {
    private final static ThreadLocal<DateFormat> df=new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    public static Date converse(String source) throws ParseException {
        return df.get().parse(source);
    }
}
