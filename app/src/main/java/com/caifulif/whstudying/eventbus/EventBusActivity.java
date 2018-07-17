package com.caifulif.whstudying.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.caifulif.whstudying.R;
import com.caifulif.whstudying.baseActivity.BaseAcvtivty;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventBusActivity extends BaseAcvtivty {

    private TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        //传递对象数据
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EvevtBean evevtBean = new EvevtBean();
                evevtBean.setBoyFriendName("小明");
                evevtBean.setState("love");
                evevtBean.setGrilFriendName("小红");
                EventBus.getDefault().post(evevtBean);

            }
        });
        textView = (TextView)findViewById(R.id.textView);
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected ViewAnimator initViewAnimator() {
        return null;
    }

    @Subscribe
    public void onEvent (EvevtBean bean)
    {
        textView.setText(bean.getBoyFriendName()+" "+bean.getState()+" "+bean.getGrilFriendName());
    }

}
