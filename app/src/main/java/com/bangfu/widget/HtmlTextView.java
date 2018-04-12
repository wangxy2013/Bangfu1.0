package com.bangfu.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import com.bangfu.utils.DisplayUtil;
import com.bangfu.utils.ImageOptions;
import com.bangfu.utils.ImageUtils;
import com.bangfu.utils.MD5;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;

/**
 * Created by user1 on 2015/7/29.
 * 展示图片 并点击放大
 */
public class HtmlTextView extends TextView implements Html.ImageGetter, HtmlDisplayer
{
    CharSequence data;
    private       ColorDrawable   empty;
    private final int             MAX_WIDTH;//图片的最大宽度
    private final int             MIN_WIDTH;//图片的最大宽度
    private final int             SCREEN_WIDTH;
   // private       ClickFullScreen clickFullScreen;
    private int pendingDrawableCount = 0;

    public HtmlTextView(Context context)
    {
        this(context, null);
    }

    public HtmlTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setMovementMethod(LinkMovementMethod.getInstance());
//        MAX_WIDTH = context.getResources().getDimensionPixelOffset(R.dimen.dimen_270px);
//        MIN_WIDTH = context.getResources().getDimensionPixelOffset(R.dimen.dimen_40px);
        MAX_WIDTH =  DisplayUtil.getScreenWidth(context);
        MIN_WIDTH = 200;
        empty = new ColorDrawable();
        SCREEN_WIDTH = DisplayUtil.getScreenWidth(context);
       // clickFullScreen = new ClickFullScreen(context);

    }

    public void displayWithHtml(CharSequence data)
    {
        if (data instanceof String)
        {
            String html = (String) data;
            html = html.replace("\n", "<br/>");
            if (TextUtils.equals(this.data, html) && pendingDrawableCount <= 0)
            {
                return;
            }
            else
            {
                pendingDrawableCount = 0;
            }
            this.data = html;

            SpannableStringBuilder text = (SpannableStringBuilder) Html.fromHtml(html, this, null);

            // 获取长度
            int len = text.length();
            // 获取图片地址
            for (int i = 0; i < len; i++)
            {
                ImageSpan[] images = text.getSpans(i, i + 1, ImageSpan.class);
                if (images != null && images.length > 0)
                {
                    String imgURL = images[0].getSource();
                    if (TextUtils.isEmpty(imgURL))
                    {
                        continue;
                    }
                    // 使图片可点击并监听点击事件
                    text.setSpan(new ImageClick(getContext(), images[0], getImageKey(imgURL)), i, i + 1, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

            URLSpan[] urlSpans = text.getSpans(0, len, URLSpan.class);
            if (urlSpans != null)
            {
                //暂时去除url链接，因为这个不安全
                for (URLSpan urlSpan : urlSpans)
                {
                    text.removeSpan(urlSpan);
                }
            }

            setText(text);
        }
        else if (data instanceof SpannableStringBuilder)
        {

            this.data = data;
            setText(data);
        }


    }

    @Override
    public Drawable getDrawable(String source)
    {
        Drawable empty = this.empty;
        if (TextUtils.isEmpty(source))
        {
            return empty;
        }

        String imageKey = getImageKey(source);
        File file = ImageLoader.getInstance().getDiskCache().get(imageKey);
        if (file != null && file.exists())
        {//取出缓存
            return createDrawableFromBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        }

        if (source.startsWith("data"))
        {
            //base 64格式图片处理
            try
            {
                String data = source.split(",")[1];
                byte[] decode = Base64.decode(data, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                try
                {
                    int dst_width;
                    int dst_height;

                    dst_width = Math.min(SCREEN_WIDTH, bitmap.getWidth());
                    float scaleFactor = dst_width / (float) bitmap.getWidth();
                    dst_height = (int) (bitmap.getHeight() * scaleFactor);
                    ImageLoader.getInstance().getDiskCache().save(imageKey, Bitmap.createScaledBitmap(bitmap, dst_width, dst_height, true));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                return empty;
            } catch (Exception ignored)
            {
                //base64 处理失败 交给后面的继续处理
            }
        }
        pendingDrawableCount++;
        ImageLoader.getInstance().loadImage(source, ImageOptions.getStandard(), listener);
        return empty;
    }

    private String getImageKey(String source)
    {
        if (!TextUtils.isEmpty(source) && source.startsWith("data"))
        {
            //base 64格式图片处理
            try
            {
                source = source.split(",")[1];
                if (source.length() > 32)
                {
                    source = source.substring(0, 31);
                }
                return MD5.md5(source);
            } catch (Exception ignored)
            {
                //base64 处理失败 交给后面的继续处理
            }
        }
        else
        {
            return source;
        }
        return MD5.md5(source);
    }

    /**
     * 用给定的bitmap生成drawable，并调整drawable大小
     */
    private Drawable createDrawableFromBitmap(Bitmap bitmap)
    {

        if (bitmap == null)
        {
            return new ColorDrawable();
        }

        int width;
        int height;
//        if (bitmap.getWidth() > MAX_WIDTH)
//        {
//            width = MAX_WIDTH;
//        }
//        else if (bitmap.getWidth() < MIN_WIDTH)
//        {
//            width = MIN_WIDTH;
//        }
//
//
//
//        else
//        {
//            width = bitmap.getWidth();
//        }
        width =   MAX_WIDTH;


        height = (int) (bitmap.getHeight() * (width / (float) bitmap.getWidth()));

        Drawable d = new BitmapDrawable(getResources(), bitmap);

        d.setBounds(0, 0, width, height);
        return d;
    }


    private ImageLoadingListener listener = new ImageLoadingListener()
    {
        @Override
        public void onLoadingStarted(String s, View view)
        {

        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason)
        {

        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap)
        {
            onNewBitmapLoaded(bitmap);
        }

        @Override
        public void onLoadingCancelled(String s, View view)
        {

        }
    };

    public void onNewBitmapLoaded(Bitmap bitmap)
    {
        displayWithHtml(data);
        pendingDrawableCount--;
        invalidate();
    }


    private class ImageClick extends ClickableSpan
    {

        private String    key;
        private ImageSpan imageSpan;

        public ImageClick(Context context, ImageSpan imageSpan, String key)
        {
            this.key = key;
            this.imageSpan = imageSpan;

        }

        @Override
        public void onClick(View widget)
        {

            File file = ImageLoader.getInstance().getDiskCache().get(key);
            if (file.exists())
            {
                // 处理点击事件，开启一个新的activity来处理显示图片

                Bitmap bitmap;
                try
                {
                    bitmap = ImageUtils.getBitmap(file.getAbsolutePath(), 500 * 1024);
                } catch (Exception e)
                {
                    bitmap = ImageUtils.getBitmap(file.getAbsolutePath(), 200 * 1024);

                }
//                clickFullScreen.setRunBitmap(bitmap);
                //                clickFullScreen.showFor(widget, false);
                //                Rect rect = imageSpan.getDrawable().getBounds();
                //
                //                clickFullScreen.show(widget, rect);
            }
        }
    }


}
