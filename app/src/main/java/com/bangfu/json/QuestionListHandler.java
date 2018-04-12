package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.OrgInfo;
import com.bangfu.entity.QuestionInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：咨询问题
 */
public class QuestionListHandler extends JsonHandler
{

    private ArrayList<QuestionInfo> mQuestionInfoList = new ArrayList<>();

    public ArrayList<QuestionInfo> getQuestionInfoList()
    {
        return mQuestionInfoList;
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
                    QuestionInfo mQuestionInfo = new QuestionInfo(arr.getJSONObject(i));
                    mQuestionInfoList.add(mQuestionInfo);
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
