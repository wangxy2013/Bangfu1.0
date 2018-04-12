package com.bangfu.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bangfu.R;
import com.bangfu.activity.AnswerActivity;
import com.bangfu.activity.BaseHandler;
import com.bangfu.activity.EvaluationActivity;
import com.bangfu.activity.FamilyListActivity;
import com.bangfu.activity.MyQuestionActivity;
import com.bangfu.adapter.MyQuestionAdapter;
import com.bangfu.adapter.SpecialAssistaceAdapter;
import com.bangfu.entity.QuestionInfo;
import com.bangfu.http.DataRequest;
import com.bangfu.http.HttpRequest;
import com.bangfu.http.IRequestListener;
import com.bangfu.json.HolderListHandler;
import com.bangfu.json.QuestionListHandler;
import com.bangfu.listener.MyItemClickListener;
import com.bangfu.utils.ConfigManager;
import com.bangfu.utils.ConstantUtil;
import com.bangfu.utils.ToastUtil;
import com.bangfu.utils.Urls;
import com.bangfu.widget.EmptyDecoration;
import com.bangfu.widget.list.refresh.PullToRefreshBase;
import com.bangfu.widget.list.refresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：王先云 on 2017/11/21 16:45
 * 邮箱：wangxianyun1@163.com
 * 描述：特别援助->待处理
 */
public class AssistanceFragment1 extends BaseFragment implements PullToRefreshBase.OnRefreshListener<RecyclerView>, IRequestListener
{


    @BindView(R.id.pullToRefreshRecyclerView)
    PullToRefreshRecyclerView mPullToRefreshRecyclerView;

    private RecyclerView mRecyclerView;

    private int mRefreshStatus;

    private EmptyDecoration emptyDecoration;

    private List<QuestionInfo> mQuestionInfoList = new ArrayList<>();

    private SpecialAssistaceAdapter mSpecialAssistaceAdapter;
    private View rootView = null;
    private Unbinder unbinder;

    private String mType;

    private int page = 1;

    private static final int    REQUEST_SUCCESS = 0x01;
    public static final  int    REQUEST_FAIL    = 0x02;
    private static final String GET_LIST        = "GET_LIST";

    private BaseHandler mHandler = new BaseHandler(getActivity())
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            switch (msg.what)
            {

                case REQUEST_SUCCESS:

                    QuestionListHandler mQuestionListHandler = (QuestionListHandler) msg.obj;
                    mQuestionInfoList.addAll(mQuestionListHandler.getQuestionInfoList());


                    for (int i = 0; i < mQuestionInfoList.size(); i++)
                    {
                        mQuestionInfoList.get(i).setType(mType);
                    }
                    mSpecialAssistaceAdapter.notifyDataSetChanged();

                    break;

                case REQUEST_FAIL:
                    break;


            }
        }
    };


    public static AssistanceFragment1 newInstance(String type)
    {
        Bundle args = new Bundle();
        args.putString("type", type);
        AssistanceFragment1 fragment = new AssistanceFragment1();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        if (rootView == null)
        {
            rootView = inflater.inflate(R.layout.fragment_pending, null);
            unbinder = ButterKnife.bind(this, rootView);
            initData();
            initViews();
            initViewData();
            initEvent();
        }
        // 缓存的rootView需要判断是否已经被加过parent
        // 如果有parent需要从parent删除，否则会发生这个rootView已经有parent的错误
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null)
        {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    protected void initData()
    {

        Bundle bundle = getArguments();
        mType = bundle.getString("type");

    }

    @Override
    protected void initViews()
    {

    }

    @Override
    protected void initEvent()
    {
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setPullRefreshEnabled(true);
    }

    @Override
    protected void initViewData()
    {
        mPullToRefreshRecyclerView.setPullLoadEnabled(true);
        mRecyclerView = mPullToRefreshRecyclerView.getRefreshableView();
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
            {
                outRect.set(5, 0, 5, 0);
            }
        });
        // mRecyclerView.addItemDecoration(new AnswerChatHolder.QADecoration(getActivity()));
        emptyDecoration = new EmptyDecoration(getActivity(), "");
        mRecyclerView.addItemDecoration(emptyDecoration);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSpecialAssistaceAdapter = new SpecialAssistaceAdapter(mQuestionInfoList, getActivity(), new MyItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {

                if(mQuestionInfoList.get(position).getAid()>0)

                {
                    Intent intent = new Intent(getActivity(), EvaluationActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("QuestionInfo", mQuestionInfoList.get(position));
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getActivity(), AnswerActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("QUESTION", mQuestionInfoList.get(position));
                    intent.putExtras(mBundle);
                    startActivity(intent);
                }

            }
        });
        mRecyclerView.setAdapter(mSpecialAssistaceAdapter);

    }


    @Override
    public void onResume()
    {
        super.onResume();
        mQuestionInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getQuestionList();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        mQuestionInfoList.clear();
        page = 1;
        mRefreshStatus = 0;
        getQuestionList();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView)
    {
        page += 1;
        mRefreshStatus = 1;
        getQuestionList();
    }


    private void getQuestionList()
    {
        Map<String, String> valuePairs = new HashMap<>();
        valuePairs.put("page", page + "");
        valuePairs.put("rows", "10");
        valuePairs.put("pid", ConfigManager.instance().getUserID());

        if("1".equals(mType))
        {
            valuePairs.put("isAns", "0");

        }
        else
        {
            valuePairs.put("isAns", "1");
        }
        DataRequest.instance().request(getActivity(), Urls.getConsultInfoUrl(), this, HttpRequest.POST, GET_LIST, valuePairs,
                new QuestionListHandler());
    }

    @Override
    public void notify(String action, String resultCode, String resultMsg, Object obj)
    {
        if (mRefreshStatus == 1)
        {
            mPullToRefreshRecyclerView.onPullUpRefreshComplete();
        }
        else
        {
            mPullToRefreshRecyclerView.onPullDownRefreshComplete();
        }
        if (GET_LIST.equals(action))
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
    }

}
