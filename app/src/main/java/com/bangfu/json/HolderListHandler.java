package com.bangfu.json;


import com.bangfu.entity.UserInfo;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：查询户主信息列表
 */
public class HolderListHandler extends JsonHandler
{

    private ArrayList<UserInfo> mUserInfoList = new ArrayList<>();

    public ArrayList<UserInfo> getUserInfoList()
    {
        return mUserInfoList;
    }

    @Override
    protected void parseJson(JSONObject obj) throws Exception
    {
        try
        {
            JSONArray arr = obj.optJSONArray("data");
            String total = obj.optString("total");
            if (null != arr)
            {
                for (int i = 0; i < arr.length(); i++)
                {
                    UserInfo mUserInfo = new UserInfo(arr.getJSONObject(i));
                    mUserInfo.setType(3);

                    if (StringUtils.stringIsEmpty(mUserInfo.getAmount()))
                    {
                        mUserInfo.setAmount(total);
                    }
                    mUserInfoList.add(mUserInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
