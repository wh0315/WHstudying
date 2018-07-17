package com.caifulif.whstudying.udp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.caifulif.whstudying.baseActivity.BaseAcvtivty;

/**
 * Created by 皓 on 2017/12/8.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    //重写onReceive方法
    @Override
    public void onReceive(Context context, Intent intent) {
//后边的XXX.class就是要启动的服务
        Intent service = new Intent(context,BaseAcvtivty.class);
        context.startService(service);
        Log.v("TAG", "开机自动服务自动启动.....");
//启动应用，参数为需要自动启动的应用的包名
        intent = context.getPackageManager().getLaunchIntentForPackage("com.mite.filchcontacts");
        context.startActivity(intent );
    }
}
