package com.luv2code.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {
    @Override
    public String getFortune() throws InterruptedException {

//        simulate a delay

        TimeUnit.SECONDS.sleep(5);

//        return a fortune

        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean tripWire) throws InterruptedException {
        if (tripWire){
            throw  new RuntimeException("Major Accident! Highway is closed");
        }
        return getFortune();
    }
}
