package com.minnthitoo.spring_webflux;

public class WaitUntail {
    public static void wait(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
