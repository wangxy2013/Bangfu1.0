package com.bangfu.widget.imageview.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;


public class CircleLRDrawable extends Drawable
{
    private Paint mPaint;
    private Paint borderPaint;
    Path boundPath;
    private boolean drawBord;
    private Mode mode= Mode.MODE_NORMAL;
    private RectF rightRect;
    private RectF leftRect;
    int color;
    public enum Mode {
        //左边一半
        MODE_LEFT,
        //右边一半
        MODE_RIGHT,
        //全部
        MODE_NORMAL
    }


    public CircleLRDrawable(int solidColor) {
        this.color=solidColor;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);


        borderPaint = new Paint();
        borderPaint.setStrokeWidth(2);
        borderPaint.setAntiAlias(true);
        borderPaint.setStyle(Paint.Style.STROKE);

        boundPath = new Path();
        rightRect = new RectF();
        leftRect = new RectF();

        setSolidColor(solidColor);

    }

    public void setDrawBord(boolean drawBord) {
        this.drawBord = drawBord;
    }

    public void setBorderColor(int color) {
        borderPaint.setColor(color);
    }

    public void setSolidColor(int color){
        mPaint.setColor(color);
        this.color=color;
        invalidateSelf();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }



    @Override
    public void draw(Canvas canvas) {


        int width = getBounds().width();
        int height = getBounds().height();

        int radius = getBounds().height() / 2;
        boundPath.reset();

        rightRect.set(width - radius * 2, 0, width, height);
        leftRect.set(0, 0, radius * 2, height);

        switch (mode) {
            case MODE_LEFT:
                buildLeft(width,height,radius);
                break;
            case MODE_NORMAL:
                buildNormal(width, height, radius);
                break;
            case MODE_RIGHT:
                buildRight(width, height, radius);
                break;
        }

        canvas.drawPath(boundPath, mPaint);
        if (drawBord) {
            canvas.drawPath(boundPath, borderPaint);
        }
    }

    private void buildNormal(int width, int height, int radius) {
        boundPath.moveTo(radius, 0);
        boundPath.lineTo(width - radius, 0);
        boundPath.arcTo(rightRect, -90, 180);
        boundPath.lineTo(radius, height);
        boundPath.arcTo(leftRect, 90, 180);
    }

    private void buildRight(int width, int height, int radius) {
        boundPath.moveTo(0, 0);
        boundPath.lineTo(width - radius, 0);
        boundPath.arcTo(rightRect, -90, 180);
        boundPath.lineTo(0, height);
    }

    private void buildLeft(int width, int height, int radius) {
        boundPath.moveTo(radius, 0);
        boundPath.lineTo(width, 0);
        boundPath.lineTo(width, height);
        boundPath.lineTo(radius,height);
        boundPath.arcTo(leftRect, 90, 180);
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
