package com.test.regressionframework.controller;

import com.test.regressionframework.entity.TestResult;
import com.test.regressionframework.Repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private TestResultRepository repo;

    // 🔹 Full report
    @GetMapping
    public List<TestResult> getReport() {
        return repo.findAll();
    }

    // 🔹 Summary
    @GetMapping("/summary")
    public Map<String, Object> getSummary() {

        List<TestResult> results = repo.findAll();

        long total = results.size();
        long pass = results.stream().filter(r -> r.getStatus().equals("PASS")).count();
        long fail = results.stream().filter(r -> r.getStatus().equals("FAIL")).count();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("pass", pass);
        map.put("fail", fail);

        return map;
    }
    @GetMapping(value = "/screenshot", produces = "image/png")
    public byte[] getScreenshot(@RequestParam String name) throws Exception {
        Path path = Paths.get("screenshots/" + name);
        return Files.readAllBytes(path);
    }
}