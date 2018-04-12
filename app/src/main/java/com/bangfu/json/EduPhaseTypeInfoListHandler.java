package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.EduPhaseTypeInfo;
import com.bangfu.entity.WorkTypeInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：学段
 */
public class EduPhaseTypeInfoListHandler extends JsonHandler
{

    private ArrayList<EduPhaseTypeInfo> mEduPhaseTypeInfoList = new ArrayList<>();

    public ArrayList<EduPhaseTypeInfo> getEduPhaseTypeInfoList()
    {
        return mEduPhaseTypeInfoList;
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
                    EduPhaseTypeInfo mEduPhaseTypeInfo = new EduPhaseTypeInfo(arr.getJSONObject(i));
                    mEduPhaseTypeInfoList.add(mEduPhaseTypeInfo);
                }
            }

            BfApplication.getInstance().setEduPhaseTypeInfoList(mEduPhaseTypeInfoList);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
