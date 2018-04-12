package com.bangfu.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2017/12/2 04:12
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class EduAssistInfo implements Serializable
{
     private String id;// 39,
             private String pid;// 8,
             private String eid;// 1,
             private String eschool;// private String 淮海中学private String ,
             private String eclassName;// private String 初三是一班private String ,
             private String ecost;// 1002.1,
             private String payment_time;// null,
             private String descb;// private String aaaaprivate String ,
             private String fid;// 13,
             private String holderName;// private String 石剑峰private String ,
             private String pname;// private String 刘云山private String ,
             private String phaseName;// private String 幼儿园private String ,
             private String paymentTime;// private String 2017-01-01private String
private String relationTypeName;
    public EduAssistInfo(JSONObject obj)
    {
        this.id = obj.optString("id");
        this.pid = obj.optString("pid");
        this.eid = obj.optString("eid");
        this.eschool = obj.optString("eschool");
        this.eclassName = obj.optString("eclassName");
        this.ecost = obj.optString("ecost");
        this.payment_time = obj.optString("payment_time");
        this.descb = obj.optString("descb");
        this.fid = obj.optString("fid");
        this.holderName = obj.optString("holderName");
        this.pname = obj.optString("pname");
        this.phaseName = obj.optString("phaseName");
        this.paymentTime = obj.optString("paymentTime");
        this.relationTypeName  = obj.optString("relationTypeName");
    }

    public String getRelationTypeName()
    {
        return relationTypeName;
    }

    public void setRelationTypeName(String relationTypeName)
    {
        this.relationTypeName = relationTypeName;
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

    public String getEid()
    {
        return eid;
    }

    public void setEid(String eid)
    {
        this.eid = eid;
    }

    public String getEschool()
    {
        return eschool;
    }

    public void setEschool(String eschool)
    {
        this.eschool = eschool;
    }

    public String getEclassName()
    {
        return eclassName;
    }

    public void setEclassName(String eclassName)
    {
        this.eclassName = eclassName;
    }

    public String getEcost()
    {
        return ecost;
    }

    public void setEcost(String ecost)
    {
        this.ecost = ecost;
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

    public String getPhaseName()
    {
        return phaseName;
    }

    public void setPhaseName(String phaseName)
    {
        this.phaseName = phaseName;
    }

    public String getPaymentTime()
    {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime)
    {
        this.paymentTime = paymentTime;
    }
}
