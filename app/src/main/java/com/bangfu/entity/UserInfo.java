package com.bangfu.entity;//

import com.bangfu.utils.StringUtils;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2017/11/28 16:41
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class UserInfo implements Serializable
{

    private String pid;// 1,
    private String did;// 1,
    private String rid;// 1,
    private String oid;// 1,
    private String pname;// private String 综合管理员private String ,
    private String sex;// null,
    private String relationType;// 0,
    private String cid;// null,
    private String workType;// 0,
    private String workTypeName;

    private String marriedType;// 0,
    private String phone;// private String 13912341234private String ,
    private String healthyType;// 0,
    private String oTime;// 1511670150000,
    private String isHolder;// 0,:工作人员
    private String faddr;// null,
    private String descb;// null
    private String dname;// 塘圩村
    private String oname;//
    private String rname;//
    private String sexName;//
    private String relationTypeName;// 户主
    private String marriedTypeName;// 未知
    private String healthyTypeName;// 健康
    private String holderName;// 刘云山
    private String duty;// 户主
    private String amount;// 1
    private String fid;// 1

    private int type;
    private int editStatus;

    public UserInfo(){}
    public UserInfo(JSONObject obj)
    {

        this.cid = obj.optString("cid");
        this.fid = obj.optString("fid");
        this.pid = obj.optString("pid");
        this.did = obj.optString("did");
        this.rid = obj.optString("rid");
        this.oid = obj.optString("oid");
        this.pname = obj.optString("pname");
        this.sex = obj.optString("sex");
        this.relationType = obj.optString("relationType");
        this.workType = obj.optString("workType");
        this.marriedType = obj.optString("marriedType");
        this.phone = obj.optString("phone");
        this.healthyType = obj.optString("healthyType");
        this.oTime = obj.optString("oTime");
        this.isHolder = obj.optString("isHolder");
        this.faddr = obj.optString("faddr");
        this.descb = obj.optString("descb");
        this.dname = obj.optString("dname");
        this.oname = obj.optString("oname");
        this.rname = obj.optString("rname");
        this.sexName = obj.optString("sexName");
        this.relationTypeName = obj.optString("relationTypeName");
        this.marriedTypeName = obj.optString("marriedTypeName");
        this.healthyTypeName = obj.optString("healthyTypeName");
        this.holderName = obj.optString("holderName");
        this.duty = obj.optString("duty");

        if (!StringUtils.stringIsEmpty(obj.optString("amount")))
        {
            this.amount = obj.optString("amount");
        }
        this.workTypeName = obj.optString("workTypeName");

    }

    public String getWorkTypeName()
    {
        return workTypeName;
    }

    public void setWorkTypeName(String workTypeName)
    {
        this.workTypeName = workTypeName;
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getDid()
    {
        return did;
    }

    public void setDid(String did)
    {
        this.did = did;
    }

    public String getRid()
    {
        return rid;
    }

    public void setRid(String rid)
    {
        this.rid = rid;
    }

    public String getOid()
    {
        return oid;
    }

    public void setOid(String oid)
    {
        this.oid = oid;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getRelationType()
    {
        return relationType;
    }

    public void setRelationType(String relationType)
    {
        this.relationType = relationType;
    }

    public String getCid()
    {
        return cid;
    }

    public void setCid(String cid)
    {
        this.cid = cid;
    }

    public String getWorkType()
    {
        return workType;
    }

    public void setWorkType(String workType)
    {
        this.workType = workType;
    }

    public String getMarriedType()
    {
        return marriedType;
    }

    public void setMarriedType(String marriedType)
    {
        this.marriedType = marriedType;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getHealthyType()
    {
        return healthyType;
    }

    public void setHealthyType(String healthyType)
    {
        this.healthyType = healthyType;
    }

    public String getoTime()
    {
        return oTime;
    }

    public void setoTime(String oTime)
    {
        this.oTime = oTime;
    }

    public String getIsHolder()
    {
        return isHolder;
    }

    public void setIsHolder(String isHolder)
    {
        this.isHolder = isHolder;
    }

    public String getFaddr()
    {
        return faddr;
    }

    public void setFaddr(String faddr)
    {
        this.faddr = faddr;
    }

    public String getDescb()
    {
        return descb;
    }

    public void setDescb(String descb)
    {
        this.descb = descb;
    }

    public String getDname()
    {
        return dname;
    }

    public void setDname(String dname)
    {
        this.dname = dname;
    }

    public String getOname()
    {
        return oname;
    }

    public void setOname(String oname)
    {
        this.oname = oname;
    }

    public String getRname()
    {
        return rname;
    }

    public void setRname(String rname)
    {
        this.rname = rname;
    }

    public String getSexName()
    {
        return sexName;
    }

    public void setSexName(String sexName)
    {
        this.sexName = sexName;
    }

    public String getRelationTypeName()
    {
        return relationTypeName;
    }

    public void setRelationTypeName(String relationTypeName)
    {
        this.relationTypeName = relationTypeName;
    }

    public String getMarriedTypeName()
    {
        return marriedTypeName;
    }

    public void setMarriedTypeName(String marriedTypeName)
    {
        this.marriedTypeName = marriedTypeName;
    }

    public String getHealthyTypeName()
    {
        return healthyTypeName;
    }

    public void setHealthyTypeName(String healthyTypeName)
    {
        this.healthyTypeName = healthyTypeName;
    }

    public String getHolderName()
    {
        return holderName;
    }

    public void setHolderName(String holderName)
    {
        this.holderName = holderName;
    }

    public String getDuty()
    {
        return duty;
    }

    public void setDuty(String duty)
    {
        this.duty = duty;
    }

    public String getFid()
    {
        return fid;
    }

    public void setFid(String fid)
    {
        this.fid = fid;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getEditStatus()
    {
        return editStatus;
    }

    public void setEditStatus(int editStatus)
    {
        this.editStatus = editStatus;
    }





}
