package com.test.regressionframework.scheduler;

import com.test.regressionframework.Service.TestExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {

    @Autowired
    private TestExecutionService service;

    // Run every 1 minute
    //@Scheduled(fixedRate = 60000)
    public void runScheduledTests() {
        System.out.println("Running scheduled tests...");
        service.runAllTests();
    }
}