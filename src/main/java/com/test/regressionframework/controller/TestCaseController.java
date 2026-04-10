package com.test.regressionframework.controller;

import com.test.regressionframework.Service.TestExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/run")
public class TestCaseController {

    @Autowired
    private TestExecutionService service;

    @PostMapping("/runAll")
    public String runAllTests() {
        service.runAllTests();
        return "Tests Executed";
    }
}