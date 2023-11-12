package com.tool.utilities;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    private Driver() {};

    private static WebDriver driver;

    public static WebDriver getDriver() {

        try {
            if (driver == null) {

                String browserType = configurationReader.getProperty("browser");

                switch (browserType) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;

                    case "firefox":
                        WebDriverManager.chromedriver().setup();
                        driver = new FirefoxDriver();
                        break;

                    case "headless-chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--headless");
                        driver = new ChromeDriver(options);
                        break;

                    default:
                        throw new IllegalArgumentException("Unsupported Browser");


                }
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error setting up WebDriver: " + e.getMessage(), e);
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}
