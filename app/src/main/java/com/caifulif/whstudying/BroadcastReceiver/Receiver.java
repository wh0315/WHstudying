package com.caifulif.whstudying.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 皓 on 2018/6/28.
 */
public class Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener != null)
            listener.receive(context, intent);
    }



    private ActionListener listener;

    public void setListener(ActionListener l) {
        listener = l;
    }

    public interface ActionListener {
        void receive(Context context, Intent intent);
    }
}
