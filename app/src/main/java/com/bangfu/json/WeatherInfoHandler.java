package com.bangfu.json;


import com.bangfu.entity.UserInfo;
import com.bangfu.entity.WeatherInfo;
import com.bangfu.utils.ConfigManager;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：
 */
public class WeatherInfoHandler extends JsonHandler
{
    private WeatherInfo mWeatherInfo;

    public WeatherInfo getWeatherInfo()
    {
        return mWeatherInfo;
    }

    @Override
    protected void parseJson(JSONObject obj) throws Exception
    {
        try
        {

            JSONObject object1 = obj.optJSONObject("result");
            if (null != object1)
            {
                mWeatherInfo = new WeatherInfo(object1.optJSONObject("result"));
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
