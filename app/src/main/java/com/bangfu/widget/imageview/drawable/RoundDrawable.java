package com.bangfu.widget.imageview.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class RoundDrawable extends Drawable
{
    private Paint  mPaint;
    private Bitmap mBitmap;

    private int radius;

    public RoundDrawable(Bitmap bitmap, int radius) {
        init(bitmap, radius);
    }


    public RoundDrawable(int color, int radius) {
        this.radius = radius;
        setColor(color);
    }

    public void setColor(int color) {
        int output_size = radius * 2;
        ColorDrawable drawable = new ColorDrawable(color);
        Bitmap bitmap = Bitmap.createBitmap(
                output_size,
                output_size,
                drawable.getOpacity() != PixelFormat.OPAQUE
                        ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, output_size, output_size);
        drawable.draw(canvas);
        init(bitmap, radius);
    }

    private void init(Bitmap bitmap, int radius) {
        mBitmap = bitmap;
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(bitmapShader);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(radius, radius, radius, mPaint);
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
