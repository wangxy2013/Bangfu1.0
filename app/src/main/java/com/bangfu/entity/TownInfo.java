package com.bangfu.entity;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王先云 on 2018/4/9 14:23
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class TownInfo implements Serializable
{
    private String did;//10,
    private String dname;//"古城街道",
    private String descb;//"宿迁市 宿城区 古城街道",
    private String superDid;//2,
    private String ulevel;//2,
    private String flag;//1,
    private String flaged;//null,

    private List<UserInfo> userInfoList;


    public TownInfo(JSONObject obj)
    {
        this.did = obj.optString("did");
        this.dname = obj.optString("dname");
        this.descb = obj.optString("descb");
        this.superDid = obj.optString("superDid");
        this.ulevel = obj.optString("ulevel");
        this.flag = obj.optString("flag");
        this.flaged = obj.optString("flaged");

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

    public String getUlevel()
    {
        return ulevel;
    }

    public void setUlevel(String ulevel)
    {
        this.ulevel = ulevel;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getFlaged()
    {
        return flaged;
    }

    public void setFlaged(String flaged)
    {
        this.flaged = flaged;
    }

    public List<UserInfo> getUserInfoList()
    {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList)
    {
        this.userInfoList = userInfoList;
    }
}
