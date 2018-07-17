package com.caifulif.whstudying.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.os.Process;
import android.view.Window;

import com.caifulif.whstudying.utils.ToastUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 皓 on 2018/3/16.
 */

public class MyApplication extends Application {

    private static MyApplication instance = null;
    private List<Activity> activityList = new LinkedList<Activity>();
    //单例模式
    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        ToastUtil.init(this);//初始化吐司
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @SuppressLint("NewApi")
    public void exit() {
        for (Activity a : activityList) {
            if (!a.isDestroyed()) {
                a.finish();
            }
        }
        android.os.Process.killProcess(Process.SIGNAL_KILL);
    }

    public void finishActivity(Activity activity) {
        activityList.remove(activity);
        activity.finish();
    }

    @SuppressLint("NewApi")
    public void finishBeforeActivity() {
        activityList.remove(activityList.size() - 1);
        for (Activity a : activityList) {
            if (!a.isDestroyed()) {
                a.finish();
            }
        }
    }
}
