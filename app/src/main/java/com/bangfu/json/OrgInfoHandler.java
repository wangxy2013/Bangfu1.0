package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.OrgInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：机构ID列表
 */
public class OrgInfoHandler extends JsonHandler
{

    private OrgInfo  mOrgInfo;

    public OrgInfo getOrgInfo()
    {
        return mOrgInfo;
    }

    @Override
    protected void parseJson(JSONObject obj) throws Exception
    {
        try
        {



            mOrgInfo = new OrgInfo(obj.optJSONObject("data"));

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
