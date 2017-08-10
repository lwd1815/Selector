package com.deepbaytech.deeplibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by deepbayter on 2016/7/6.
 */
public class Base64Utils {

    public static String BitmapToBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 95, bos);
        try {
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = bos.toByteArray();
        System.out.println(bytes.length+"----------------------------------------------");
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public static Bitmap Base64ToBitmap(String base64){
        byte[] bytes = Base64.decode(base64,Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0,
                bytes.length);
        return bitmap;
    }
}
