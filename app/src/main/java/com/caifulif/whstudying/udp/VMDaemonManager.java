package com.caifulif.whstudying.udp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.caifulif.whstudying.application.MyApplication;
import com.caifulif.whstudying.baseActivity.BaseAcvtivty;
import com.caifulif.whstudying.udp.service.VMCoreService;

/**
 * 守护进程管理类
 * Created by lzan13 on 2017/3/9.
 */
public class VMDaemonManager {

    private final static String TAG = VMDaemonManager.class.getSimpleName();

    private static VMDaemonManager instance = null;

    private Activity daemonActivity;

    private VMDaemonManager() {
    }
    /**
     * 获取单例类实例
     */
    public static VMDaemonManager getInstance() {
        if (instance == null) {
            instance = new VMDaemonManager();
        }
        return instance;
    }
    /**
     * 启动守护 Activity，其实就是一像素大小的流氓 activity
     */
    public void startDaemonActivity() {
        Log.i(TAG, "startCoreProcess: 启动流氓 Activity");
        MyApplication.getInstance()
                .startActivity(new Intent(MyApplication.getInstance(), BaseAcvtivty.class));
    }
    /**
     * 结束流氓的 activity
     */
    public void finishDaemonActivity() {
        Log.i(TAG, "startCoreProcess: 结束流氓 Activity");
        if (daemonActivity != null) {
            daemonActivity.finish();
        }
    }
    /**
     * 启动核心进程
     */
    public void startCoreProcess() {
        Log.i(TAG, "startCoreProcess: 启动核心进程");
        Intent wakeIntent = new Intent(MyApplication.getInstance(), VMCoreService.class);
        MyApplication.getInstance().startService(wakeIntent);
    }

    /**
     * 保存当前启动的一像素 Activity
     */
    public void setDaemonActivity(Activity daemonActivity) {
        this.daemonActivity = daemonActivity;
    }
}
