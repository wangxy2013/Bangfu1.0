package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：行政区
 */
public class DeptInfoListHandler extends JsonHandler
{

    private ArrayList<DeptInfo> mDeptInfoList = new ArrayList<>();

    public ArrayList<DeptInfo> getDeptInfoList()
    {
        return mDeptInfoList;
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
                    DeptInfo mDeptInfo = new DeptInfo(arr.getJSONObject(i));
                    mDeptInfoList.add(mDeptInfo);
                }
            }
            BfApplication.getInstance().setDeptInfoList(mDeptInfoList);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
