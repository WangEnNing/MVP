package com.allure.mvp.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allure.mvp.R;
import com.allure.mvp.util.LogUtils;

import java.util.List;


public class BaseActivity extends Activity
{
	private TextView top_title;
	private LinearLayout top_back;
	protected String empty = "";
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		AppManager.getInstance().addActivity(this);
	}

	/**
	 * 设置标题文字
	 * 
	 * @param String
	 */
	protected void SetText(String String)
	{
		// registration_title = findViewById(R.id.registration_title);
		top_title = (TextView) findViewById(R.id.title_text);

		top_title.setText(String);
	}

	/*
	 * 返回上一层
	 */
	protected void BackFinsh()
	{
		top_back = (LinearLayout) findViewById(R.id.imageviewback);

		top_back.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{

				BaseActivity.this.finish();
			}
		});
	}

	/**
	 * 通过类名启动Activity
	 * 
	 * @param pClass
	 */
	protected void openActivity(Class<?> pClass)
	{
		openActivity(pClass, null);
	}

	/**
	 * 通过类名启动Activity，并且含有Bundle数据
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	protected void openActivity(Class<?> pClass, Bundle pBundle)
	{
		Intent intent = new Intent(this, pClass);
		if (pBundle != null)
		{
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	public static boolean isBackground(Context context)
	{

		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		for (RunningAppProcessInfo appProcess : appProcesses)
		{
			if (appProcess.processName.equals(context.getPackageName()))
			{
				if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_BACKGROUND)
				{
					LogUtils.d("后台", appProcess.processName);
					return true;
				} else
				{
					LogUtils.d("前台", appProcess.processName);
					return false;
				}
			}
		}
		return false;
	}

//	/**
//	 * 显示正在加载的进度条
//	 *
//	 */
//	public void showProgressDialog()
//	{
//		View view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null);
//		if (progressDialog != null && progressDialog.isShowing())
//		{
//			progressDialog.dismiss();
//			progressDialog = null;
//		}
//		progressDialog = new ProgressDialog(this);
//		progressDialog.setCancelable(true);
//		progressDialog.setCanceledOnTouchOutside(false);
//		try
//		{
//			progressDialog.show();
//			progressDialog.setContentView(view);
//		} catch (BadTokenException exception)
//		{
//			exception.printStackTrace();
//		}
//	}
//
//	/**
//	 * 不允许取消的progressBar
//	 */
//	public void showProgressDialog2()
//	{
//		View view = LayoutInflater.from(this).inflate(R.layout.loading_layout, null);
//		if (progressDialog != null && progressDialog.isShowing())
//		{
//			progressDialog.dismiss();
//			progressDialog = null;
//		}
//		progressDialog = new ProgressDialog(this);
//		progressDialog.setCancelable(false);
//		progressDialog.setCanceledOnTouchOutside(false);
//		try
//		{
//			progressDialog.show();
//			progressDialog.setContentView(view);
//		} catch (BadTokenException exception)
//		{
//			exception.printStackTrace();
//		}
//	}
//
//	/**
//	 * 隐藏正在加载的进度条
//	 *
//	 */
//	public void dismissProgressDialog()
//	{
//		if (null != progressDialog && progressDialog.isShowing() == true)
//		{
//			progressDialog.dismiss();
//		}
//	}
}