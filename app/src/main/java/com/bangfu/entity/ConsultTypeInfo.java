package com.bangfu.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2017/11/30 22:51
 * 邮箱：wangxianyun1@163.com
 * 描述：咨询问题类型
 */
public class ConsultTypeInfo
{
    private String id;
    private String  typeName;


    public ConsultTypeInfo(JSONObject obj)
    {
        this.id=obj.optString("id");
        this.typeName=obj.optString("typeName");
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }
}
