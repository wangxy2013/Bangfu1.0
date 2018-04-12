package com.bangfu.http;

import android.content.Context;
import android.util.Log;

import com.bangfu.json.JsonHandler;
import com.bangfu.utils.APPUtils;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.LogUtil;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class HttpRequest implements Runnable
{


    public static final int GET     = 0;
    public static final int POST    = 2;
    public static final int UPLOAD  = 3;
    private             int mAction = GET;

    private String              mType;
    private String              urlRequest;
    private Map<String, String> valuePair;
    private IRequestListener mIRequestListener = null;
    private JsonHandler      mHandler          = null;
    private File    mFile;
    private Context mContext;

    public HttpRequest(int action, String type, String url, IRequestListener listener, JsonHandler handler)
    {
        mAction = action;
        urlRequest = url;
        mType = type;
        mIRequestListener = listener;
        mHandler = handler;
    }

    public HttpRequest(Context mContext, int action, String type, String url, Map<String, String> valuePairs,
                       IRequestListener listener, JsonHandler handler)
    {
        this(action, type, url, listener, handler);
        this.mContext = mContext;
        this.valuePair = valuePairs;
//        valuePair.put("os_version", APPUtils.getCurrentapiVersion() + "");
//        valuePair.put("os", "android");
        //valuePair.put("channel", ConstantUtil.CHANNEL_NO);
//        valuePair.put("version", APPUtils.getVersionName(mContext));
        if (valuePair == null)
        {
            valuePair = new HashMap<>();
        }
    }

    public HttpRequest(int action, String type, String url, File mFile,
                       IRequestListener listener, JsonHandler handler)
    {
        this(action, type, url, listener, handler);
        this.mFile = mFile;
    }

    public void run()
    {
        Object result = doRequest();
        Log.e("tag", "response result : " + result);
        try
        {

            //            String target = RSAUtils.decrypt((String)result, ConstantUtil.PRIVATE_KEY);
            //            Log.e("tag", "response result : " + target);
            if (mIRequestListener != null)
            {
                if (result != null)
                {
                    if (mHandler != null)
                    {
                        mHandler.parseJson(mContext, result.toString());
                        mIRequestListener.notify(mType, mHandler.getResultCode(), mHandler.getResultMsg(), mHandler);
                    }

                }
                else
                {
                    mIRequestListener.notify(mType, "-1", "网络请求失败...", null);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public Object doRequest()
    {
        try
        {
            if (mAction == GET)
            {
                return doGet();
            }
            else if (mAction == POST)
            {
                return doPost();
            }
            else if (mAction == UPLOAD)
            {
                return doUpload();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;

    }


    private String doGet() throws Exception
    {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(10, TimeUnit.SECONDS);
        //        mOkHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        //        mOkHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        //        mOkHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
        urlRequest = urlRequest + concatParams();
        LogUtil.e("TAG", urlRequest);
        Request request = new Request.Builder().url(urlRequest).build();
        Response response = mOkHttpClient.newCall(request).execute();// execute
        if (response.isSuccessful())
        {
            System.out.println(response.code());
            String body = response.body().string();
            return body;
        }
        return null;
    }


    private String doPost() throws Exception
    {

        LogUtil.e("TAG", urlRequest + concatParams());
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        //                mOkHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        //                mOkHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        //                mOkHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
        /**
         * 3.0之后版本
         */
        //   FormBody.Builder builder = new FormBody.Builder();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        /**
         * 在这对添加的参数进行遍历，map遍历有四种方式，如果想要了解的可以网上查找
         */
        for (Map.Entry<String, String> map : valuePair.entrySet())
        {
            String key = map.getKey().toString();
            String value = null;
            /**
             * 判断值是否是空的
             */
            if (map.getValue() == null)
            {
                value = "";
            }
            else
            {
                value = map.getValue();
            }
            /**
             * 把key和value添加到formbody中
             */
            builder.add(key, value);
        }
        RequestBody requestBody = builder.build();

        //当写请求头的时候，使用 header(name, value) 可以设置唯一的name、value。如果已经有值，旧的将被移除，然后添加新的。使用 addHeader(name, value) 可以添加多值（添加，不移除已有的）。
        Request request = new Request.Builder().url(urlRequest).header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5").addHeader("Accept", "application/vnd.github.v3+json")
                .post(requestBody).build();

        Response response = mOkHttpClient.newCall(request).execute();// execute
        if (response.isSuccessful())
        {
            System.out.println(response.code());
            String body = response.body().string();
            return body;

        }
        return null;
    }


    private String doUpload() throws Exception
    {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        //添加一个文本表单参数
        multipartBuilder.addFormDataPart("filename", mFile.getName());
        LogUtil.d("TAG", "filename:" + mFile.getName());
        multipartBuilder.addFormDataPart("face", mFile.getName(), RequestBody.create(MediaType.parse("image/png"), mFile));
        LogUtil.d("TAG", "upfacepic:" + mFile.getName());
        //构造文件上传时的请求对象Request
        Request request = new Request.Builder().url(urlRequest).post(multipartBuilder.build()).build();
        Response response = mOkHttpClient.newCall(request).execute();// execute
        if (response.isSuccessful())
        {
            System.out.println(response.code());
            String body = response.body().string();
            return body;

        }
        return null;
    }


    //    /**
    //     * 提交参数里有文件的数据
    //     *
    //     * @return 服务器返回结果
    //     * @throws Exception
    //     */
    //    //发送个人头像
    //    public String uploadSubmit() throws Exception
    //    {
    //        System.out.println("11111");
    //        HttpPost post = new HttpPost();
    //        HttpClient httpClient = new DefaultHttpClient();
    //        MultipartEntity entity = new MultipartEntity();
    //        //        if (valuePairs != null && !valuePairs.isEmpty()) {
    //        //            for (Map.Entry<String, String> entry : valuePairs.entrySet()) {
    //        //                if (entry.getValue() != null
    //        //                        && entry.getValue().trim().length() > 0) {
    //        //                    entity.addPart(entry.getKey(),new StringBody(entry.getValue(),
    //        //                            Charset.forName(org.apache.http.protocol.HTTP.UTF_8)));
    //        //                }
    //        //            }
    //        //        }
    //        entity.addPart("filename", new StringBody(mFile.getName()));
    //        // 添加文件参数
    //        if (mFile != null && mFile.exists())
    //        {
    //            entity.addPart("upfacepic", new FileBody(mFile));
    //        }
    //        post.setEntity(entity);
    //        HttpResponse response = httpClient.execute(post);
    //        int stateCode = response.getStatusLine().getStatusCode();
    //        StringBuffer sb = new StringBuffer();
    //        System.out.println("222");
    //        if (stateCode == HttpStatus.SC_OK)
    //        {
    //            System.out.println("333");
    //            HttpEntity result = response.getEntity();
    //            if (result != null)
    //            {
    //                InputStream is = result.getContent();
    //                BufferedReader br = new BufferedReader(new InputStreamReader(is));
    //                String tempLine;
    //                while ((tempLine = br.readLine()) != null)
    //                {
    //                    sb.append(tempLine);
    //                }
    //            }
    //        }
    //        post.abort();
    //        return sb.toString();
    //    }

    private String concatParams()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("?");
      //  sb.append("&");
        for (Map.Entry<String, String> map : valuePair.entrySet())
        {
            String key = map.getKey().toString();
            String value = null;
            if (map.getValue() == null)
            {
                value = "";
            }
            else
            {
                value = map.getValue();
            }

            sb.append(key).append("=").append(value).append("&");


        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

}
