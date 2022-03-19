package com.example.tiwen.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author XUJIAYIN
 * @create 2022-03-18-20:49
 */
@Component
public class pythonRun {
    @Autowired
    private startPy startPy;

    @Scheduled(cron = "0 0 7,12,17 * * *")
//    @Scheduled(cron = "0/20 * * * * *")
    public void run1() {
        startPy.PyService();
    }

}
