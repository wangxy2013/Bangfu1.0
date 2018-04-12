package com.bangfu.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2018/3/29 10:15
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class LunarInfo
{
    private String year;//2018,
    private String month;//3,
    private String day;//29,
    private String lunarYear;//2018,
    private String lunarMonth;//2,
    private String lunarDay;//13,
    private String cnyear;//贰零壹捌
    private String cnmonth;//二
    private String cnday;//十三
    private String hyear;//戊戌
    private String cyclicalYear;//戊戌
    private String cyclicalMonth;//乙卯
    private String cyclicalDay;//庚申
    private String suit;//祭祀,解除,入殓,除服,成服,移柩,启钻,安葬,修坟,立碑,谢土,沐浴,扫舍,捕捉,取渔,结网,畋猎,理发
    private String taboo;//安床,嫁娶,作灶,入宅
    private String animal;//狗
    private String week;//Thursday


    public LunarInfo(JSONObject obj)
    {
        this.year = obj.optString("year");
        this.month = obj.optString("month");
        this.day = obj.optString("day");
        this.lunarYear = obj.optString("lunarYear");
        this.lunarMonth = obj.optString("lunarMonth");
        this.lunarDay = obj.optString("lunarDay");
        this.cnmonth = obj.optString("cnmonth");
        this.cnday = obj.optString("cnday");
        this.cyclicalYear = obj.optString("cyclicalYear");
        this.cyclicalMonth = obj.optString("cyclicalMonth");
        this.cyclicalDay = obj.optString("cyclicalDay");
        this.suit = obj.optString("suit");
        this.taboo = obj.optString("taboo");
        this.animal = obj.optString("animal");
        this.week = obj.optString("week");
    }


    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public String getLunarYear()
    {
        return lunarYear;
    }

    public void setLunarYear(String lunarYear)
    {
        this.lunarYear = lunarYear;
    }

    public String getLunarMonth()
    {
        return lunarMonth;
    }

    public void setLunarMonth(String lunarMonth)
    {
        this.lunarMonth = lunarMonth;
    }

    public String getLunarDay()
    {
        return lunarDay;
    }

    public void setLunarDay(String lunarDay)
    {
        this.lunarDay = lunarDay;
    }

    public String getCnyear()
    {
        return cnyear;
    }

    public void setCnyear(String cnyear)
    {
        this.cnyear = cnyear;
    }

    public String getCnmonth()
    {
        return cnmonth;
    }

    public void setCnmonth(String cnmonth)
    {
        this.cnmonth = cnmonth;
    }

    public String getCnday()
    {
        return cnday;
    }

    public void setCnday(String cnday)
    {
        this.cnday = cnday;
    }

    public String getHyear()
    {
        return hyear;
    }

    public void setHyear(String hyear)
    {
        this.hyear = hyear;
    }

    public String getCyclicalYear()
    {
        return cyclicalYear;
    }

    public void setCyclicalYear(String cyclicalYear)
    {
        this.cyclicalYear = cyclicalYear;
    }

    public String getCyclicalMonth()
    {
        return cyclicalMonth;
    }

    public void setCyclicalMonth(String cyclicalMonth)
    {
        this.cyclicalMonth = cyclicalMonth;
    }

    public String getCyclicalDay()
    {
        return cyclicalDay;
    }

    public void setCyclicalDay(String cyclicalDay)
    {
        this.cyclicalDay = cyclicalDay;
    }

    public String getSuit()
    {
        return suit;
    }

    public void setSuit(String suit)
    {
        this.suit = suit;
    }

    public String getTaboo()
    {
        return taboo;
    }

    public void setTaboo(String taboo)
    {
        this.taboo = taboo;
    }

    public String getAnimal()
    {
        return animal;
    }

    public void setAnimal(String animal)
    {
        this.animal = animal;
    }

    public String getWeek()
    {
        return week;
    }

    public void setWeek(String week)
    {
        this.week = week;
    }
}
