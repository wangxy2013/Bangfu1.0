package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.OrgInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：机构ID列表
 */
public class OrgInfoListHandler extends JsonHandler
{

    private ArrayList<OrgInfo> mOrgInfoList = new ArrayList<>();

    public ArrayList<OrgInfo> getOrgInfoList()
    {
        return mOrgInfoList;
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
                    OrgInfo mOrgInfo = new OrgInfo(arr.getJSONObject(i));
                    mOrgInfoList.add(mOrgInfo);
                }
            }

            BfApplication.getInstance().setOrgInfoList(mOrgInfoList);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
