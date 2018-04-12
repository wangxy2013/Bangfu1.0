package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.RelationTypeInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：关系
 */
public class RelationTypefoListHandler extends JsonHandler
{

    private ArrayList<RelationTypeInfo> mRelationTypeInfoList = new ArrayList<>();

    public ArrayList<RelationTypeInfo> getRelationTypeInfoList()
    {
        return mRelationTypeInfoList;
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
                    RelationTypeInfo mRelationTypeInfo = new RelationTypeInfo(arr.getJSONObject(i));
                    mRelationTypeInfoList.add(mRelationTypeInfo);
                }
            }
            BfApplication.getInstance().setRelationTypeInfoList(mRelationTypeInfoList);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
