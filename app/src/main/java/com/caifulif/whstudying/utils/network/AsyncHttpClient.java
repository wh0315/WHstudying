package com.caifulif.whstudying.utils.network;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by 皓 on 2018/3/20.
 */

public abstract class AsyncHttpClient extends AsyncHttpResponseHandler {
    @Override
    public void onFailure(Throwable arg0) {
        Log.d("info", "请求失败，隐藏进度条"+ arg0);
        onMyFailure(arg0);
        super.onFailure(arg0);
    }

    @Override
    public void onStart() {
        Log.d("info", "请求数据开始，弹出进度条框");
        super.onStart();
    }

    @Override
    public void onSuccess(String arg0) {
        Log.d("info", "请求成功，隐藏进度条"+ arg0);
        onMySuccess(arg0);
        super.onSuccess(arg0);
    }

    public abstract void onMySuccess(String result);

    public abstract void onMyFailure(Throwable arg0);

}