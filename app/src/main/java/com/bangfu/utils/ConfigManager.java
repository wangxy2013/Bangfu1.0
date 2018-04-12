package com.bangfu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author
 */
public class ConfigManager
{
    /**
     * instance
     */
    private static ConfigManager     sConfigManager     = null;
    /**
     * mCtx
     */
    private        Context           mCtx               = null;
    /**
     * SharedPreferences
     */
    private        SharedPreferences mSharedPreferences = null;
    /**
     * config
     */
    private static String            CONFIG_NAME        = "config";

    private static final String IS_FRIST_LOGING = "IS_FRIST_LOGING";
    private static final String USER_PWD        = "user_pwd";//密码
    private static final String USER_NAME       = "user_name";// 用户名
    private static final String USER_ID         = "user_id";
    private static final String PREF_UUID       = "miei";
    private static final String UNIQUE_CODE     = "unique_code";
    private static final String USER_MOBILE     = "mobile";
    private static final String IS_SAVE_PWD     = "is_save_pwd";
    private static final String GUEST_ID        = "guest_id";
    private static final String USER_NICK_NAME  = "user_nick_name";
    private static final String SEARCH_MY_GROUP = "SEARCH_MY_GROUP";
    private static final String SEARCH_GROUP    = "SEARCH_GROUP";
    private static final String SEARCH_VIDEO    = "SEARCH_VIDEO";
    private static final String REGISER_ID      = "regiser_id";
    private static final String IS_HOLDER      = "is_holder";
    private static final String IS_UPLOAD_CHANNEL = "is_upload_channel";

    /**
     * 返回实例
     *
     * @return
     */
    public static ConfigManager instance()
    {
        if (sConfigManager == null)
        {
            sConfigManager = new ConfigManager();
        }

        return sConfigManager;
    }

    /**
     * init shared Preferences
     *
     * @param context
     */
    public void init(Context context)
    {
        mCtx = context;
        mSharedPreferences = context.getSharedPreferences(CONFIG_NAME, 0);

    }


    public String getUserPwd()
    {
        return mSharedPreferences.getString(USER_PWD, "");
    }

    public void setUserPwd(String pwd)
    {
        mSharedPreferences.edit().putString(USER_PWD, pwd).commit();
        return;
    }


    // 用户名
    public String getUserName()
    {
        return mSharedPreferences.getString(USER_NAME, "");
    }

    public void setUserName(String userName)
    {
        mSharedPreferences.edit().putString(USER_NAME, userName).commit();
    }


    public String getUserID()
    {
        return mSharedPreferences.getString(USER_ID, "");
    }

    public void setUserId(String userId)
    {
        mSharedPreferences.edit().putString(USER_ID, userId).commit();
        return;
    }

    public void setUUID(String uuid)
    {
        mSharedPreferences.edit().putString(PREF_UUID, uuid).commit();
    }

    public String getUUID()
    {
        return mSharedPreferences.getString(PREF_UUID, null);
    }


    public String getUniqueCode()
    {
        return mSharedPreferences.getString(UNIQUE_CODE, "");
    }

    public void setUniqueCode(String uniqueCode)
    {
        mSharedPreferences.edit().putString(UNIQUE_CODE, uniqueCode).commit();
    }

    public String getMobile()
    {
        return mSharedPreferences.getString(USER_MOBILE, "");
    }

    public void setMobile(String mobile)
    {
        mSharedPreferences.edit().putString(USER_MOBILE, mobile).commit();
    }


    public boolean getIsSavePwd()
    {
        return mSharedPreferences.getBoolean(IS_SAVE_PWD, false);
    }

    public void setIsSavePwd(boolean status)
    {
        mSharedPreferences.edit().putBoolean(IS_SAVE_PWD, status).commit();
    }


    public void setGUESTID(String id)
    {

        mSharedPreferences.edit().putString(GUEST_ID, id).commit();
    }

    public String getGUESTID()
    {
        //return StringUtils.getRandomString(5);
        return mSharedPreferences.getString(GUEST_ID, "");
    }




    public void setHolder(String isHolder)
    {

        mSharedPreferences.edit().putString(IS_HOLDER, isHolder).commit();
    }

    public String getHolder()
    {
        return mSharedPreferences.getString(IS_HOLDER, "0");
    }








    public void setUserNickName(String nickName)
    {
        mSharedPreferences.edit().putString(USER_NICK_NAME, nickName).commit();
    }

    public String getUserNickName()
    {
        return mSharedPreferences.getString(USER_NICK_NAME, "");
    }

    public void setRegisterId(String registerId)
    {
        mSharedPreferences.edit().putString(REGISER_ID, registerId).commit();
    }

    public String getRegisterId()
    {
        return mSharedPreferences.getString(REGISER_ID, "");
    }

    public boolean getIsFristLogin()
    {
        return mSharedPreferences.getBoolean(IS_FRIST_LOGING, true);
    }

    public void setIsFristLogin(boolean status)
    {
        mSharedPreferences.edit().putBoolean(IS_FRIST_LOGING, status).commit();
    }

    public boolean getIsUploadChannel()
    {
        return mSharedPreferences.getBoolean(IS_UPLOAD_CHANNEL, false);
    }

    public void setIsUploadChannel(boolean status)
    {
        mSharedPreferences.edit().putBoolean(IS_UPLOAD_CHANNEL, status).commit();
    }


}

