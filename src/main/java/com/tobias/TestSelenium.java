package com.tobias;

import org.junit.Assert;
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

    @DataProvider(name="url")
    public Object[][] url(){
        return new Object[][] {
                {"https://www.baidu.com"}
        };
    }
    @BeforeTest
    public void before() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

    }

    @Test(dataProvider = "url")
    public void testOpen(String url) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        Thread.sleep(100);
        String windowHandle = driver.getWindowHandle();
        driver.findElement(By.id("kw")).sendKeys("audi");
        driver.findElement(By.id("su")).click();
//        Set<String> h = driver.getWindowHandles();
        Thread.sleep(500);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("audi"));
        driver.close();
    }

    @AfterTest
    public void after() {
        System.out.println("after");
    }
}
