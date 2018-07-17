package com.caifulif.whstudying.BroadcastReceiver;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ViewAnimator;

import com.caifulif.whstudying.R;
import com.caifulif.whstudying.baseActivity.BaseAcvtivty;

public class TestBroadcast extends BaseAcvtivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_broadcast);
        sendToSXL();
        startActivity(new Intent(TestBroadcast.this,GetBroadcastActivity.class));
    }

    //给静态广播发送信息
    public void sendToSXL() {
        //发送广播使用sendBroadcast，需要指定Intent的action来说明发送什么样的广播。
        //Intent里面的参数是action，要和静态注册的广播的actin对应
        Intent intent = new Intent("myBroadcastReceiver");
        intent.putExtra("msg", "这是MainActivity页面发送的广播----》》");
        sendBroadcast(intent);

    }


    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected ViewAnimator initViewAnimator() {
        return null;
    }
}
