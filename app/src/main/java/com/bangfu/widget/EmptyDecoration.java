package com.bangfu.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;


/**
 * Created by user1 on 2015/7/13.
 * 当列表数据为空时展示提示文字
 */
public class EmptyDecoration extends RecyclerView.ItemDecoration
{

    private Context context;
    private CharSequence empty = null;
    private Paint        p     = new Paint();
    float offsetY = 0;

    public EmptyDecoration(Context context, String empty)
    {
        this.context = context;
        this.empty = empty;
        p.setTextSize(40);
        p.setTextAlign(Paint.Align.CENTER);
    }

    public void setOffsetY(float offsetY)
    {
        this.offsetY = offsetY;
    }

    /**
     * 获取画笔
     *
     * @return 用来绘制文本的画笔
     */
    public Paint getPaint()
    {
        return p;
    }

    public void setEmpty(CharSequence empty)
    {
        this.empty = empty;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        if (state.getItemCount() == 0)
        {
            CharSequence text;
            String empty = this.empty.toString();
            if (p.measureText(empty) < parent.getWidth())
            {
                text = this.empty;
            }
            else
            {
                text = empty.substring(0, p.breakText(this.empty, 0, this.empty.length(), true, parent.getWidth(), null));
            }
            c.drawText(text, 0, text.length(), parent.getWidth() / 2, parent.getHeight() / 2 - TextPainUtil.getBaseLineOffset(p) + offsetY, p);
        }

        super.onDrawOver(c, parent, state);
    }

}
