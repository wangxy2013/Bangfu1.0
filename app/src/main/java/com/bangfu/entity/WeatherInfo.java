package com.bangfu.entity;

import org.json.JSONObject;

/**
 * 作者：王先云 on 2017/12/4 15:50
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class WeatherInfo
{

    private String date;//private String 2017-12-04",
    private String templow;//private String 2",
    private String temp;//private String 7",
    private String img;//private String 1",
    private String week;//private String 星期一",
    private String city;//private String 宿迁",
    private String windpower;//private String 3级",


public  WeatherInfo(JSONObject obj)
{
    this.date = obj.optString("date");
    this.templow = obj.optString("templow");
    this.temp = obj.optString("temp");
    this.week = obj.optString("week");
    this.city = obj.optString("city");
    this.windpower = obj.optString("windpower");
}

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTemplow()
    {
        return templow;
    }

    public void setTemplow(String templow)
    {
        this.templow = templow;
    }

    public String getTemp()
    {
        return temp;
    }

    public void setTemp(String temp)
    {
        this.temp = temp;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public String getWeek()
    {
        return week;
    }

    public void setWeek(String week)
    {
        this.week = week;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getWindpower()
    {
        return windpower;
    }

    public void setWindpower(String windpower)
    {
        this.windpower = windpower;
    }
}
