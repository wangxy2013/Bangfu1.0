package com.bangfu.listener;


/**
 * <回调接口>
 * 
 */
public class OnClickListenerUtils
{


	/**
	 * <分享弹框回调接口>
	 * 
	 * @author zhong
	 */
	public interface OnShareReturnClickListerner
	{
		/**
		 * <分享给微信朋友>
		 */
		public abstract void toWeixin();

		/**
		 * <分享到微信朋友圈>
		 */
		public abstract void toWeixinPyq();

		/**
		 * <分享给QQ朋友>
		 */
		public abstract void toQQFriend();

		/**
		 * <分享到QQ控件>
		 */
		public abstract void toQQZone();

		/**
		 * <分享到新浪微博>
		 */
		public abstract void toSina();
	}


	/**
	 * 点击确定按钮
	 */
	public interface OnConfirmBtnClickListerner
	{
		public abstract void onConfirm();
	}

	/**
	 * 点击确定按钮
	 */
	public interface OnSubmitBtnClickListerner
	{
		public abstract void onSubmit(String content);
	}


}
