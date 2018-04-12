package com.bangfu.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bangfu.R;


/**
 * <自定义进度弹框>
 * 
 * @author zhong
 */
public class CustomProgressDialog extends Dialog
{
	private static CustomProgressDialog customProgressDialog = null;

	public CustomProgressDialog(Context context)
	{
		super(context);
	}

	public CustomProgressDialog(Context context, int theme)
	{
		super(context, theme);
	}

	public static CustomProgressDialog createDialog(Context context)
	{
		customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);
		customProgressDialog.setCancelable(false);
		customProgressDialog.setContentView(R.layout.dialog_loading);
		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus)
	{

		if (customProgressDialog == null)
		{
			return;
		}

		ImageView imageView = (ImageView) customProgressDialog.findViewById(R.id.loadingImageView);
		RotateAnimation refreshingAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		refreshingAnimation.setDuration(1500);
		refreshingAnimation.setFillAfter(true);
		refreshingAnimation.setRepeatCount(-1);
		// 添加匀速转动动画
		LinearInterpolator lir = new LinearInterpolator();
		refreshingAnimation.setInterpolator(lir);
		imageView.startAnimation(refreshingAnimation);
	}

	/**
	 * 
	 * [Summary] setTitile 标题
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public CustomProgressDialog setTitile(String strTitle)
	{
		return customProgressDialog;
	}

	/**
	 * 
	 * [Summary] setMessage 提示内容
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public CustomProgressDialog setMessage(String strMessage)
	{
		TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.id_tv_loadingmsg);

		if (tvMsg != null)
		{
			tvMsg.setText(strMessage);
		}

		return customProgressDialog;
	}
}
