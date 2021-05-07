package com.supaur.pratice.optionalTest.timeAPI;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.SimpleFormatter;

public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task=new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                //return DateFormatThreadLocal.converse("20210110");
                return LocalDate.parse("20210110",dateTimeFormatter);
            }
        };
        ExecutorService executorService= Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results=new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            results.add(executorService.submit(task));
        }
        for (Future<LocalDate> future:results) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }
}
