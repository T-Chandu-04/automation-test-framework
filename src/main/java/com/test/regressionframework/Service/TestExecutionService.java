package com.test.regressionframework.Service;

import com.test.regressionframework.entity.TestResult;
import com.test.regressionframework.Repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.regressionframework.util.screen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class TestExecutionService {

    @Autowired
    private TestResultRepository repo;

    @Autowired
    private ExecutorService executor;

    // 🔹 Single test execution
    public void executeTest(String testName) {

        long start = System.currentTimeMillis();

        TestResult result = new TestResult();
        result.setTestName(testName);

        WebDriver driver = null;
        String screenshotPath = null;

        try {
            System.out.println("Running test: " + testName);

            // 🔹 Start browser
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://www.google.com");

            if (testName.equals("FailTest")) {
                throw new RuntimeException("Test Failed");
            }

            result.setStatus("PASS");

        } catch (Exception e) {

            result.setStatus("FAIL");

            System.out.println("Test Failed: " + testName);

            if (driver != null) {
                System.out.println("Taking screenshot...");
                screenshotPath = screen.capture(driver, testName + "_" + System.currentTimeMillis());
            } else {
                System.out.println("Driver is NULL ❌");
            }
        }

        // ⏱ execution time
        result.setExecutionTime(System.currentTimeMillis() - start);

        // 📸 set screenshot
        result.setScreenshotPath(screenshotPath);

        // close browser
        if (driver != null) {
            driver.quit();
        }

        repo.save(result);
    }

    // 🔹 Parallel execution
    public void runAllTests() {

        //repo.deleteAll(); // clear old results

        List<String> tests = List.of("ApiTest", "SeleniumTest", "FailTest");

        for (String test : tests) {
            executor.submit(() -> executeTest(test));
        }
    }
}