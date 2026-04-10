package com.test.regressionframework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + testName + ".png";
            File dest = new File(path);

            FileHandler.copy(src, dest);

            return path;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}