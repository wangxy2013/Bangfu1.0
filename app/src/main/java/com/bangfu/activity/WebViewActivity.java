package com.bangfu.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangfu.R;

/**
 * User: 王先云
 * Date: 2016-09-8 15:56
 * DESC: H5界面跳转
 */
public class WebViewActivity extends Activity
{
    public static final String EXTRA_URL   = "extra_url";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String IS_SETTITLE = "isSetTitle";
    private WebView   mWebView;
    private String    mUrl;
    private boolean   isSetTitle;
    private ImageView mBackIv;
    private TextView  mTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();
        initViewData();
        initEvent();

    }

    protected void initView()
    {
        mBackIv = (ImageView) findViewById(R.id.iv_back);
        mTitleTv = (TextView) findViewById(R.id.tv_title);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //mWebView.addJavascriptInterface(new JSService(), "object");
        mWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                if (mWebView.getContentHeight() != 0)
                {
                    // 网页显示完成
                }
            }

            @Override
            public void onLoadResource(WebView view, String url)
            {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                if (url != null && url.startsWith("appay"))
                {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress)
            {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title)
            {
                super.onReceivedTitle(view, title);

                if (!isSetTitle)
                    mTitleTv.setText(title);
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed)
            {
                super.onReceivedTouchIconUrl(view, url, precomposed);
                //SLog.debug("onReceivedTouchIconUrl:" + url);
            }
        });

        mWebView.setDownloadListener(new DownloadListener()
        {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
                try
                {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)));
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void initViewData()
    {
        mUrl = getIntent().getStringExtra(EXTRA_URL);
        isSetTitle = getIntent().getBooleanExtra(IS_SETTITLE, true);

        if (isSetTitle)
        {
            mTitleTv.setText(getIntent().getStringExtra(EXTRA_TITLE));
        }
        if (!TextUtils.isEmpty(mUrl))
        {
            mWebView.loadUrl(mUrl);
        }
    }


    private void initEvent()
    {
        mBackIv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mWebView.canGoBack())
                {
                    mWebView.goBack();
                }
                else
                {
                    finish();
                }
            }
        });
    }

    public class JSService
    {
//        @JavascriptInterface
//        public void onPayDone()
//        {
//            //            startActivity(new Intent(WebViewActivity.this, MainActivityEx.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//            //            ContextUtil.toast_debug("onPayDone");
//        }
//
//        public void onPayFail()
//        {
//            //            ContextUtil.toast_debug("onPayFail");
//        }
//
//        @JavascriptInterface
//        public void onClosed()
//        {
//            finish();
//        }
//
//
//        @JavascriptInterface
//        public void onSubscriptionSuccess(String groupId, String roomId)
//        {
//
//        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {

            if (mWebView.canGoBack())
            {
                mWebView.goBack();
            }
            else
            {
                return super.onKeyDown(keyCode, event);
            }

            return false;
        }
        else
        {
            return super.onKeyDown(keyCode, event);
        }

    }
}
