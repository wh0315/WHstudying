package com.caifulif.whstudying.PageConn;

import android.content.Context;
import android.text.TextUtils;

import com.caifulif.common.net.retrofit.service.RxBaseApi;
import com.caifulif.common.net.retrofit.subscriber.HttpResultSubscriber;
import com.caifulif.whstudying.constants.Constans;
import com.caifulif.whstudying.mvp.modle.User;
import com.caifulif.whstudying.utils.ToastUtil;
import com.caifulif.whstudying.utils.log.JsonLog;
import com.caifulif.whstudying.widget.ProgressDialog;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import constants.CommonServerConstant;

/**
 * Created by 皓 on 2018/3/26.
 */

public class PageConnUtils {


    private static Context mContext;
    private static ProgressDialog progressDialog ;
    /**
     *  获json数据
     *$author wh
     *created at 2018/3/26 13:34
     */
    public static void mHoueIndex(Context context, final OnFisrtPageLintener mOnFisrtPageLintener) {
        mContext = context ;
        showLoading();
        RxBaseApi.getDefault(context, CommonServerConstant.SERVER_IP_PORT, null).executePost(context, new HttpResultSubscriber<String>() {
            @Override
            public void onSuccess(String resultData) {
                hideLoading();
                if (!TextUtils.isEmpty(resultData)) {
                    if (mOnFisrtPageLintener != null) {
                      /*  Gson gson = new Gson();
                        ErrorBean errorBean = gson.fromJson(resultData, ErrorBean.class);
                        mOnFisrtPageLintener.pageLintener(errorBean);*/
                    }
                }
            }
            @Override
            public void onFailure(String errMessage) {
                ToastUtil.show(errMessage);
                hideLoading();
                JsonLog.printJson("onFailure", errMessage, "onFailure");
            }
            @Override
            public void onCookieInvalid(String errMessage) {
            }
        }, String.class,"/BranchWeChat/app/partyFee/getPartyFlow.action", null);

    }

    /**
     * 登录
     * $author wh
     * created at 2018/4/3 15:04
     */
    public static void mLogin(Context context, String mobile, String passWord, final
    OnFisrtPageLintener mOnFisrtPageLintener) {
        mContext = context;
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("passWord", passWord);
        RxBaseApi.getDefault(context, Constans.BASE_URL + Constans.BRANCHWECHAT, map).executePost
                (mContext, new HttpResultSubscriber<String>() {
                    @Override
                    public void onSuccess(String resultData) {
                        showLoading();
                        if (!TextUtils.isEmpty(resultData)) {
                            if (mOnFisrtPageLintener != null) {
                                JsonLog.printJson("onSuccess", resultData, "resultData");
                                Gson gson = new Gson();
                                User errorBean = gson.fromJson(resultData, User.class);
                                mOnFisrtPageLintener.pageLintener(errorBean);
                            }
                        }
                    }

                    @Override
                    public void onFailure(String errMessage) {
                        ToastUtil.show(errMessage);
                        hideLoading();
                        mOnFisrtPageLintener.errorBeanLintener(errMessage);
                        JsonLog.printJson("onFailure", errMessage, "onFailure");
                    }

                    @Override
                    public void onCookieInvalid(String errMessage) {

                    }
                }, String.class, Constans.USERLOGIN, map);
    }

    public static void showLoading() {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(mContext);
        }
        progressDialog.showDialog();
    }

    public static void hideLoading() {
        if (null != progressDialog) {
            progressDialog.dismissDialog();
        }
    }


    public interface OnFisrtPageLintener {
        void pageLintener(User errorBean);

        void errorBeanLintener(String codeDesc);
    }


}
