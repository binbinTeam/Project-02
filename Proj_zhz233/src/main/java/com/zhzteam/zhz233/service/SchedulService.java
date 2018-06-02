package com.zhzteam.zhz233.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulService {
    @Scheduled(cron = "0/10 * * * * ?")
    public void Ten(){
        System.err.println("11111111");
    }
}
