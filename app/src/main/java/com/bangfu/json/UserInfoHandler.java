package com.bangfu.json;


import com.bangfu.entity.UserInfo;
import com.bangfu.utils.ConfigManager;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：获取用户个人信息
 */
public class UserInfoHandler extends JsonHandler
{
    private UserInfo mUserInfo;

    public UserInfo getUserInfo()
    {
        return mUserInfo;
    }

    @Override
    protected void parseJson(JSONObject obj) throws Exception
    {
        try
        {

            mUserInfo = new UserInfo(obj.optJSONObject("data"));

            if (null != mUserInfo)
            {
                ConfigManager.instance().setUserId(mUserInfo.getPid());
                ConfigManager.instance().setMobile(mUserInfo.getPhone());
                ConfigManager.instance().setUserName(mUserInfo.getPname());
                ConfigManager.instance().setHolder(mUserInfo.getIsHolder());
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
