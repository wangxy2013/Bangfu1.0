package com.bangfu.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2017/11/29 21:47
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class NewsInfo   implements Serializable
{


    private String id;// 10,
    private String oid;// 1,
    private String title;// private String 111wwwwprivate String ,
    private String content;// private String wwwwwwprivate String ,
    private String typeId;// 5,
    private String attachmentId;// 0,
    private String pubTime;// null,
    private String stopFlag;// 1,
    private String oname;// private String 民政局private String ,
    private String typeName;// private String 扶贫政策private String ,
    private String attachmentName;// null,
    private String attachmentUrl;// null,
    private String pubDate;// private String 2017-11-28 08:16:56private String ,
    private String stopFlagDesc;// private String 启用private String


    public NewsInfo(JSONObject obj)
    {
        this.id = obj.optString("id");
        this.oid = obj.optString("oid");
        this.title = obj.optString("title");
        this.content = obj.optString("content");
        this.typeId = obj.optString("typeId");
        this.attachmentId = obj.optString("attachmentId");
        this.pubTime = obj.optString("pubTime");
        this.stopFlag =obj.optString("stopFlag");
        this.oname = obj.optString("oname");
        this.typeName = obj.optString("typeName");
        this.attachmentName = obj.optString("attachmentName");
        this.attachmentUrl= obj.optString("attachmentUrl");
        this.pubDate = obj.optString("pubDate");
        this.stopFlagDesc= obj.optString("stopFlagDesc");
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOid()
    {
        return oid;
    }

    public void setOid(String oid)
    {
        this.oid = oid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTypeId()
    {
        return typeId;
    }

    public void setTypeId(String typeId)
    {
        this.typeId = typeId;
    }

    public String getAttachmentId()
    {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId)
    {
        this.attachmentId = attachmentId;
    }

    public String getPubTime()
    {
        return pubTime;
    }

    public void setPubTime(String pubTime)
    {
        this.pubTime = pubTime;
    }

    public String getStopFlag()
    {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag)
    {
        this.stopFlag = stopFlag;
    }

    public String getOname()
    {
        return oname;
    }

    public void setOname(String oname)
    {
        this.oname = oname;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getAttachmentName()
    {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName)
    {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl()
    {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl)
    {
        this.attachmentUrl = attachmentUrl;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getStopFlagDesc()
    {
        return stopFlagDesc;
    }

    public void setStopFlagDesc(String stopFlagDesc)
    {
        this.stopFlagDesc = stopFlagDesc;
    }
}
