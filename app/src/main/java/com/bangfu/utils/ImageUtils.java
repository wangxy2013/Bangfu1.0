package com.bangfu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 图片操作工具�?
 *
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class ImageUtils
{

    public final static String SDCARD_MNT = "/mnt/sdcard";
    public final static String SDCARD     = "/sdcard";

    /**
     * 请求相册
     */
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 100;
    /**
     * 请求相机
     */
    public static final int REQUEST_CODE_GETIMAGE_BYCAMERA = 101;
    /**
     * 请求裁剪
     */
    public static final int REQUEST_CODE_GETIMAGE_BYCROP   = 102;

    /**
     * 写图片文�? 在Android系统中，文件保存�? /data/data/PACKAGE_NAME/files 目录�?
     *
     * @throws IOException
     */
    public static void saveImage(Context context, String fileName, Bitmap bitmap) throws IOException
    {
        saveImage(context, fileName, bitmap, 100);
    }

    public static void saveImage(Context context, String fileName, Bitmap bitmap, int quality) throws IOException
    {
        if (bitmap == null || fileName == null || context == null)
            return;

        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, quality, stream);
        byte[] bytes = stream.toByteArray();
        fos.write(bytes);
        fos.close();
    }

    /**
     * 写图片文件到SD�?
     *
     * @throws IOException
     */
    public static void saveImageToSD(Context ctx, String filePath, Bitmap bitmap, int quality) throws IOException
    {
        if (bitmap != null)
        {
            File file = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
            if (!file.exists())
            {
                file.mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
            bitmap.compress(CompressFormat.JPEG, quality, bos);
            bos.flush();
            bos.close();
            if (ctx != null)
            {
                scanPhoto(ctx, filePath);
            }
            if (bitmap != null && !bitmap.isRecycled())
            {
                bitmap.recycle(); // 回收图片�?占的内存
                System.gc();
            }
        }
    }

    /**
     * 让Gallery上能马上看到该图�?
     */
    private static void scanPhoto(Context ctx, String imgFileName)
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File file = new File(imgFileName);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        ctx.sendBroadcast(mediaScanIntent);
    }

    /**
     * 获取bitmap
     *
     * @param context
     * @param fileName
     * @return
     */
    public static Bitmap getBitmap(Context context, String fileName)
    {
        FileInputStream fis = null;
        Bitmap bitmap = null;
        try
        {
            fis = context.openFileInput(fileName);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                fis.close();
            } catch (Exception e)
            {
            }
        }
        return bitmap;
    }

    /**
     * 获取bitmap
     *
     * @param filePath
     * @return
     */
    public static Bitmap getBitmapByPath(String filePath)
    {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize = 2;
        return getBitmapByPath(filePath, op);
    }

    public static Bitmap getBitmapByPath(String filePath, BitmapFactory.Options opts)
    {
        FileInputStream fis = null;
        Bitmap bitmap = null;
        try
        {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis, null, opts);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                fis.close();
            } catch (Exception e)
            {
            }
        }
        return bitmap;
    }

    /**
     * 获取bitmap
     *
     * @param file
     * @return
     */
    public static Bitmap getBitmapByFile(File file)
    {
        FileInputStream fis = null;
        Bitmap bitmap = null;
        try
        {
            fis = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                fis.close();
            } catch (Exception e)
            {
            }
        }
        return bitmap;
    }

    /**
     * 使用当前时间戳拼接一个唯�?的文件名
     *
     * @return
     */
    public static String getTempFileName()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss_SS");
        String fileName = format.format(new Timestamp(System.currentTimeMillis()));
        return fileName;
    }

    /**
     * 获取照相机使用的目录
     *
     * @return
     */
    public static String getCamerPath()
    {
        return Environment.getExternalStorageDirectory() + File.separator + "FounderNews" + File.separator;
    }

    /**
     * 判断当前Url是否标准的content://样式，如果不是，则返回绝对路�?
     *
     * @return
     */
    public static String getAbsolutePathFromNoStandardUri(Uri mUri)
    {
        String filePath = null;

        String mUriString = mUri.toString();
        mUriString = Uri.decode(mUriString);

        String pre1 = "file://" + SDCARD + File.separator;
        String pre2 = "file://" + SDCARD_MNT + File.separator;

        if (mUriString.startsWith(pre1))
        {
            filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + mUriString.substring(pre1.length());
        }
        else if (mUriString.startsWith(pre2))
        {
            filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + mUriString.substring(pre2.length());
        }
        return filePath;
    }

    /**
     * 通过uri获取文件的绝对路�?
     *
     * @param uri
     * @return
     */
    public static String getAbsoluteImagePath(Activity context, Uri uri)
    {
        String imagePath = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.managedQuery(uri, proj, // Which columns to
                // return
                null, // WHERE clause; which rows to return (all rows)
                null, // WHERE clause selection arguments (none)
                null); // Order-by clause (ascending by name)

        if (cursor != null)
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.getCount() > 0 && cursor.moveToFirst())
            {
                imagePath = cursor.getString(column_index);
            }
        }

        return imagePath;
    }

    // /**
    // * 获取图片缩略�? 只有Android2.1以上版本支持
    // *
    // * @param imgName
    // * @param kind
    // * MediaStore.Images.Thumbnails.MICRO_KIND
    // * @return
    // */
    // public static Bitmap loadImgThumbnail(Activity context, String imgName,
    // int kind) {
    // Bitmap bitmap = null;
    //
    // String[] proj = { MediaStore.Images.Media._ID,
    // MediaStore.Images.Media.DISPLAY_NAME };
    //
    // Cursor cursor =
    // context.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, proj,
    // MediaStore.Images.Media.DISPLAY_NAME + "='" + imgName + "'", null, null);
    //
    // if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
    // ContentResolver crThumb = context.getContentResolver();
    // BitmapFactory.Options options = new BitmapFactory.Options();
    // options.inSampleSize = 1;
    // bitmap = MethodsCompat.getThumbnail(crThumb, cursor.getInt(0), kind,
    // options);
    // }
    // return bitmap;
    // }

    public static Bitmap loadImgThumbnail(String filePath, int w, int h)
    {
        Bitmap bitmap = getBitmapByPath(filePath);
        return zoomBitmap(bitmap, w, h);
    }

    /**
     * 获取SD卡中�?新图片路�?
     *
     * @return
     */
    public static String getLatestImage(Activity context)
    {
        String latestImage = null;
        String[] items = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        Cursor cursor = context.managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, items, null, null, MediaStore.Images.Media._ID + " desc");

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                latestImage = cursor.getString(1);
                break;
            }
        }

        return latestImage;
    }

    /**
     * 计算缩放图片的宽�?
     *
     * @param img_size
     * @param square_size
     * @return
     */
    public static int[] scaleImageSize(int[] img_size, int square_size)
    {
        if (img_size[0] <= square_size && img_size[1] <= square_size)
            return img_size;
        double ratio = square_size / (double) Math.max(img_size[0], img_size[1]);
        return new int[]{(int) (img_size[0] * ratio), (int) (img_size[1] * ratio)};
    }

    /**
     * 创建缩略�?
     *
     * @param context
     * @param largeImagePath 原始大图路径
     * @param thumbfilePath  输出缩略图路�?
     * @param square_size    输出图片宽度
     * @param quality        输出图片质量
     * @throws IOException
     */
    public static void createImageThumbnail(Context context, String largeImagePath, String thumbfilePath, int square_size, int quality) throws IOException
    {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 1;
        // 原始图片bitmap
        Bitmap cur_bitmap = getBitmapByPath(largeImagePath, opts);

        if (cur_bitmap == null)
            return;

        // 原始图片的高�?
        int[] cur_img_size = new int[]{cur_bitmap.getWidth(), cur_bitmap.getHeight()};
        // 计算原始图片缩放后的宽高
        int[] new_img_size = scaleImageSize(cur_img_size, square_size);
        // 生成缩放后的bitmap
        Bitmap thb_bitmap = zoomBitmap(cur_bitmap, new_img_size[0], new_img_size[1]);
        // 生成缩放后的图片文件
        saveImageToSD(null, thumbfilePath, thb_bitmap, quality);
    }

    /**
     * 放大缩小图片
     *
     * @param bitmap
     * @param w
     * @param h
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h)
    {
        Bitmap newbmp = null;
        if (bitmap != null)
        {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            float scaleWidht = ((float) w / width);
            float scaleHeight = ((float) h / height);
            matrix.postScale(scaleWidht, scaleHeight);
            newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        }
        return newbmp;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap)
    {
        // 获取这个图片的宽和高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 定义预转换成的图片的宽度和高�?
        int newWidth = 200;
        int newHeight = 200;
        // 计算缩放率，新尺寸除原始尺寸
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        // 旋转图片 动作
        // matrix.postRotate(45);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return resizedBitmap;
    }

    public static Bitmap scaleBitmap1(Bitmap bitmap, int newWidth, int newHeight)
    {
        // 获取这个图片的宽和高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 定义预转换成的图片的宽度和高�?
        // int newWidth = 200;
        // int newHeight = 200;
        // 计算缩放率，新尺寸除原始尺寸
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        // 旋转图片 动作
        // matrix.postRotate(45);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return resizedBitmap;
    }

    /**
     * (缩放)重绘图片
     *
     * @param context Activity
     * @param bitmap
     * @return
     */
    public static Bitmap reDrawBitMap(Activity context, Bitmap bitmap)
    {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int rHeight = dm.heightPixels;
        int rWidth = dm.widthPixels;
        // float rHeight=dm.heightPixels/dm.density+0.5f;
        // float rWidth=dm.widthPixels/dm.density+0.5f;
        // int height=bitmap.getScaledHeight(dm);
        // int width = bitmap.getScaledWidth(dm);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        float zoomScale;
        /** 方式1 **/
        // if(rWidth/rHeight>width/height){//以高为准
        // zoomScale=((float) rHeight) / height;
        // }else{
        // //if(rWidth/rHeight<width/height)//以宽为准
        // zoomScale=((float) rWidth) / width;
        // }
        /** 方式2 **/
        // if(width*1.5 >= height) {//以宽为准
        // if(width >= rWidth)
        // zoomScale = ((float) rWidth) / width;
        // else
        // zoomScale = 1.0f;
        // }else {//以高为准
        // if(height >= rHeight)
        // zoomScale = ((float) rHeight) / height;
        // else
        // zoomScale = 1.0f;
        // }
        /** 方式3 **/
        if (width >= rWidth)
            zoomScale = ((float) rWidth) / width;
        else
            zoomScale = 1.0f;
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 缩放图片动作
        matrix.postScale(zoomScale, zoomScale);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 将Drawable转化为Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable)
    {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    /**
     * 获得圆角图片的方�?
     *
     * @param bitmap
     * @param roundPx �?般设�?14
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx)
    {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 获得带�?�影的图片方�?
     *
     * @param bitmap
     * @return
     */
    public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap)
    {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2, width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, (height + height / 2), Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff,
                TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight() + reflectionGap, paint);

        return bitmapWithReflection;
    }

    /**
     * 将bitmap转化为drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap)
    {
        Drawable drawable = new BitmapDrawable(bitmap);
        return drawable;
    }

    /**
     * 获取图片类型
     *
     * @param file
     * @return
     */
    public static String getImageType(File file)
    {
        if (file == null || !file.exists())
        {
            return null;
        }
        InputStream in = null;
        try
        {
            in = new FileInputStream(file);
            String type = getImageType(in);
            return type;
        } catch (IOException e)
        {
            return null;
        } finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException e)
            {
            }
        }
    }

    /**
     * 获取图片的类型信�?
     *
     * @param in
     * @return
     * @see #getImageType(byte[])
     */
    public static String getImageType(InputStream in)
    {
        if (in == null)
        {
            return null;
        }
        try
        {
            byte[] bytes = new byte[8];
            in.read(bytes);
            return getImageType(bytes);
        } catch (IOException e)
        {
            return null;
        }
    }

    /**
     * 获取图片的类型信�?
     *
     * @param bytes 2~8 byte at beginning of the image file
     * @return image mimetype or null if the file is not image
     */
    public static String getImageType(byte[] bytes)
    {
        if (isJPEG(bytes))
        {
            return "image/jpeg";
        }
        if (isGIF(bytes))
        {
            return "image/gif";
        }
        if (isPNG(bytes))
        {
            return "image/png";
        }
        if (isBMP(bytes))
        {
            return "application/x-bmp";
        }
        return null;
    }

    private static boolean isJPEG(byte[] b)
    {
        if (b.length < 2)
        {
            return false;
        }
        return (b[0] == (byte) 0xFF) && (b[1] == (byte) 0xD8);
    }

    private static boolean isGIF(byte[] b)
    {
        if (b.length < 6)
        {
            return false;
        }
        return b[0] == 'G' && b[1] == 'I' && b[2] == 'F' && b[3] == '8' && (b[4] == '7' || b[4] == '9') && b[5] == 'a';
    }

    private static boolean isPNG(byte[] b)
    {
        if (b.length < 8)
        {
            return false;
        }
        return (b[0] == (byte) 137 && b[1] == (byte) 80 && b[2] == (byte) 78 && b[3] == (byte) 71 && b[4] == (byte) 13 && b[5] == (byte) 10 && b[6] == (byte)
                26 && b[7] == (byte) 10);
    }

    private static boolean isBMP(byte[] b)
    {
        if (b.length < 2)
        {
            return false;
        }
        return (b[0] == 0x42) && (b[1] == 0x4d);
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap)
    {

        String result = null;
        ByteArrayOutputStream baos = null;
        try
        {
            if (bitmap != null)
            {
                baos = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (baos != null)
                {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data)
    {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static byte[] Bitmap2Bytes(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static String getUUID()
    {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;
    }



    /**
     * 对图片进行base64编码
     *
     * @param bitmap 用来进行编码的bitmap，如果为null则返回null
     * @return base64编码的bitmap
     */
    public static String base64Encode(Bitmap bitmap)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bytes = bos.toByteArray();

        try
        {
            bos.flush();
            bos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return "data:image/png;base64," + Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * 以一个bitmap的中心为圆点，以radius为半径，去截取图片
     *
     * @param src    将要被处理的图片
     * @param radius 单位px
     * @return 处理后的推按
     */
    public static Bitmap roundBitmap(Bitmap src, int radius)
    {

        int output_size = radius * 2;
        if (src == null)
        {
            //如果图片不存在，就创建一个色块
            ColorDrawable drawable = new ColorDrawable(Color.GRAY);
            src = Bitmap.createBitmap(output_size, output_size, drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(src);
            drawable.setBounds(0, 0, output_size, output_size);
            drawable.draw(canvas);
        }
        int src_w = src.getWidth();
        int src_h = src.getHeight();

        //按照图片宽高较小的值来计算缩放比例。即，如果宽小就按照宽缩放，反之亦然。
        float scale = output_size / (float) Math.min(src_h, src_w);

        //缩放后的Bitmap
        Bitmap scaledSrc = Bitmap.createScaledBitmap(src, (int) (src_w * scale), (int) (src_h * scale), false);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap result = Bitmap.createBitmap(output_size, output_size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        //canvas.drawARGB(0,0,0,0);//背景透明效果
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        float translate_x = (result.getWidth() - scaledSrc.getWidth()) / 2;
        float translate_y = (result.getHeight() - scaledSrc.getHeight()) / 2;

        canvas.save();
        canvas.translate(translate_x, translate_y);
        canvas.drawBitmap(scaledSrc, 0, 0, paint);
        canvas.restore();

        if (src != scaledSrc && !scaledSrc.isRecycled()) scaledSrc.recycle();

        return result;
    }


    public static Bitmap convertView2Bitmap(View v)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap result = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        v.draw(canvas);
        return result;
    }

    /**
     * 耗时方法，不可放在UI线程上
     *
     * @param url 网络图片的url
     * @return bitmap
     */
    public static Bitmap getImageFromWeb(String url)
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            return BitmapFactory.decodeStream(is);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getBitmap(String path, long maxSize)
    {

        File file = new File(path);

        long fileSize = file.length();
        if (fileSize <= maxSize)
        {
            return BitmapFactory.decodeFile(path);
        }
        else
        {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = (int) Math.ceil(fileSize / (float) maxSize);
            return BitmapFactory.decodeFile(path, options);
        }
    }
}
