package com.bangfu.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.BfApplication;
import com.bangfu.R;
import com.bangfu.entity.LunarInfo;
import com.bangfu.entity.WeatherInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.LunarInfoHandler;
import com.bangfu.json.WeatherInfoHandler;
import com.bangfu.utils.APPUtils;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.DialogUtils;
import com.bangfu.utils.StringUtils;
import com.bangfu.utils.Urls;
import com.bangfu.utils.VersionManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/11/16 09:36
 * 邮箱：wangxianyun1@163.com
 * 描述：首页->未登录
 */
public class IndexActivity extends BaseActivity implements IRequestListener
{
    @BindView(R.id.tv_date)
    TextView       tvDate;
    @BindView(R.id.tv_temperature)
    TextView       tvTemperature;
    @BindView(R.id.tv_city)
    TextView       tvCity;
    @BindView(R.id.ll_temperature)
    LinearLayout   llTemperature;
    @BindView(R.id.iv_ngjs)
    ImageView      ivNgjs;
    @BindView(R.id.rl_ngjs)
    RelativeLayout rlNgjs;//农耕技术
    @BindView(R.id.iv_jyxx)
    ImageView      ivJyxx;
    @BindView(R.id.rl_jyxx)
    RelativeLayout rlJyxx;
    @BindView(R.id.iv_hnzc)
    ImageView      ivHnzc;
    @BindView(R.id.rl_hnzc)
    RelativeLayout rlHnzc;
    @BindView(R.id.iv_news)
    ImageView      ivNews;
    @BindView(R.id.rl_news)
    RelativeLayout rlNews;
    @BindView(R.id.tv_help)
    TextView       tvHelp;
    @BindView(R.id.ll_help)
    LinearLayout   llHelp;
    @BindView(R.id.btn_login)
    Button         btnLogin;
    @BindView(R.id.tv_today)
    TextView       tvToday;
    @BindView(R.id.tv_week)
    TextView       tvWeek;
    @BindView(R.id.tv_lunar)
    TextView       tvLunar;
    @BindView(R.id.tv_day)
    TextView       tvDay;
    @BindView(R.id.tv_cyclicalYear)
    TextView       tvCyclicalYear;
    @BindView(R.id.tv_cyclicalMonth)
    TextView       tvCyclicalMonth;
    @BindView(R.id.ll_lunar)
    LinearLayout   llLunar;
    //    @BindView(R.id.tv_suit)
    //    TextView       tvSuit;
    //    @BindView(R.id.taboo)
    //    TextView       tvTaboo;


    private              String suit                      = "";
    private              String taboo                     = "";
    private static final int    REQUEST_SUCCESS           = 0x01;
    public static final  int    REQUEST_FAIL              = 0x02;
    private static final int    GET_LUNAR_REQUEST_SUCCESS = 0x03;
    private static final String GET_WEATHER               = "GET_WEATHER";
    private static final String GET_LUNAR                 = "GET_LUNAR";


    private BaseHandler mHandler = new BaseHandler(this)
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case REQUEST_SUCCESS:
                    WeatherInfoHandler mWeatherInfoHandler = (WeatherInfoHandler) msg.obj;
                    WeatherInfo mWeatherInfo = mWeatherInfoHandler.getWeatherInfo();

                    if (null != mWeatherInfo)
                    {
                        tvDate.setText(mWeatherInfo.getDate());
                        tvTemperature.setText(mWeatherInfo.getTemp() + "°");
                        tvCity.setText("宿迁市");
                    }
                    getLunar();
                    break;
                case REQUEST_FAIL:
                    break;

                case GET_LUNAR_REQUEST_SUCCESS:
                    LunarInfoHandler mLunarInfoHandler = (LunarInfoHandler) msg.obj;
                    LunarInfo mLunarInfo = mLunarInfoHandler.getLunarInfo();

                    if (null != mLunarInfoHandler)
                    {
                        String mDate = mLunarInfo.getYear() + "-" + StringUtils.supplement(mLunarInfo.getMonth()) + "-" + StringUtils.supplement(mLunarInfo
                                .getDay());
                        tvToday.setText(mDate);

                        tvWeek.setText(StringUtils.getWeek(mDate));

                        tvLunar.setText(mLunarInfo.getCnmonth() + "月" + mLunarInfo.getCnday());
                        tvDay.setText(mLunarInfo.getDay());
                        tvCyclicalYear.setText(mLunarInfo.getCyclicalYear()+"【" +mLunarInfo.getAnimal()+"年】");
                        tvCyclicalMonth.setText(mLunarInfo.getCyclicalMonth()+"月  " + mLunarInfo.getCyclicalDay()+"日");


                        suit = mLunarInfo.getSuit();
                        taboo = mLunarInfo.getTaboo();

                        if (!StringUtils.stringIsEmpty(suit))
                        {
                            suit = "<html>" + suit.replace(",", "<br/>") + "</html>";
                        }


                        if (!StringUtils.stringIsEmpty(taboo))
                        {
                            taboo = "<html>" + taboo.replace(",", "<br/>") + "</html>";
                        }


                    }
                    break;

            }
        }
    };


    @Override
    protected void initData()
    {
        Map<String, String> valuePairs = new HashMap<>();
        DataRequest.instance().request(IndexActivity.this, Urls.getWeatherUrl(), this, HttpRequest.POST, GET_WEATHER, valuePairs,
                new WeatherInfoHandler());

    }


    private void getLunar()
    {
        Map<String, String> valuePairs1 = new HashMap<>();
        DataRequest.instance().request(IndexActivity.this, Urls.getLunarUrl(), this, HttpRequest.GET, GET_LUNAR, valuePairs1,
                new LunarInfoHandler());
    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_index);
        setTranslucentStatus();
    }

    @Override
    protected void initEvent()
    {
        rlNgjs.setOnClickListener(this);
        rlHnzc.setOnClickListener(this);
        rlJyxx.setOnClickListener(this);
        rlNews.setOnClickListener(this);
        llHelp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        llLunar.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {

        tvHelp.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        tvHelp.getPaint().setAntiAlias(true);//抗锯齿

        //初始化第三方APK
        if (APPUtils.fileIsExists(ConstantUtil.THIRD_PATH + ConstantUtil.THIRD_APKNAME))
        {
            APPUtils.copyAssetsFile(IndexActivity.this, ConstantUtil.THIRD_APKNAME, ConstantUtil.THIRD_PATH);
        }
        new VersionManager(this).init();

    }


    @Override
    protected void onResume()
    {
        super.onResume();
        if (BfApplication.getInstance().isLogin())
        {
            btnLogin.setText("快速进入");
        }
        else
        {
            btnLogin.setText("登录");
        }
    }

    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.btn_login:


                if (!BfApplication.getInstance().isLogin())
                {
                    startActivity(new Intent(IndexActivity.this, LoginActivity.class));
                }
                else
                {

                    if ("0".equals(ConfigManager.instance().getHolder()))
                    {
                        startActivity(new Intent(IndexActivity.this, WorkerActivity.class));
                    }
                    else
                    {
                        startActivity(new Intent(IndexActivity.this, FarmerActivity.class));
                    }
                }
                break;

            case R.id.rl_ngjs:
                startActivity(new Intent(IndexActivity.this, ProgramPolicyActivity.class).putExtra("TYPE", "1"));
                break;

            case R.id.rl_hnzc:
                startActivity(new Intent(IndexActivity.this, ProgramPolicyActivity.class).putExtra("TYPE", "3"));
                break;

            case R.id.rl_jyxx:
                startActivity(new Intent(IndexActivity.this, ProgramPolicyActivity.class).putExtra("TYPE", "2"));
                break;
            case R.id.rl_news:
                startActivity(new Intent(IndexActivity.this, ProgramPolicyActivity.class).putExtra("TYPE", "4"));
                break;

            case R.id.ll_lunar:
                DialogUtils.showLunarDialog(IndexActivity.this,suit,taboo);
                break;

        }
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (GET_WEATHER.equals(action))
        {
            if (ConstantUtil.RESULT_SUCCESS.equals(resultCode))
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_SUCCESS, obj));
            }

            else
            {
                mHandler.sendMessage(mHandler.obtainMessage(REQUEST_FAIL, resultMsg));
            }
        }

        else if (GET_LUNAR.equals(action))
        {

            mHandler.sendMessage(mHandler.obtainMessage(GET_LUNAR_REQUEST_SUCCESS, obj));
        }
    }


}
