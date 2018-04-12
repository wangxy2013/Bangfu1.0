package com.bangfu.listener;

/**
 * User: 王先云
 * Date: 2015-09-02 09:02
 * DESC: 一句话简单描述
 */
public class MyOnClickListener
{
    /**
     * <pop dismiss 监听>
     *
     * @author zhong
     */
    public interface OnLivePopDismissListener
    {

        public abstract void dismissBackCall();
    }



    /**
     * <pop dismiss 监听>
     *
     * @author zhong
     */
    public interface OnChooseGiftListener
    {

        public abstract void setGiftId(String id, int num);
    }


    public interface OnSuccessListener
    {
        public abstract void onSuccess();
    }


    public interface OnSubmitListener
    {
        public abstract void onSubmit(String content);
    }


    public interface  OnQuestionSubmitListener
    {
        public abstract void onSubmit(String content, String type);
    }


    public interface OnCallBackListener
    {
        public abstract void OnCallBack(String msgId);
    }

}
