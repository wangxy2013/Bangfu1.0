package com.bangfu.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bangfu.R;
import com.bangfu.entity.NewsInfo;
import com.bangfu.widget.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王先云 on 2017/12/1 22:08
 * 邮箱：wangxianyun1@163.com
 * 描述：一句话简单描述
 */
public class NewsDetailActivity extends BaseActivity
{


    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.tv_title)
    TextView       tvTitle;
    @BindView(R.id.tv_news_title)
    TextView       tvNewsTitle;
    @BindView(R.id.mWebView)
    WebView        mWebView;
    @BindView(R.id.tv_html)
    HtmlTextView   tvHtml;


    @Override
    protected void initData()
    {

    }

    @Override
    protected void initViews(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_news_detail);
        setStatusColor(ContextCompat.getColor(this, R.color.blue));
    }

    @Override
    protected void initEvent()
    {
        rlBack.setOnClickListener(this);
    }

    @Override
    protected void initViewData()
    {


        String type = getIntent().getStringExtra("TYPE");
        NewsInfo mNewsInfo = (NewsInfo) getIntent().getSerializableExtra("NewsInfo");
        if ("1".equals(type))
        {
            tvTitle.setText("农耕养殖技术");
        }
        else if ("2".equals(type))
        {
            tvTitle.setText("就业创业信息");
        }
        else if ("3".equals(type))
        {
            tvTitle.setText("惠农政策宣传");
        }
        else if ("4".equals(type))
        {
            tvTitle.setText("新闻时事报道");
        }
        else if ("5".equals(type))
        {
            tvTitle.setText("扶贫政策");
        }
        else if ("6".equals(type))
        {
            tvTitle.setText("信息宣传");
        }
        if (null != mNewsInfo)
        {
            tvNewsTitle.setText(mNewsInfo.getTitle());
            // tvContent.setText(Html.fromHtml(mNewsInfo.getContent()));
            // mWebView.loadData(mNewsInfo.getContent(), "text/html; charset=UTF-8", null);
            tvHtml.displayWithHtml(mNewsInfo.getContent());
        }
    }


    @Override
    public void onClick(View v)
    {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
