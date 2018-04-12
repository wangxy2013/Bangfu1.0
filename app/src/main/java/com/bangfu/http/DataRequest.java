package com.bangfu.http;


import android.content.Context;


import com.bangfu.json.JsonHandler;

import java.io.File;
import java.util.Map;


/**
 * @author 王先云
 * @date 2014年9月16日 上午9:40:51
 * @since[产品/模块版本]
 * @see
 */
public class DataRequest
{
    private static DataRequest sDataManage = null;

    public static DataRequest instance()
    {
        if (sDataManage == null)
        {
            sDataManage = new DataRequest();
        }
        return sDataManage;
    }

    /**
     * @param listener   监听
     * @param httpType   get 还是post
     * @param reqType    请求标识
     * @param valuePairs 请求参数
     * @param handler    json解析
     */
    public void request(Context mContext, String url, IRequestListener listener, int httpType, String reqType,
                        Map<String, String> valuePairs, JsonHandler handler)
    {
        HttpRequest request = new HttpRequest(mContext, httpType, reqType, url, valuePairs, listener, handler);
        ThreadPoolFactory.execute(request);
    }


    public void request(String url, IRequestListener listener, int httpType, String reqType,
                        File mFile, JsonHandler handler)
    {
        HttpRequest request = new HttpRequest(httpType, reqType, url, mFile, listener, handler);
        ThreadPoolFactory.execute(request);
    }

}
