package com.bangfu.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2017/11/30 16:12
 * 邮箱：wangxianyun1@163.com
 * 描述：教育分段
 */
public class EduPhaseTypeInfo
{
    private String eid;// 1,
    private String phase;// 幼儿园

    public  EduPhaseTypeInfo(JSONObject obj)
    {
        this.eid = obj.optString("eid");
        this.phase = obj.optString("phase");
    }

    public String getEid()
    {
        return eid;
    }

    public void setEid(String eid)
    {
        this.eid = eid;
    }

    public String getPhase()
    {
        return phase;
    }

    public void setPhase(String phase)
    {
        this.phase = phase;
    }
}
