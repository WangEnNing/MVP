package com.allure.mvp.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;
/**
 * 
 * @ClassName: AppManager 
 * @Description: Activity管理类
 * @author wen
 */
public class AppManager
{
	private static Stack<Activity> mActivityStack;
	private static AppManager mAppManager;

	private AppManager()
	{
	}

	/**
	 * 单一实例
	 */
	public static AppManager getInstance()
	{
		if (mAppManager == null)
		{
			mAppManager = new AppManager();
		}
		return mAppManager;
	}

	/**
	 * 添加Activity到堆栈
	 */
	public void addActivity(Activity activity)
	{
		if (mActivityStack == null)
		{
			mActivityStack = new Stack<Activity>();
		}
		mActivityStack.add(activity);
	}

	/**
	 * 获取栈顶Activity（堆栈中最后一个压入的）
	 */
	public Activity getTopActivity()
	{
		Activity activity = mActivityStack.lastElement();
		return activity;
	}

	/**
	 * 结束栈顶Activity（堆栈中最后一个压入的）
	 */
	public void killTopActivity()
	{
		Activity activity = mActivityStack.lastElement();
		killActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void killActivity(Activity activity)
	{
		if (activity != null)
		{
			mActivityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 */
	public void killActivity(Class<?> cls)
	{
		for (Activity activity : mActivityStack)
		{
			if (activity.getClass().equals(cls))
			{
				killActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void killAllActivity()
	{
		for (int i = 0, size = mActivityStack.size(); i < size; i++)
		{
			if (null != mActivityStack.get(i))
			{
				mActivityStack.get(i).finish();
			}
		}
		mActivityStack.clear();
	}

	/**
	 * 结束除了当前的其他所有Activity
	 * @param activity
	 */
	public void killOthersActivity(Activity activity){
		for (int i = 0, size = mActivityStack.size(); i < size; i++)
		{
			if (null != mActivityStack.get(i) && activity != mActivityStack.get(i))
			{
				mActivityStack.get(i).finish();
			}
		}
		mActivityStack.clear();
		mActivityStack.add(activity);
	}
	/**
	 * 根据名字查找Activity
	 * @param className
	 * @return
	 */
	public Activity getActivityByName(String className){
		Activity activity = null;
		for (int i = 0, size = mActivityStack.size(); i < size; i++)
		{
			if (null != mActivityStack.get(i))
			{
				if (mActivityStack.get(i).getClass().getName().equals(className))
				{
					activity = mActivityStack.get(i);
				}
			}
		}
		return activity;
	}
	/**
	 * 删除Activity
	 * @param activity
	 */
	public void removeActivity(Activity activity){
		int pos = -1;
		if (activity != null)
		{
			for (int i = 0, size = mActivityStack.size(); i < size; i++)
			{
				if (null != mActivityStack.get(i))
				{
					if (activity == mActivityStack.get(i))
					{
						pos = i;
						activity.finish();
					}
				}
			}
			mActivityStack.remove(pos);
		}
	}
	
	/**
	 * 退出应用程序
	 */
	@SuppressWarnings("deprecation")
	public void AppExit(Context context)
	{
		try
		{
			killAllActivity();
			ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			activityMgr.restartPackage(context.getPackageName());
			System.exit(0);
		} catch (Exception e)
		{
		}
	}
}
