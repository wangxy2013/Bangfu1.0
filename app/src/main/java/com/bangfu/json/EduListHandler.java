package com.bangfu.json;


import com.bangfu.entity.EduAssistInfo;
import com.bangfu.entity.HtyPresentInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：教育救助
 */
public class EduListHandler extends JsonHandler
{

    private ArrayList<EduAssistInfo> mEduAssistInfoList = new ArrayList<>();

    public ArrayList<EduAssistInfo> getEduAssistInfoList()
    {
        return mEduAssistInfoList;
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
                    EduAssistInfo mEduAssistInfo = new EduAssistInfo(arr.getJSONObject(i));
                    mEduAssistInfoList.add(mEduAssistInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
