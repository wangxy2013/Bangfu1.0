package com.bangfu.json;


import com.bangfu.entity.HtyPresentInfo;
import com.bangfu.entity.NewsInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：大病救助
 */
public class HtyListHandler extends JsonHandler
{

    private ArrayList<HtyPresentInfo> mHtyPresentInfoList = new ArrayList<>();

    public ArrayList<HtyPresentInfo> getHtyPresentInfoList()
    {
        return mHtyPresentInfoList;
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
                    HtyPresentInfo mHtyPresentInfo = new HtyPresentInfo(arr.getJSONObject(i));
                    mHtyPresentInfoList.add(mHtyPresentInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
