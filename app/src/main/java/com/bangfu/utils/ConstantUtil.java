package com.bangfu.utils;


import android.os.Environment;

public class ConstantUtil
{
    /**
     * 成功返回:true
     */
    public static final String RESULT_SUCCESS = "1";
    /**
     * 返回失败:false
     */
    public static final String RESULT_FAIL    = "-1";

    //第三方APK 相关操作
    public static final String THIRD_PATH        = Environment.getExternalStorageDirectory() + "/thirdParty/";
    public static final String THIRD_PACKAGENAME = "com.suqian.sunshinepovertyalleviation";
    public static final String THIRD_CLASSNAME   = "com.suqian.sunshinepovertyalleviation.ui.activity.LoginActivity";
    public static final String THIRD_APKNAME     = "jzfp.apk";



    private String TIANQI_DAILY_WEATHER_URL = "https://api.seniverse.com/v3/weather/daily.json";

    private String TIANQI_API_SECRET_KEY = "4eyjkglnnszar4gh"; //

    private String TIANQI_API_USER_ID = "UE8AEB0638"; //


}
