package com.bangfu.utils;

import com.bangfu.BfApplication;
import com.bangfu.entity.ConsultTypeInfo;
import com.bangfu.entity.DeptInfo;
import com.bangfu.entity.EduPhaseTypeInfo;
import com.bangfu.entity.HealthyTypeInfo;
import com.bangfu.entity.OrgInfo;
import com.bangfu.entity.RelationTypeInfo;
import com.bangfu.entity.WorkTypeInfo;

import java.util.ArrayList;
import java.util.List;
/**
 * 作者：王先云 on 2017/12/1 11:13
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class TypeUtils
{

    public static ArrayList<String> getWorkTypeList(ArrayList<WorkTypeInfo> list)
    {
        ArrayList<String> mTypeList = new ArrayList<>();

        if (null != list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                mTypeList.add(list.get(i).getType_name());

            }
        }

        return mTypeList;

    }


    public static ArrayList<String> getHealthyTypeList(ArrayList<HealthyTypeInfo> list)
    {
        ArrayList<String> mTypeList = new ArrayList<>();

        if (null != list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                mTypeList.add(list.get(i).getType_name());

            }
        }

        return mTypeList;

    }


    public static ArrayList<String> getRelationTypeList(ArrayList<RelationTypeInfo> list)
    {
        ArrayList<String> mTypeList = new ArrayList<>();

        if (null != list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                mTypeList.add(list.get(i).getType_name());

            }
        }
        return mTypeList;

    }


    public static ArrayList<String> getDeptNameList(ArrayList<DeptInfo> list)
    {
        ArrayList<String> mTypeList = new ArrayList<>();

        if (null != list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                mTypeList.add(list.get(i).getDname());

            }
        }
        return mTypeList;

    }


    //咨询问题类型
    public static ArrayList<String> getConsultTypeList(ArrayList<ConsultTypeInfo> list)
    {
        ArrayList<String> mTypeList = new ArrayList<>();

        if (null != list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                mTypeList.add(list.get(i).getTypeName());

            }
        }
        return mTypeList;

    }


    public static ArrayList<String> getOrgNameList(ArrayList<OrgInfo> list)
    {
        ArrayList<String> mTypeList = new ArrayList<>();

        if (null != list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                mTypeList.add(list.get(i).getOname());

            }
        }
        return mTypeList;

    }



    public static String getConsultTypeName(String id)
    {
        String name = "其他";

        ArrayList<ConsultTypeInfo> list = BfApplication.getInstance().getConsultTypeInfoList();

        if (null != list&& null !=id)
        {
            for (int i = 0; i < list.size(); i++)
            {
                if (id.equals(list.get(i).getId()))
                {
                    name = list.get(i).getTypeName();
                }
            }
        }

        return name;
    }



    public  static  String  getOrgName(String id)
    {
        String name = "其他";

        ArrayList<OrgInfo> list = BfApplication.getInstance().getOrgInfoList();

        if (null != list && null !=id)
        {
            for (int i = 0; i < list.size(); i++)
            {
                if (id.equals(list.get(i).getOid()))
                {
                    name = list.get(i).getOname();
                }
            }
        }

        return name;
    }


    public  static ArrayList<String> getTypeNameList(List<String> list)
    {
        ArrayList<String>  mNameList = new ArrayList<>();

        if(null != list)
        {
            for (int i = 0; i <list.size() ; i++)
            {
                mNameList.add(list.get(i));
            }
        }
        return mNameList;
    }



    public static ArrayList<String> getEduPhaseTypeList(ArrayList<EduPhaseTypeInfo> list)
    {
        ArrayList<String> mTypeList = new ArrayList<>();

        if (null != list)
        {
            for (int i = 0; i < list.size(); i++)
            {
                mTypeList.add(list.get(i).getPhase());

            }
        }
        return mTypeList;

    }

}
