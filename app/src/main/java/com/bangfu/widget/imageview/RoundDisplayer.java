package com.bangfu.widget.imageview;

import android.graphics.Bitmap;

import com.bangfu.utils.ImageUtils;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public class RoundDisplayer implements BitmapDisplayer
{

    private int radius;

    public RoundDisplayer(int radius) {
        this.radius = radius;
    }

    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        imageAware.setImageBitmap(ImageUtils.roundBitmap(bitmap,radius));
    }
}
