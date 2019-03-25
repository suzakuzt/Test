package com.example.demo.util;

import org.testng.Assert;

public class Verify {

    public static StringBuffer verificationErrors= new StringBuffer();;
//封装断言抛出异常处理.TESTNG自带的断言处理会出现报错异常出现错误后相关脚本不会再运行下去需要对这块进行处理
    public static void verifyTrue(boolean o) {
        try {
            Assert.assertTrue(o);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    public static void verifyFalse(boolean o) {
        try {
            Assert.assertFalse(o);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    public static void verifyEquals(Object expected, Object actuals) {
        try {
            Assert.assertEquals(expected, actuals);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    public static void verifyEquals(Object expected, Object actuals,
                                    String message) {
        try {
            Assert.assertEquals(expected, actuals, message);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    public static  void assertEquals(String actual, String expected,
                                     String message) {
        try {
            Assert.assertEquals( actual, expected, message);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    public static  void assertEquals(String actual, String expected) {
        try {
            Assert.assertEquals( actual, expected);
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    public static void tearDown() {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }






















}
