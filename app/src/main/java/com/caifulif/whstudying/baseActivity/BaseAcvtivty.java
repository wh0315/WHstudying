package com.caifulif.whstudying.baseActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ViewAnimator;

import com.caifulif.whstudying.application.MyApplication;
import com.caifulif.whstudying.eventbus.EmptyEvent;
import com.caifulif.whstudying.widget.ProgressDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by 皓 on 2018/3/16.
 */

public abstract class BaseAcvtivty extends AppCompatActivity {
    ViewAnimator viewAnimator;
    private Context context ;
    private ProgressDialog progressDialog ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this ;
        MyApplication.getInstance().addActivity(this);
        EventBus.getDefault().register(BaseAcvtivty.this);

/*
        //一像素Activity
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 1;
        lp.height = 1;
        window.setAttributes(lp);
        VMDaemonManager.getInstance().setDaemonActivity(this);*/

        viewAnimator = initViewAnimator();

    }

    public void showLoading() {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(BaseAcvtivty.this);
        }
        progressDialog.showDialog();
    }


    public void hideLoading() {
        if (null != progressDialog) {
            progressDialog.dismissDialog();
        }
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(BaseAcvtivty.this)){
            EventBus.getDefault().register(BaseAcvtivty.this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(BaseAcvtivty.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(BaseAcvtivty.this);
    }

    /**
     * 在父Activity中加上一个空事件  不同Actiivty或者不同类之间的事件传送
     * @param event
     */
    @Subscribe
    public void  onEvent(EmptyEvent event){}


    /**
     * 状态栏是否设置成半透明
     */
    protected abstract boolean isApplyStatusBarTranslucency();


    /**
     * 由子类提供包含有 Empty/Error/ProgressBar 的 ViewAnimator
     * 如果子类想要个性化Empty/Error/ProgressBar, 在xml中定制ViewAnimator, 然后将对象返回即可
     * tip:定制的xml布局, 必须遵循第一个子布局为none, 第二个子布局为loading, 第三个子布局为empty, 第四个为error
     */
    protected abstract ViewAnimator initViewAnimator();

    /**
     * 隐藏progressBar/ErrorView/EmptyView
     */
    protected void showNone() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 0)
                if (!viewAnimator.getChildAt(0).isShown())
                    viewAnimator.setDisplayedChild(0);
    }

    /**
     * 为子类提供展示progressBar的通用方法
     */
    protected void showProgressBar() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 1)
                if (!viewAnimator.getChildAt(1).isShown())
                    viewAnimator.setDisplayedChild(1);
    }

    /**
     * 为子类提供展示EmptyView的通用方法
     */
    protected void showEmptyView() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 2)
                if (!viewAnimator.getChildAt(2).isShown())
                    viewAnimator.setDisplayedChild(2);
    }


    /**
     * 为子类提供展示ErrorView的通用方法
     */
    protected void showErrorView() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 3)
                if (!viewAnimator.getChildAt(3).isShown())
                    viewAnimator.setDisplayedChild(3);
    }

    /**
     * 为子类提供展示NetworkView的通用方法
     */
    protected void showNetworkView() {
        if (viewAnimator != null)
            if (viewAnimator.getChildCount() > 4)
                if (!viewAnimator.getChildAt(4).isShown())
                    viewAnimator.setDisplayedChild(4);
    }



}
