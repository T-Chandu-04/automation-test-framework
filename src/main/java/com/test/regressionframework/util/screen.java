package com.test.regressionframework.util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class screen {

    public static String capture(WebDriver driver, String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String filePath = "screenshots/" + testName + ".png";

            File dest = new File(filePath);
            dest.getParentFile().mkdirs(); // create folder

            Files.copy(src.toPath(), dest.toPath());

            return filePath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}