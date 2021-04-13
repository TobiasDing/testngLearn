package com.tobias;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Scanner;

public class AUTObject {
    static AUTObject aut = new AUTObject();
    public static void main(String[] args) {

        System.out.println("Please input:");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        boolean isNumber = aut.checkNumber(str);
        if (isNumber) {
            System.out.println("It is a number.");
        } else {
            System.out.println("It is not a number.");
        }
    }
    public double power(double x, int y) {
        double result = 1;

        if (y < 0) {
            for (int i = 1; i <= -y; i++) {
                result = result * x;
            }
            result = 1 / result;
        } else {
            for (int i = 1; i <= y; i++) {
                result *= x;
            }
        }
        return result;
    }

    public boolean checkNumber(String str) {

//        boolean isNumber = true;
        short negative = 0;
        short point = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            boolean condition = (c >= 48 && c<=57) || c == 45 || c == 46;
            if (!condition) {
                return false;
            } else {
                if (c == 45) {
                    negative += 1;
                }

                if (c == 46) {
                    point += 1;
                }
            }
            if (negative > 1 || point > 1 || str.charAt(0) == 46 || str.charAt(0) == '0') {
                return false;
            }

            if (negative == 1 &&(str.charAt(0) != 45 || str.charAt(1) == '0' || str.charAt(1) == '.')) {
                return false;
            }

        }
        return true;
    }


    @DataProvider(name = "checkNumber")
    public Object[][] numbers() {
        return  new Object[][]{
                {"123", true},
                {"1.23", true},
                {"-123", true},
                {"-123.0", true},
                {"-0.1230", true},
                {"0.1230", true},
                {".123", false},
                {"0123", false},
                {"0.1.23", false},
                {"-.123", false},
                {".-123", false},
                {"-1-23", false},
                {"..123", false},
                {"&123", false},
                {"00123", false},
        };
    }

    @Test(dataProvider = "checkNumber")
    public void testCheckNumber(String str, boolean expect) {
        boolean actual = aut.checkNumber(str);
        Assert.assertEquals(actual, expect, String.format("Checking numbers error! input: %s", str));
    }


    @DataProvider(name = "power")
    public Object[][] numbersForPower() {
        return new Object[][] {
                {2.0, 2, 4.0},
                {2.0, -2, 0.25},
        };
    }
    @Test(dataProvider = "power")
    public void testPower(double x, int y, double expect) {
        double actual = aut.power(x, y);
        Assert.assertEquals(actual, expect);
    }

}
