package com.bangfu.json;



import org.json.JSONObject;


/**
 * 作者：王先云 on 2016/9/7 13:48
 * 邮箱：wangxianyun1@163.com
 * 描述：返回结果
 */
public class ResultHandler extends JsonHandler
{
    private String content;

    public String getContent()
    {
        return content;
    }

    @Override
    protected void parseJson(JSONObject obj) throws Exception
    {
        content = obj.optString("content");
    }
}
