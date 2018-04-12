package com.bangfu.utils;

/**
 * URL管理类
 *
 * @date 2014年9月16日 上午9:48:03
 * @since[产品/模块版本]
 * @seejlj
 */
public class Urls
{
    public static final String HTTP_IP = "http://112.35.25.62:8085";

    public static final String BASE_URL = HTTP_IP + "/fp/";


    //用戶登录
    public static String getLoginUrl()
    {
        return BASE_URL + "clientLogin.do";
    }

    //查询某一农户户主及其全部成员信息（包括农户全部成员信息）
    public static String getFamilyMemberInfoUrl()
    {
        return BASE_URL + "cust/familyMemberInfo.do";
    }


    //查询户主信息列表
    public static String getHolderInfoListUrl()
    {
        return BASE_URL + "cust/holderInfo.do";
    }

    //查询户主信息列表
    public static String getSearchHolderInfoUrl()
    {
        return BASE_URL + "cust/searchHolderInfoForApp.do";
    }

    //查询發佈信息
    public static String getPublishInfoListUrl()
    {
        return BASE_URL + "pub/getPublishInfo.do";
    }


    //机构ID列表
    public static String getOrgListUrl()
    {
        return BASE_URL + "cust/getOrgForApp.do";
    }

    //健康状况类型
    public static String getAllHealthyTypeUrl()
    {
        return BASE_URL + "cust/getAllHealthyTypeForApp.do";
    }

    //咨询问题类型
    public static String getConsultTypeUrl()
    {
        return BASE_URL + "consult/getConsultTypeForApp.do";
    }

    //咨询问题类型
    public static String getOrgForApp1Url()
    {
        return BASE_URL + "cust/getOrgForApp1.do";
    }



    //行政区划列表
    public static String getDepListUrl()
    {
        return BASE_URL + "dept/deptInfo.do";
    }


    //、农户关系类型
    public static String getAllRelationTypeUrl()
    {
        return BASE_URL + "cust/getAllRelationTypeForApp.do";
    }

    //就业状况列表
    public static String getAllWorkTypeUrl()
    {
        return BASE_URL + "cust/getAllWorkTypeForApp.do";
    }

    //教育分段列表
    public static String getEduPhaseTypeUrl()
    {
        return BASE_URL + "edu/getEduPhaseTypeForApp.do";
    }

    //2.6咨询信息
    public static String getConsultInfoUrl()
    {
        return BASE_URL + "consult/getConsultInfo.do";
    }

    //2.6咨询信息
    public static String getSaveConsultInfoUrl()
    {
        return BASE_URL + "consult/saveConsultInfo.do";
    }

    //新增农户信息
    public static String getAddCustomerUrl()
    {
        return BASE_URL + "cust/addCustomer.do";
    }

    //根据pid获得单条个人信息
    public static String getCustomerInfoByPidUrl()
    {
        return BASE_URL + "cust/getCustomerInfoByPid.do";
    }



    //获取家庭成员信息
    public static String getFamilyMemberListUrl()
    {
        return BASE_URL + "cust/familyMemberInfo.do";
    }

    //获取pid行政区划下的全部大病救助信息列表
    public static String getHtyInfoListUrl()
    {
        return BASE_URL + "hty/htyInfo.do";
    }


    //获取pid行政区划下的全部大病救助信息列表
    public static String getSearchHtyUrl()
    {
        return BASE_URL + "hty/searchInfo.do";
    }



    //增加大病帮扶信息
    public static String getAddHtyAssistInfoUrl()
    {
        return BASE_URL + "hty/addHtyAssistInfo.do";
    }

    //根据pid的行政区划获取该行政区划下的全部教育救助信息
    public static String getEduInfoListUrl()
    {
        return BASE_URL + "edu/eduInfo.do";
    }

    //根据pid的行政区划获取该行政区划下的全部教育救助信息
    public static String getSearchEduUrl()
    {
        return BASE_URL + "edu/searchInfo.do";
    }

    //修改教育帮扶信息
    public static String getAddEduAssistInfoUrl()
    {
        return BASE_URL + "edu/addEduAssistInfo.do";
    }


    //新增咨询信息
    public static String getAddConsultInfoUrl()
    {
        return BASE_URL + "consult/addConsultInfo.do";
    }


    //根据指定的pid获得该pid发布的咨询信息
    public static String getConsultInfoByPidUrl()
    {
        return BASE_URL + "consult/getConsultInfoByPid.do";
    }

    //满意度提交
    public static String getEvaluationUrl()
    {
        return BASE_URL + "consult/Evaluation.do";
    }

    //获取天气
    public static String getWeatherUrl()
    {
        return "https://way.jd.com/jisuapi/weather?city=宿迁&cityid=&citycode=&appkey=11a66cf37e494499829ffdc2fd63ac7c";
    }


    //获取天气
    public static String geVersionUrl()
    {
        return BASE_URL + "getAppVersion.do";
    }

    //获取日历信息
    public static String getLunarUrl()
    {
        return "https://www.sojson.com/open/api/lunar/json.shtml";
    }


    //获取城镇列表
    public static String getDeptListUrl()
    {
        return BASE_URL + "dept/getDeptList.do";
    }

    //获取城镇列表
    public static String getDeptVillaListUrl()
    {
        return BASE_URL + "dept/getDeptVillaList.do";
    }


}
