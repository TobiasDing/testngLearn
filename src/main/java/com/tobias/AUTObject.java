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
            for (int i = 1; i < -y; i++) {
                result = result * x;
            }
            result = 1 / result;
        } else {
            for (int i = 1; i < y; i++) {
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

            if (negative == 1 &&(str.charAt(0) != 45 || str.charAt(1) == '0')) {
                return false;
            }

        }
        return true;
    }


    @DataProvider(name = "numbers")
    public Object[][] numbers() {
        return  new Object[][]{
                {"123", true},
                {"1.23", true},
                {"-123", true},
                {".123", true},
        };
    }
    @Test(dataProvider = "numbers")
    public void testCheckNumber(String str, boolean expect) {
        boolean actual = aut.checkNumber(str);
        Assert.assertEquals(actual, expect, String.format("Checking numbers error! input: %s", str));
    }
}
