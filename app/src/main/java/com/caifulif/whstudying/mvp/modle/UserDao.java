package com.caifulif.whstudying.mvp.modle;

import android.content.Context;

import com.caifulif.whstudying.PageConn.PageConnUtils;

/**
 * Created by 皓 on 2018/4/3.
 */

public class UserDao implements IUserDao {
    @Override
    public void checkUser(Context context, String username, String userpwd, LoginCallBack
            callBack) {
        PageConnUtils.mLogin(context, username, userpwd, new PageConnUtils.OnFisrtPageLintener() {
            @Override
            public void pageLintener(User errorBean) {

            }

            @Override
            public void errorBeanLintener(String codeDesc) {

            }
        });
    }
}
