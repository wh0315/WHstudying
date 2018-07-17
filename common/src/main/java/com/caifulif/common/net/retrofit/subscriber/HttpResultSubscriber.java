package com.caifulif.common.net.retrofit.subscriber;

import android.content.Context;

import com.caifulif.common.R;
import com.caifulif.common.net.utils.NetWorkUtil;
import com.orhanobut.logger.Logger;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * Created by _SOLID
 * Date:2016/7/27
 * Time:21:27
 */
public abstract class HttpResultSubscriber<T> extends Subscriber<T> {

    private Context context;
    private String resultMessage;
    private String resultCode;
    private int resultType;
    private String errorMsg;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if(e == null){
            onFailure(errorMsg);
            return;
        }
        Logger.e(e.getMessage());
        e.printStackTrace();
        errorMsg = e.getMessage();
        if(context != null){
            if(!NetWorkUtil.isNetWorkConnected(context)){//无网络
                errorMsg = context.getString(R.string.no_net_conneted);
            }else if(e instanceof UnknownHostException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }else if(e instanceof ConnectException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }else if(e instanceof TimeoutException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }else if(e instanceof SocketTimeoutException){//连接服务失败
                errorMsg = context.getString(R.string.no_server_net_conneted);
            }
        }
        onFailure(errorMsg);
    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultType(int resultType) {
        this.resultType = resultType;
    }

    public abstract void onSuccess(T resultData);

    public abstract void onFailure(String errMessage);

    public abstract void onCookieInvalid(String errMessage);

    public void setContext(Context context) {
        this.context = context;
    }

    private void startToLoginActivity(){
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        // 设置action, 隐式跳转
//        intent.setAction("cc.ewell.emdt.ui.login.loginactivity");
//        context.startActivity(intent);
    }
}
