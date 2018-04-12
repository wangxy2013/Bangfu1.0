package com.bangfu.json;


import com.bangfu.entity.LunarInfo;
import com.bangfu.entity.UserInfo;
import com.bangfu.utils.ConfigManager;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 */
public class LunarInfoHandler extends JsonHandler
{

    private LunarInfo lunarInfo;

    public LunarInfo getLunarInfo()
    {
        return lunarInfo;
    }

    @Override
    protected void parseJson(JSONObject obj) throws Exception
    {
        try
        {

            lunarInfo = new LunarInfo(obj.optJSONObject("data"));


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
