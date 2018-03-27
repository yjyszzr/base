package com.dl.base.util;

import java.security.MessageDigest;

public class MD5Utils {
    public static String MD5(String str) {
        String result = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update((str).getBytes("UTF-8"));
            byte b[] = md5.digest();

            int i;
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            result = buf.toString();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return result;
    }

    public static String encryption(String str1, String str2) {
        String s1 = MD5(str1);
        String s2 = MD5(s1 + str2);
        return s2;
    }
    
    public static String salt() {
        String s1 = (int) ((Math.random() * 9 + 1) * 100000) + "";
        return s1;
    }

    public static void main(String[] args) {
        String str1 = MD5("136134525");
        String str2 = MD5(str1 + "4a75b6170adc6f32d563");

        System.out.println("str2 = " + str2);
        //pass = 18bae7fd13f7e119de1efcfc5b175fb5
    }
}
