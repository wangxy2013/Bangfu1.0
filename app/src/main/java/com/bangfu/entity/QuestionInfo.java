package com.bangfu.entity;


import org.json.JSONObject;

import java.io.Serializable;

/**
 * 作者：王先云 on 2017/11/20 17:03
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class QuestionInfo implements Serializable
{

    //
    //    `id` int(11) NOT NULL COMMENT '信息ID',
    //        `pid` int(10) NOT NULL COMMENT '咨询人ID',
    //        `subTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '应答时间',
    //        `content` varchar(10000) DEFAULT NULL COMMENT '咨询内容',
    //        `isAnony` int(11) DEFAULT '0' COMMENT '是否匿名，0-非匿名，1匿名',
    //        `questionTypeId` int(11) DEFAULT NULL COMMENT '咨询问题类型ID',
    //        `oid` int(11) DEFAULT NULL COMMENT '咨询目标机构ID',
    //        `answer` varchar(10000) DEFAULT NULL COMMENT '应答内容',
    //        `ansTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '应答时间',
    //        `aid` int(11) DEFAULT NULL COMMENT '应答人ID',
    //        `evaluation` int(11) DEFAULT NULL COMMENT '答复评价，1-满意、2-比较满意、3-不满意',

    private String id;// 1,
    private String pid;// 8,
    private String subTime;// null,
    private String content;// private  String dddddddddddddddddddddddprivate  String ,
    private String isAnony;// 1,
    private String questionTypeId;// 3,
    private String oid;// 1,
    private String answer;// private  String private  String ,
    private String ansTime;// null,
    private int aid;// 1,>=1 有回复
    private int evaluation;// 0,
    private String pname;// private  String 刘云山private  String ,
    private String questionTypeDesc;// private  String 社会保障private  String ,
    private String oname;// private  String 民政局private  String ,
    private String aname;// private  String 综合管理员private  String ,
    private String evaluationDesc;// private  String 未评估private  String ,
    private String isAnonyDesc;// private  String 匿名private  String ,
    private String subDate;// private  String 2017-11-30private  String ,
    private String ansDate;// private  String 2017-11-30private  String
    private String  type;


    public QuestionInfo(JSONObject obj)
    {
        this.id = obj.optString("id");
        this.oid = obj.optString("oid");
        this.pid = obj.optString("pid");
        this.subTime = obj.optString("subTime");
        this.content = obj.optString("content");
        this.isAnony = obj.optString("isAnony");
        this.questionTypeId = obj.optString("questionTypeId");
        this.answer = obj.optString("answer");
        this.ansTime = obj.optString("ansTime");
        this.evaluation = obj.optInt("evaluation");
        this.pname = obj.optString("pname");
        this.questionTypeDesc = obj.optString("questionTypeDesc");
        this.oname = obj.optString("oname");
        this.aname = obj.optString("aname");
        this.evaluationDesc = obj.optString("evaluationDesc");
        this.isAnonyDesc = obj.optString("isAnonyDesc");
        this.subDate = obj.optString("subDate");
        this.ansDate = obj.optString("ansDate");
        this.aid = obj.optInt("aid");
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

    public String getSubTime()
    {
        return subTime;
    }

    public void setSubTime(String subTime)
    {
        this.subTime = subTime;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getIsAnony()
    {
        return isAnony;
    }

    public void setIsAnony(String isAnony)
    {
        this.isAnony = isAnony;
    }

    public String getQuestionTypeId()
    {
        return questionTypeId;
    }

    public void setQuestionTypeId(String questionTypeId)
    {
        this.questionTypeId = questionTypeId;
    }

    public String getOid()
    {
        return oid;
    }

    public void setOid(String oid)
    {
        this.oid = oid;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getAnsTime()
    {
        return ansTime;
    }

    public void setAnsTime(String ansTime)
    {
        this.ansTime = ansTime;
    }

    public int getAid()
    {
        return aid;
    }

    public void setAid(int aid)
    {
        this.aid = aid;
    }


    public int getEvaluation()
    {
        return evaluation;
    }

    public void setEvaluation(int evaluation)
    {
        this.evaluation = evaluation;
    }

    public String getPname()
    {
        return pname;
    }

    public void setPname(String pname)
    {
        this.pname = pname;
    }

    public String getQuestionTypeDesc()
    {
        return questionTypeDesc;
    }

    public void setQuestionTypeDesc(String questionTypeDesc)
    {
        this.questionTypeDesc = questionTypeDesc;
    }

    public String getOname()
    {
        return oname;
    }

    public void setOname(String oname)
    {
        this.oname = oname;
    }

    public String getAname()
    {
        return aname;
    }

    public void setAname(String aname)
    {
        this.aname = aname;
    }

    public String getEvaluationDesc()
    {
        return evaluationDesc;
    }

    public void setEvaluationDesc(String evaluationDesc)
    {
        this.evaluationDesc = evaluationDesc;
    }

    public String getIsAnonyDesc()
    {
        return isAnonyDesc;
    }

    public void setIsAnonyDesc(String isAnonyDesc)
    {
        this.isAnonyDesc = isAnonyDesc;
    }

    public String getSubDate()
    {
        return subDate;
    }

    public void setSubDate(String subDate)
    {
        this.subDate = subDate;
    }

    public String getAnsDate()
    {
        return ansDate;
    }

    public void setAnsDate(String ansDate)
    {
        this.ansDate = ansDate;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
