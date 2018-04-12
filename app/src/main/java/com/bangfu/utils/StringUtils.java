package com.bangfu.utils;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
{

    /**
     * 判断是否正整数
     *
     * @param string
     * @return
     */
    public static boolean isNumeric(String string)
    {
        boolean flag = false;
        Pattern p = Pattern.compile("[1-9]{1}[0-9]{0,1}");
        Matcher m = p.matcher(string);
        flag = m.matches();

        return flag;
    }

    public static boolean isAllNumeric(String str)
    {
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为�?
     *
     * @param str
     * @return
     */
    public static boolean stringIsEmpty(String str)
    {
        if ("".equals(str) || null == str || "null".equals(str))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String getFormTime(long time)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        return df.format(time);// new Date()为获取当前系统时间
    }

    public static String getFormTime1(long time)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
        return df.format(time);// new Date()为获取当前系统时间
    }

    public static String getDateToString(Date date)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        return df.format(date);// new Date()为获取当前系统时间
    }

    SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
    Date             date = new Date();
    String           str  = sdf.format(date);

    public static String formatTime(long ms)
    {
        if (ms == 0)
        {
            return "00:00:00";
        }
        // 将毫秒数换算成x天x时x分x秒x毫秒
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day;
        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        return strHour + ":" + strMinute + ":" + strSecond + " ";
    }

    public static String getCurrentTime(String format)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime()
    {
        return getCurrentTime("yyyy-MM-dd");
    }

    public static String getTimestamp()
    {
        return getCurrentTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentTime1()
    {
        return getCurrentTime("yyyy-MM");
    }

    public static int getIndex(String str, String[] codeArr)
    {
        int mIndex = 0;

        if (!stringIsEmpty(str))
        {
            for (int i = 0; i < codeArr.length; i++)
            {
                if (str.equals(codeArr[i]))
                {
                    mIndex = i;
                }
            }
        }

        return mIndex;
    }

    /**
     * 〈比较时间〉
     *
     * @param DATE1
     * @param DATE2
     * @return int
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @since [起始版本]
     */
    public static int compareDate(String DATE1, String DATE2)
    {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try
        {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime())
            {
                System.out.println("dt1 在dt2前");
                return 1;
            }
            else if (dt1.getTime() < dt2.getTime())
            {
                System.out.println("dt1在dt2后");
                return -1;
            }
            else
            {
                return 0;
            }
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 〈判断邮箱格式〉
     *
     * @param email
     * @return boolean
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @since [起始版本]
     */
    public static boolean checkEmail(String email)
    {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 〈手机号码正则〉
     *
     * @param mobiles
     * @return boolean
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @since [起始版本]
     */
    public static boolean isMobileNO(String mobiles)
    {
        Pattern p = Pattern.compile("^((13[0-9])|(17[0-9])|(15[^4,\\D])|(176)|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    // 前一天
    public static String getBeforeDay(String time)
    {
        String day = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(time);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            date1 = calendar.getTime();
            day = getDateToString(date1);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return day;
    }

    // 后一天
    public static String getNextDay(String time)
    {
        String day = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(time);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            date1 = calendar.getTime();
            day = getDateToString(date1);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return day;
    }


    public static String getFormatTime(String time)
    {

        String day = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(time);
            day = getDateToString(date1);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return day;
    }

    public static String addMonth(String datetime, int month)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(datetime);
        Date date = new Date(lt);
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, month);
        cl.add(Calendar.DAY_OF_MONTH, -1);

        date = cl.getTime();
        return yearToMonth(sdf.format(date));
    }

    public static String Day(String datetime)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(datetime);
        Date date = new Date(lt);
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        date = cl.getTime();
        return sdf.format(date);
    }

    public static String addDay(String datetime, int day)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(datetime);
        Date date = new Date(lt);
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DAY_OF_MONTH, day);

        date = cl.getTime();
        return sdf.format(date);
    }

    /**
     * 〈获取今天时间+30天*月份〉
     *
     * @return String
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @since [起始版本]
     */
    public static String getTodayTime(int day)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();// 取时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DAY_OF_MONTH, day - 1);
        date = calendar.getTime(); // 这个时间就是日期增加过后的结果
        String dateString = sdf.format(date);

        return dateString;
    }


    public static String stampToDate(String s)
    {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return yearToMonth(res);
    }

    public static String yearToMonth(String time)
    {
        String[] str = time.split("-");
        return str[0] + "年" + str[1] + "月" + str[2] + "日  ";
    }

    public static String getUUid()
    {
        return UUID.randomUUID().toString();

    }

    /**
     * 〈获取明天时间〉
     *
     * @return String
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @since [起始版本]
     */
    public static String getTomorrowTime()
    {
        Date date = new Date();// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }


    public static float getRating(String starLevel)
    {
        float mRating = 3;

        if ("1".equals(starLevel))
        {
            mRating = 1;
        }
        else if ("2".equals(starLevel))
        {
            mRating = (float) 1.5;
        }
        else if ("3".equals(starLevel))
        {
            mRating = (float) 2;
        }
        else if ("4".equals(starLevel))
        {
            mRating = (float) 2.5;
        }
        else if ("5".equals(starLevel))
        {
            mRating = (float) 3;
        }
        else if ("6".equals(starLevel))
        {
            mRating = (float) 3.5;
        }
        else if ("7".equals(starLevel))
        {
            mRating = (float) 4;
        }
        else if ("8".equals(starLevel))
        {
            mRating = (float) 4.5;
        }
        else if ("9".equals(starLevel))
        {
            mRating = (float) 5;
        }

        return mRating;
    }

    public static String formatString(String str)
    {
        if (stringIsEmpty(str))
        {
            return "";
        }

        return str;
    }


    // 特殊字符过滤
    public static String stringFilter(String str)
    {
        String regEx = "[`~!@#$^&*()=|{}':;',\"\\[\\].<>~！@#￥……&*（）&;—|{}【】《》‘；：”“'。，、？]"; // 要过滤掉的字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    private static long lastClickTime;

    public static boolean isFastDoubleClick()
    {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 800)
        {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 〈身份证中间显示****〉
     *
     * @param str
     * @return String
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @since [起始版本]
     */
    public static String certificateFormat(String str)
    {
        String a = "";
        if (!stringIsEmpty(str) && str.length() > 8)
        {
            a = str.substring(0, 4) + "****" + str.substring(str.length() - 4, str.length());
        }

        return a;
    }

    /**
     * 〈手机号码中间显示****〉
     *
     * @param str
     * @return String
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @since [起始版本]
     */
    public static String phoneFormat(String str)
    {
        String a = "";
        if (!stringIsEmpty(str) && str.length() > 8)
        {
            a = str.substring(0, 3) + "****" + str.substring(str.length() - 4, str.length());
        }

        return a;
    }

    /**
     * 用户名正则2-16位汉字数字字母下划线
     *
     * @param name
     * @return
     */
    public static boolean checkUserName(String name)
    {
        String regEX = "^([\u4e00-\u9fa5]|[A-Za-z0-9._@-]){2,16}$";
        Pattern p = Pattern.compile(regEX);
        Matcher m = p.matcher(name);
        return m.matches();

    }


    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate)
    {
        if (stringIsEmpty(strDate))
        {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(java.util.Date dateDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr(java.util.Date dateDate)
    {
        if (null == dateDate)
        {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    public static String longToShort(String time)
    {

        SimpleDateFormat sdf = null;
        String dateTime = "";
        try
        {
            sdf = new SimpleDateFormat("HH:mm:ss");
            String strDatatime = time + "000";
            Long lDatatime = Long.parseLong(strDatatime);
            dateTime = sdf.format(lDatatime);
        } catch (Exception e)
        {
            //TODO 处理异常
            e.printStackTrace();
        }
        return dateTime;

    }

    public static String longToShort1(String time)
    {

        SimpleDateFormat sdf = null;
        String dateTime = "";
        try
        {
            sdf = new SimpleDateFormat("yyyyMMdd");
            String strDatatime = time + "000";
            Long lDatatime = Long.parseLong(strDatatime);
            dateTime = sdf.format(lDatatime);
        } catch (Exception e)
        {
            //TODO 处理异常
            e.printStackTrace();
        }
        return dateTime;

    }

    public static String longToShort2(String time)
    {

        SimpleDateFormat sdf = null;
        String dateTime = "";
        try
        {
            sdf = new SimpleDateFormat("MM/dd");
            String strDatatime = time + "000";
            Long lDatatime = Long.parseLong(strDatatime);
            dateTime = sdf.format(lDatatime);
        } catch (Exception e)
        {
            //TODO 处理异常
            e.printStackTrace();
        }
        return dateTime;

    }


    public static String longToShort3(String time)
    {

        SimpleDateFormat sdf = null;
        String dateTime = "";
        try
        {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String strDatatime = time + "000";
            Long lDatatime = Long.parseLong(strDatatime);
            dateTime = sdf.format(lDatatime);
        } catch (Exception e)
        {
            //TODO 处理异常
            e.printStackTrace();
        }
        return dateTime;

    }

    private static String code = "abcdefghijklmnopqrstuvwxyz";

    public static String getRandomString(int length)
    {
        StringBuffer sb = new StringBuffer();
        int len = code.length();
        for (int i = 0; i < length; i++)
        {
            sb.append(code.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    private static int getRandom(int count)
    {

        return (int) Math.round(Math.random() * (count));

    }


    //获取聊天类型
    public static String getChatType(String json)
    {
        String type = null;
        try
        {
            JSONObject obj = new JSONObject(json);

            type = obj.optString("type");


        } catch (Exception e)
        {
            e.printStackTrace();
            return type;
        }


        return type;
    }

    //获取删除聊天消息
    public static String getDelMsg(String json)
    {
        String type = null;
        try
        {
            JSONObject obj = new JSONObject(json);

            type = obj.optString("msg_ymdid");


        } catch (Exception e)
        {
            e.printStackTrace();
            return type;
        }


        return type;
    }

    //获取圈子是否是工会圈子
    public static String getGild(String json)
    {
        String type = null;
        try
        {
            JSONObject obj = new JSONObject(json);

            type = obj.optString("is_gild");


        } catch (Exception e)
        {
            e.printStackTrace();
            return type;
        }


        return type;
    }


    /**
     * 去掉url中的路径，留下请求参数部分
     *
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL)
    {
        String strAllParam = null;
        String[] arrSplit = null;
        strURL = strURL.trim().toLowerCase();
        arrSplit = strURL.split("[?]");
        if (strURL.length() > 1)
        {
            if (arrSplit.length > 1)
            {
                if (arrSplit[1] != null)
                {
                    strAllParam = arrSplit[1];
                }
            }
        }
        return strAllParam;
    }


    //URL正则
    public static boolean checkUrl(String urlString)
    {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(urlString);
        return matcher.matches();
    }

    /**
     * 解析出url参数中的键值对 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     *
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL)
    {
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit = null;
        String strUrlParam = TruncateUrlPage(URL);
        if (strUrlParam == null)
        {
            return mapRequest;
        }
        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit)
        {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            // 解析出键值
            if (arrSplitEqual.length > 1)
            {
                // 正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
            }
            else
            {
                if (arrSplitEqual[0] != "")
                {
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }


    public static boolean isNumber(String str)
    {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后一位的数字的正则表达式
        java.util.regex.Matcher match = pattern.matcher(str);
        if (match.matches() == false)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public static String   supplement(String  date)
    {

        if(date.length()==1)
        {
            date = "0"+date;
        }

        return  date;
    }

    public static String getWeek(String sdate)
    {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static Date strToDate(String strDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
}
