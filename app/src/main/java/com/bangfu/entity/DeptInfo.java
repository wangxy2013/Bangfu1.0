package com.bangfu.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2017/11/30 22:40
 * 邮箱：wangxianyun1@163.com
 * 描述：行政区
 */
public class DeptInfo
{
            private String did;// 1,
            private String dname;// 宿迁市
            private String descb;// 宿迁市
            private String superDid;// 1,
            private String superDidName;// 宿迁市
            private String level;// 4,
            private String levelName;// 地市级


public  DeptInfo(JSONObject obj)
{
    this.did = obj.optString("did");
    this.dname = obj.optString("dname");
    this.descb = obj.optString("descb");
    this.superDid = obj.optString("superDid");
    this.superDidName = obj.optString("superDidName");
    this.level = obj.optString("level");
    this.levelName = obj.optString("levelName");
}

    public String getDid()
    {
        return did;
    }

    public void setDid(String did)
    {
        this.did = did;
    }

    public String getDname()
    {
        return dname;
    }

    public void setDname(String dname)
    {
        this.dname = dname;
    }

    public String getDescb()
    {
        return descb;
    }

    public void setDescb(String descb)
    {
        this.descb = descb;
    }

    public String getSuperDid()
    {
        return superDid;
    }

    public void setSuperDid(String superDid)
    {
        this.superDid = superDid;
    }

    public String getSuperDidName()
    {
        return superDidName;
    }

    public void setSuperDidName(String superDidName)
    {
        this.superDidName = superDidName;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getLevelName()
    {
        return levelName;
    }

    public void setLevelName(String levelName)
    {
        this.levelName = levelName;
    }
}
