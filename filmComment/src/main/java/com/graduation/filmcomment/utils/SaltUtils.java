package com.graduation.filmcomment.utils;

import java.util.Random;

public class SaltUtils {
    public static String addSalt(int length) {
     String str= "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  //62字符
     Random random = new Random();
     StringBuffer sb = new StringBuffer();
     for (int i=0;i<length;i++){
         int number = random.nextInt(62);
         sb.append(str.charAt(number));
     }
     return sb.toString();
    }
}
