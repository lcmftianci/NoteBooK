package com.example.administrator.snake;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public class ImageHelper {
    //图像， 色相， 饱和度， 亮度
    public static Bitmap handleImageEffect(Bitmap bm, float hue, float saturation, float lum)
    {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);//传进来的bm我们是无法修改她的参数，所以我们需要新建一个bitmap
        Canvas canvas = new Canvas(bmp);//创建画布
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //色相
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        //饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        //亮度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));

        canvas.drawBitmap(bm, 0, 0, paint);
        return bmp;
    }
}
