package com.caifulif.whstudying.BroadcastReceiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.caifulif.whstudying.R;
import com.caifulif.whstudying.utils.ToastUtil;

public class GetBroadcastActivity extends Activity implements Receiver.ActionListener {


    private Receiver receiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_broadcast);
        receiver = new Receiver();
        //注册广播接收者,需要一个意图对象,也需要action参数,这里是定义Action参数
        IntentFilter filter = new IntentFilter("javaReceiver");
        //动态设置广播的优先级
        filter.setPriority(999);
        //注册广播,
        registerReceiver(receiver, filter);
        receiver.setListener(this);

    }

    @Override
    protected void onDestroy() {
        //在适当的时候要解除广播接收者的绑定
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public void receive(Context context, Intent intent) {
        ToastUtil.show(intent.getStringExtra("msg"));
    }
}
