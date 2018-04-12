package com.bangfu.json;


import com.bangfu.BfApplication;
import com.bangfu.entity.ConsultTypeInfo;
import com.bangfu.entity.DeptInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：咨询问题类型
 */
public class ConsultTypeInfoListHandler extends JsonHandler
{

    private ArrayList<ConsultTypeInfo> mConsultTypeInfoList = new ArrayList<>();


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
                    ConsultTypeInfo mConsultTypeInfo = new ConsultTypeInfo(arr.getJSONObject(i));
                    mConsultTypeInfoList.add(mConsultTypeInfo);
                }
            }
            BfApplication.getInstance().setConsultTypeInfoList(mConsultTypeInfoList);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
