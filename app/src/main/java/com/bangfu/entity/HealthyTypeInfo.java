package com.bangfu.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2017/11/30 22:49
 * 邮箱：wangxianyun1@163.com
 * 描述：健康状况
 */
public class HealthyTypeInfo
{
    private String id;
    private String type_name;

    public HealthyTypeInfo(JSONObject obj)
    {
        this.id = obj.optString("id");
        this.type_name = obj.optString("type_name");
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getType_name()
    {
        return type_name;
    }

    public void setType_name(String type_name)
    {
        this.type_name = type_name;
    }
}
