package com.caifulif.whstudying.mvp.modle;


import android.content.Context;

/**
 * Created by 皓 on 2018/4/3.
 */

public interface IUserDao {

    void checkUser(Context context, String username, String userpwd, LoginCallBack callBack);

    interface LoginCallBack {
        void onSuccess();

        void onFiled();
    }

}
