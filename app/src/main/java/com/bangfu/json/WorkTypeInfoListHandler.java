package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.WorkTypeInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：工作状况
 */
public class WorkTypeInfoListHandler extends JsonHandler
{

    private ArrayList<WorkTypeInfo> mWorkTypeInfoList = new ArrayList<>();

    public ArrayList<WorkTypeInfo> getWorkTypeInfoList()
    {
        return mWorkTypeInfoList;
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
                    WorkTypeInfo mWorkTypeInfo = new WorkTypeInfo(arr.getJSONObject(i));
                    mWorkTypeInfoList.add(mWorkTypeInfo);
                }
            }
            BfApplication.getInstance().setWorkTypeInfoList(mWorkTypeInfoList);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
