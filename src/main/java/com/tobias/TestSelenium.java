package com.tobias;

import org.openqa.selenium.remote.session.FirefoxFilter;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import static org.hamcrest.Matchers.*;
import org.junit.Assert.*;

import java.util.Set;

public class TestSelenium {
    WebDriver driver;

    @DataProvider(name="kw")
    public Object[][] kw(){
        return new Object[][] {
                {"audi"},
                {"bmw"}
        };
    }
    @BeforeTest
    public void before() {

        System.out.println("Chrome Browser opened.");

    }

    @Test
    @Parameters({"webdriver", "path"})
    public void testSearch(String webdriver, String path) throws InterruptedException {

        System.setProperty(webdriver, path);
        if (webdriver.contains("chrome")) {
            driver = new ChromeDriver();
        } else if (webdriver.contains("gecko")) {
            driver = new FirefoxDriver();
        }


        String kw = "bmw";
        driver.get("https://www.baidu.com");

        String windowHandle = driver.getWindowHandle();
        driver.findElement(By.id("kw")).sendKeys(kw);
        driver.findElement(By.id("su")).click();
//        Set<String> h = driver.getWindowHandles();
        Thread.sleep(500);
        String currentUrl = driver.getCurrentUrl();
        System.out.println(webdriver);
        Assert.assertTrue(currentUrl.contains("bmw"), String.format("Keyword:%s, searching FAILED!", kw));
        driver.close();

    }

    @AfterTest
    public void after() {

        System.out.println("Chrome Browser closed.");
    }
}
