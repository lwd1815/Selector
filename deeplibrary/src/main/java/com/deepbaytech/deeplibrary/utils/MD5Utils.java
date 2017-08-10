package com.deepbaytech.deeplibrary.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by deepbayter on 2016/7/6.
 */
public class MD5Utils {
    //传一个字符串获取其MD5加密
    public static   String getMD5(String key) {
        String cacheKey;
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes("UTF-8"));
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (Exception e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static   String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    /**
     * md5加密
     * @param psw
     * @return
     */
    public static String md5(String psw){
        StringBuilder sb = new StringBuilder();
        try {
            //数据摘要器
            //algorithm : 加密方式
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //第一次加密，将一个byte数组进行加密，返回一个加密过的byte数据，二进制哈希算法
            byte[] digest = messageDigest.digest(psw.getBytes());
            for (int i = 0; i < digest.length; i++) {
                //byte:-128--127
                //将负数转化成正整数
                int reuslt = digest[i] & 0xff;
                //将int类型的数据转化成十六进制的字符串
                //String hexString = Integer.toHexString(reuslt)+1;//不规则加密，加盐操作
                String hexString = Integer.toHexString(reuslt);
                if (hexString.length() < 2) {
                    //System.out.print("0");
                    sb.append("0");
                }
                //System.out.println(hexString);
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return psw;
    }
}
