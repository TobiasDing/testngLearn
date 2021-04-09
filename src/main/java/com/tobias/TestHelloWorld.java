package com.tobias;

import org.testng.Assert;
import org.testng.annotations.*;

import java.security.acl.Group;

public class TestHelloWorld {


    @DataProvider()
    public Object[][] user() {
        return new Object[][] {
                {"tim", "tim@tobias.com"},
                {"tim2", "tim2@tobias.com"},
                {"tim3", "tim3@tobias.com"}
        };
    }


    @BeforeTest
    public void p() {
        System.out.println("Before");
    }

    @Test(groups="smoke")
    @Parameters({"userName", "realEmail"})
    public void testEmailGenerator(String userName, String realEmail) {
        RandomEmailGenerator obj = new RandomEmailGenerator();
        String email = obj.generate(userName);

        Assert.assertEquals(email, realEmail);
//        Assert.assertEquals(email, "feedback@tobias.com");
    }

    @Test(groups="smoke")
    @Parameters({"userName", "realEmail"})
    public void testEmailGenerator2(String userName, String realEmail) {
        RandomEmailGenerator obj = new RandomEmailGenerator();
        String email = obj.generate(userName);

        Assert.assertEquals(email, realEmail);
//        Assert.assertEquals(email, "feedback@tobias.com");
    }
//
//    @Test
//    public void testEmailGenerator2() {
//        RandomEmailGenerator obj = new RandomEmailGenerator();
//        String email = obj.generate();
//
////        Assert.assertNull(email);
//        Assert.assertEquals(email, "feedback@tobias.com");
//    }

    @AfterTest
    public void after() {
        System.out.println("After");
    }
}
