package com.bangfu.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2017/11/29 13:08
 * 邮箱：wangxianyun1@163.com
 * 描述：机构
 */
public class OrgInfo
{
    private String oid;// 1,
    private String oname;//  民政局 ,
    private String descb;// null,
    private String ulevel;// 0,
    private String superDid;// 0


    public OrgInfo(JSONObject obj)
    {
        this.oid = obj.optString("oid");
        this.oname = obj.optString("oname");
        this.descb = obj.optString("descb");
        this.ulevel = obj.optString("ulevel");
        this.superDid = obj.optString("superDid");

    }


    public String getOid()
    {
        return oid;
    }

    public void setOid(String oid)
    {
        this.oid = oid;
    }

    public String getOname()
    {
        return oname;
    }

    public void setOname(String oname)
    {
        this.oname = oname;
    }

    public String getDescb()
    {
        return descb;
    }

    public void setDescb(String descb)
    {
        this.descb = descb;
    }

    public String getUlevel()
    {
        return ulevel;
    }

    public void setUlevel(String ulevel)
    {
        this.ulevel = ulevel;
    }

    public String getSuperDid()
    {
        return superDid;
    }

    public void setSuperDid(String superDid)
    {
        this.superDid = superDid;
    }
}
