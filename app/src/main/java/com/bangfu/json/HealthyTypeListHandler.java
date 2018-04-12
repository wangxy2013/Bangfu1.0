package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.HealthyTypeInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：健康狀況
 */
public class HealthyTypeListHandler extends JsonHandler
{

    private ArrayList<HealthyTypeInfo> mHealthyTypeInfoList = new ArrayList<>();

    public ArrayList<HealthyTypeInfo> getHealthyTypeInfoList()
    {
        return mHealthyTypeInfoList;
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
                    HealthyTypeInfo mHealthyTypeInfo = new HealthyTypeInfo(arr.getJSONObject(i));
                    mHealthyTypeInfoList.add(mHealthyTypeInfo);
                }
            }

            BfApplication.getInstance().setHealthyTypeInfoList(mHealthyTypeInfoList);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
