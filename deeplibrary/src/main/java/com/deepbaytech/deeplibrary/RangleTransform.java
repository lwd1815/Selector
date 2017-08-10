package com.deepbaytech.deeplibrary;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * @author EtherealPatrick
 *         Created at 2016/8/11.
 * @email yinghengsun@deepbaytech.com
 */
public class RangleTransform implements Transformation {
    //设置圆角的角度
    private int rangle = 0;

    public RangleTransform(int rangle) {
        this.rangle = rangle;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap bitmap = Bitmap.createBitmap(source.getWidth(),source.getHeight(),source.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0,0,source.getWidth(),source.getHeight());
        canvas.drawRoundRect(rectF,rangle,rangle,paint);
        source.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "rangle";
    }
}
