package com.company.util;

import java.util.Random;

public class RandomUtil {
    public static String getRandomSmsCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000, 9999));
    }
}
