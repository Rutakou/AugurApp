package com.example.augur.APICoinmarket;

public class Helper {
    public static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static boolean checkHTTPStatus(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }
}
