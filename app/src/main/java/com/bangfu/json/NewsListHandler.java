package com.bangfu.json;


import com.bangfu.entity.NewsInfo;
import com.bangfu.entity.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：新闻信息列表
 */
public class NewsListHandler extends JsonHandler
{

    private ArrayList<NewsInfo> mNewsInfoList = new ArrayList<>();

    public ArrayList<NewsInfo> getNewsInfoList()
    {
        return mNewsInfoList;
    }

    @Override
    protected void parseJson(JSONObject obj) throws Exception
    {
        try
        {


            JSONArray arr = obj.optJSONArray("data");

            if (null != arr)
            {
                for (int i = 0; i < arr.length(); i++)
                {
                    NewsInfo mNewsInfo = new NewsInfo(arr.getJSONObject(i));
                    mNewsInfoList.add(mNewsInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
