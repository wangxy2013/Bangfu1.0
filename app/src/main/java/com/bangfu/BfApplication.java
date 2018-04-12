package com.bangfu;

import android.app.Application;
import android.content.Context;

import com.bangfu.activity.FamilyListActivity;
import com.bangfu.entity.ConsultTypeInfo;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.EduPhaseTypeInfo;
import com.bangfu.entity.HealthyTypeInfo;
import com.bangfu.entity.NewsInfo;
import com.bangfu.entity.OrgInfo;
import com.bangfu.entity.RelationTypeInfo;
import com.bangfu.entity.WorkTypeInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.ConsultTypeInfoListHandler;
import com.bangfu.json.DeptInfoListHandler;
import com.bangfu.json.EduPhaseTypeInfoListHandler;
import com.bangfu.json.HealthyTypeListHandler;
import com.bangfu.json.OrgInfoListHandler;
import com.bangfu.json.RelationTypefoListHandler;
import com.bangfu.json.WorkTypeInfoListHandler;
import com.bangfu.utils.APPUtils;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.StringUtils;
import com.bangfu.utils.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：王先云 on 2016/8/5 14:46
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class BfApplication extends Application implements IRequestListener
{

    private static BfApplication instance;

    public static BfApplication getInstance() {return instance;}

    private ArrayList<WorkTypeInfo>     mWorkTypeInfoList     = new ArrayList<>();
    private ArrayList<RelationTypeInfo> mRelationTypeInfoList = new ArrayList<>();
    private ArrayList<OrgInfo>          mOrgInfoList          = new ArrayList<>();
    private ArrayList<NewsInfo>         mNewsInfoList         = new ArrayList<>();
    private ArrayList<HealthyTypeInfo>  mHealthyTypeInfoList  = new ArrayList<>();
    private ArrayList<EduPhaseTypeInfo> mEduPhaseTypeInfoList = new ArrayList<>();
    private ArrayList<DeptInfo>         mDeptInfoList         = new ArrayList<>();
    private ArrayList<ConsultTypeInfo>  mConsultTypeInfoList  = new ArrayList<>();

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        APPUtils.configImageLoader(getApplicationContext());
        ConfigManager.instance().init(this);

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);


        initOrgList();
        initHealthyTypeList();
        initConsultTypeList();
        initDeptInfoList();
        initRelationTypeList();
        initWorkTypeList();
        initEduPhaseTypeUrl();
    }


    private void initOrgList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(this, Urls.getOrgListUrl(), this, HttpRequest.GET, "GET_ORG", valuePairs,
                new OrgInfoListHandler());
    }

    private void initHealthyTypeList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(this, Urls.getAllHealthyTypeUrl(), this, HttpRequest.GET, "GET_HEALTHY_TYPE", valuePairs,
                new HealthyTypeListHandler());
    }

    private void initConsultTypeList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(this, Urls.getConsultTypeUrl(), this, HttpRequest.GET, "GET_CONSULT_TYPE", valuePairs,
                new ConsultTypeInfoListHandler());
    }

    private void initDeptInfoList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(this, Urls.getDepListUrl(), this, HttpRequest.GET, "GET_DEPT", valuePairs,
                new DeptInfoListHandler());
    }

    private void initRelationTypeList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(this, Urls.getAllRelationTypeUrl(), this, HttpRequest.GET, "GET_RELATION_TYPE", valuePairs,
                new RelationTypefoListHandler());
    }


    private void initWorkTypeList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(this, Urls.getAllWorkTypeUrl(), this, HttpRequest.GET, "GET_WORK_TYPE", valuePairs,
                new WorkTypeInfoListHandler());
    }


    private void initEduPhaseTypeUrl()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(this, Urls.getEduPhaseTypeUrl(), this, HttpRequest.GET, "GET_EDUPHASE_TYPE", valuePairs,
                new EduPhaseTypeInfoListHandler());
    }


    public boolean isLogin()
    {
        if (StringUtils.stringIsEmpty(ConfigManager.instance().getUserID()))
        {
            return false;
        }
        else
        {
            return true;
        }

    }


    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {

    }


    public ArrayList<WorkTypeInfo> getWorkTypeInfoList()
    {
        return mWorkTypeInfoList;
    }

    public void setWorkTypeInfoList(ArrayList<WorkTypeInfo> mWorkTypeInfoList)
    {
        this.mWorkTypeInfoList = mWorkTypeInfoList;
    }

    public ArrayList<RelationTypeInfo> getRelationTypeInfoList()
    {
        return mRelationTypeInfoList;
    }

    public void setRelationTypeInfoList(ArrayList<RelationTypeInfo> mRelationTypeInfoList)
    {
        this.mRelationTypeInfoList = mRelationTypeInfoList;
    }

    public ArrayList<OrgInfo> getOrgInfoList()
    {
        return mOrgInfoList;
    }

    public void setOrgInfoList(ArrayList<OrgInfo> mOrgInfoList)
    {
        this.mOrgInfoList = mOrgInfoList;
    }

    public ArrayList<NewsInfo> getNewsInfoList()
    {
        return mNewsInfoList;
    }

    public void setNewsInfoList(ArrayList<NewsInfo> mNewsInfoList)
    {
        this.mNewsInfoList = mNewsInfoList;
    }

    public ArrayList<HealthyTypeInfo> getHealthyTypeInfoList()
    {
        return mHealthyTypeInfoList;
    }

    public void setHealthyTypeInfoList(ArrayList<HealthyTypeInfo> mHealthyTypeInfoList)
    {
        this.mHealthyTypeInfoList = mHealthyTypeInfoList;
    }

    public ArrayList<EduPhaseTypeInfo> getEduPhaseTypeInfoList()
    {
        return mEduPhaseTypeInfoList;
    }

    public void setEduPhaseTypeInfoList(ArrayList<EduPhaseTypeInfo> mEduPhaseTypeInfoList)
    {
        this.mEduPhaseTypeInfoList = mEduPhaseTypeInfoList;
    }

    public ArrayList<DeptInfo> getDeptInfoList()
    {
        return mDeptInfoList;
    }

    public void setDeptInfoList(ArrayList<DeptInfo> mDeptInfoList)
    {
        this.mDeptInfoList = mDeptInfoList;
    }

    public ArrayList<ConsultTypeInfo> getConsultTypeInfoList()
    {
        return mConsultTypeInfoList;
    }

    public void setConsultTypeInfoList(ArrayList<ConsultTypeInfo> mConsultTypeInfoList)
    {
        this.mConsultTypeInfoList = mConsultTypeInfoList;
    }
}
