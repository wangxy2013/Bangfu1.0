package com.bangfu.json;


import android.content.Context;
import android.content.Intent;


import com.bangfu.utils.ConstantUtil;

import org.json.JSONObject;


public abstract class JsonHandler
{

    private String resultCode = null;
    private String resultMsg  = null;

    protected abstract void parseJson(JSONObject obj) throws Exception;

    public void parseJson(Context mContext, String jsonString)
    {
        try
        {
            if (null == jsonString)
            {
                setResultCode(ConstantUtil.RESULT_FAIL);
            }
            else
            {
                JSONObject jsonOject = new JSONObject(jsonString);

                if ("10000".equals(jsonOject.optString("code")))
                {
                    setResultCode(ConstantUtil.RESULT_SUCCESS);
                }
                else
                {

                    if ("true".equals(jsonOject.optString("success")))
                    {
                        setResultCode(ConstantUtil.RESULT_SUCCESS);
                    }
                    else
                    {
                        setResultCode(ConstantUtil.RESULT_FAIL);

                    }
                    setResultMsg(jsonOject.optString("message"));
                }

                //  JSONObject obj = jsonOject.optJSONObject("data");
                if (null != jsonOject) parseJson(jsonOject);
            }
        } catch (Exception e)
        {
            // e.printStackTrace();
            setResultCode(ConstantUtil.RESULT_FAIL);
            setResultMsg("网络请求失败...");
        }

    }

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getResultMsg()
    {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg)
    {
        this.resultMsg = resultMsg;
    }

}
