package com.bangfu.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2017/12/2 01:41
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class HtyPresentInfo implements Serializable
{
           private String id;//11,
           private String pid;//10,
           private String illnessName;//"重症",
           private String hospital;//"回民医院",
           private String cost;//11111,
           private String payment_time;//null,
           private String descb;//"临时在",
           private String fid;//13,
           private String holderName;//"石剑峰",
           private String pname;//"刘云",
           private String paymentTime;//"2017-11-28"

    private String  relationTypeName;

    public  HtyPresentInfo(JSONObject obj)
    {
        this.id= obj.optString("id");
        this.pid= obj.optString("pid");
        this.illnessName= obj.optString("illnessName");
        this.hospital= obj.optString("hospital");
        this.cost= obj.optString("cost");
        this.payment_time= obj.optString("payment_time");
        this.descb= obj.optString("descb");
        this.fid= obj.optString("fid");
        this.holderName= obj.optString("holderName");
        this.paymentTime =obj.optString("paymentTime");
        this.pname= obj.optString("pname");
        this.relationTypeName = obj.optString("relationTypeName");

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getIllnessName()
    {
        return illnessName;
    }

    public void setIllnessName(String illnessName)
    {
        this.illnessName = illnessName;
    }

    public String getHospital()
    {
        return hospital;
    }

    public void setHospital(String hospital)
    {
        this.hospital = hospital;
    }

    public String getCost()
    {
        return cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    public String getPayment_time()
    {
        return payment_time;
    }

    public void setPayment_time(String payment_time)
    {
        this.payment_time = payment_time;
    }

    public String getDescb()
    {
        return descb;
    }

    public void setDescb(String descb)
    {
        this.descb = descb;
    }

    public String getFid()
    {
        return fid;
    }

    public void setFid(String fid)
    {
        this.fid = fid;
    }

    public String getHolderName()
    {
        return holderName;
    }

    public void setHolderName(String holderName)
    {
        this.holderName = holderName;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getPaymentTime()
    {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime)
    {
        this.paymentTime = paymentTime;
    }

    public String getRelationTypeName()
    {
        return relationTypeName;
    }

    public void setRelationTypeName(String relationTypeName)
    {
        this.relationTypeName = relationTypeName;
    }
}
