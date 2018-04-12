package com.bangfu.entity;

import org.json.JSONObject;

public class VersionInfo
{
    public String version;//版本号
    public String version_desc;//版本描述
    public String version_name;//
    public String link;//下载链接
    public String forcedup;//是否强制升级。
    public String add_time;
    public String update_time;
    public String del_flag;


    public VersionInfo(JSONObject obj)
    {
        this.version = obj.optString("version");
        this.version_desc = obj.optString("version_desc");
        this.version_name = obj.optString("version_name");
        this.link = obj.optString("link");
        this.forcedup = obj.optString("forcedup");
        this.add_time = obj.optString("add_time");
        this.update_time = obj.optString("update_time");
        this.del_flag = obj.optString("del_flag");
    }


    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getVersion_desc()
    {
        return version_desc;
    }

    public void setVersion_desc(String version_desc)
    {
        this.version_desc = version_desc;
    }

    public String getVersion_name()
    {
        return version_name;
    }

    public void setVersion_name(String version_name)
    {
        this.version_name = version_name;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getForcedup()
    {
        return forcedup;
    }

    public void setForcedup(String forcedup)
    {
        this.forcedup = forcedup;
    }

    public String getAdd_time()
    {
        return add_time;
    }

    public void setAdd_time(String add_time)
    {
        this.add_time = add_time;
    }

    public String getUpdate_time()
    {
        return update_time;
    }

    public void setUpdate_time(String update_time)
    {
        this.update_time = update_time;
    }

    public String getDel_flag()
    {
        return del_flag;
    }

    public void setDel_flag(String del_flag)
    {
        this.del_flag = del_flag;
    }
}
