package com.bangfu.widget;

import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.widget.TextView;

public class TextPainUtil
{
    public static void addDeleteLine(TextView tv) {
        tv.getPaint().setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
    }

    public static void addUnderLine(TextView tv) {
        tv.getPaint().setFlags(TextPaint.UNDERLINE_TEXT_FLAG);
    }

    public static float getBaseLineOffset(Paint p) {

        return -(p.ascent() + p.descent()) / 2;
    }

    public static RectF getTextBound(Paint paint, String text) {
        float width = paint.measureText(text);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float height = -fm.top + fm.bottom;
        return new RectF(0,0,width,height);
    }
}
