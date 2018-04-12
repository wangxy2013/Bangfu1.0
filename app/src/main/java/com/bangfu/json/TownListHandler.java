package com.bangfu.json;


import com.bangfu.entity.EduAssistInfo;
import com.bangfu.entity.TownInfo;
import com.bangfu.entity.UserInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 */
public class TownListHandler extends JsonHandler
{

    private ArrayList<TownInfo> mTownInfoList = new ArrayList<>();

    public ArrayList<TownInfo> getTownInfoList()
    {
        return mTownInfoList;
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
                    JSONObject townObj = arr.optJSONObject(i);
                    TownInfo mTownInfo = new TownInfo(townObj);

                    JSONArray userArr= townObj.optJSONArray("userInfo");



                    if(null !=userArr)
                    {
                        ArrayList<UserInfo> userInfoList = new ArrayList<>();
                        for (int j = 0; j <userArr.length() ; j++)
                        {
                            UserInfo mUserInfo = new UserInfo(userArr.optJSONObject(j));
                            userInfoList.add(mUserInfo);
                        }
                        mTownInfo.setUserInfoList(userInfoList);
                    }

                    mTownInfoList.add(mTownInfo);
                }
            }


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
