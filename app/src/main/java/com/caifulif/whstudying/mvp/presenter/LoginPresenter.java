package com.caifulif.whstudying.mvp.presenter;

import android.content.Context;

import com.caifulif.whstudying.mvp.modle.IUserDao;
import com.caifulif.whstudying.mvp.modle.UserDao;
import com.caifulif.whstudying.mvp.view.IloginView;

/**
 * Created by 皓 on 2018/4/3.
 */

public class LoginPresenter implements ILoginPresenter {


    private IloginView iloginView;
    private IUserDao iUserDao;
    private Context context;

    public LoginPresenter(IloginView iloginView) {
        this.iloginView = iloginView;
        iUserDao = new UserDao();
    }

    @Override
    public void login(Context context) {
        iUserDao.checkUser(context, iloginView.getUsername(), iloginView.getUserPwd(), new
                IUserDao.LoginCallBack() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onFiled() {

                    }
                });
    }
}
