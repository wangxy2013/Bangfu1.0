package com.bangfu.entity;

/**
 * 作者：王先云 on 2017/11/16 17:15
 * 邮箱：wangxianyun1@163.com
 * 描述：农户基本信息
 */
public class FarmerDescInfo
{
    private String number;//家庭编号
    private String name;
    private String photo;
    private String sex;
    private String phone;
    private String address;
    private String relationship;
    private String population;//人口数
    private String identityCard;//身份证
    private String maritalStatus;//婚姻情况
    private String employmentStatus;//就业情况
    private String health;
    private String disease;//大病名称
    private String hospital;//就诊医院
    private String total;//总费用
    private int    editStatus; //编辑状态 0：//不可编辑   1：可编辑

    private int type;//1：自己 2：家人


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getRelationship()
    {
        return relationship;
    }

    public void setRelationship(String relationship)
    {
        this.relationship = relationship;
    }

    public String getPopulation()
    {
        return population;
    }

    public void setPopulation(String population)
    {
        this.population = population;
    }

    public String getIdentityCard()
    {
        return identityCard;
    }

    public void setIdentityCard(String identityCard)
    {
        this.identityCard = identityCard;
    }

    public String getMaritalStatus()
    {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus)
    {
        this.maritalStatus = maritalStatus;
    }

    public String getEmploymentStatus()
    {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus)
    {
        this.employmentStatus = employmentStatus;
    }

    public String getHealth()
    {
        return health;
    }

    public void setHealth(String health)
    {
        this.health = health;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getDisease()
    {
        return disease;
    }

    public void setDisease(String disease)
    {
        this.disease = disease;
    }

    public String getHospital()
    {
        return hospital;
    }

    public void setHospital(String hospital)
    {
        this.hospital = hospital;
    }

    public String getTotal()
    {
        return total;
    }

    public void setTotal(String total)
    {
        this.total = total;
    }

    public int getEditStatus()
    {
        return editStatus;
    }

    public void setEditStatus(int editStatus)
    {
        this.editStatus = editStatus;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }
}

