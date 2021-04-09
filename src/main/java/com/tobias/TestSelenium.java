package com.tobias;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import org.junit.Assert.*;

import java.util.Set;

public class TestSelenium {
    WebDriver driver;

    @DataProvider(name="url")
    public Object[][] url(){
        return new Object[][] {
                {"audi"},
                {"bmw"}
        };
    }
    @BeforeTest
    public void before() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Chrome Browser opened.");

    }

    @Test(dataProvider = "url")
    public void testSearch(String kw) throws InterruptedException {

        driver.get("https://www.baidu.com");
        Thread.sleep(100);
        String windowHandle = driver.getWindowHandle();
        driver.findElement(By.id("kw")).sendKeys(kw);
        driver.findElement(By.id("su")).click();
//        Set<String> h = driver.getWindowHandles();
        Thread.sleep(100);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("123"), String.format("Keyword:%s, searching FAILED!", kw));

    }

    @AfterTest
    public void after() {
        driver.close();
        System.out.println("Chrome Browser closed.");
    }
}
